package bitcamp.myapp;

import java.io.File;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class App {

  public static void main(String[] args) throws Exception {

    System.out.println("서버 시작!");

    // Tomcat 서버 구동시키는 객체 준비
    Tomcat tomcat = new Tomcat();

    // 서버의 포트 번호 설정
    tomcat.setPort(8888);

    // Tomcat 서버를 실행하는 동안 사용할 임시 폴더 지정
    tomcat.setBaseDir("temp");

    // Tomcat 서버의 연결 정보를 설정
    Connector connector = tomcat.getConnector();
    connector.setURIEncoding("UTF-8");

    // Tomcat 서버에 배포할 Web Application 환경 정보 준비
    StandardContext ctx = (StandardContext) tomcat.addWebapp("/", // Context 경로(Web Application 경로)
        new File("./src/main/webapp").getAbsolutePath()); // Web Application 파일이 있는 실제 경로
    ctx.setReloadable(true);

    // Web Application 기타 정보 설정
    WebResourceRoot resources = new StandardRoot(ctx);

    // Web Application에 Servlet 클래스 등록
    resources.addPreResources(new DirResourceSet(resources, // Root Web Application 정보
        "/WEB-INF/classes", // Servlet 클래스 파일의 위치 정보
        new File("bin/main").getAbsolutePath(), // Servlet 클래스 파일이 있는 실제 경로
        "/" // Web Application 내부 경로
    ));

    // Web Application을 설정 정보를 Web Application 환경 정보에 등록
    ctx.setResources(resources);

    // Tomcat Server 구동
    tomcat.start();

    // Tomcat Server를 구동한 후 종료될 때까지 JVM을 끝내지 말고 기다린다.
    tomcat.getServer().await();

    System.out.println("서버 종료!");
  }
}