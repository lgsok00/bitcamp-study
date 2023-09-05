package bitcamp.myapp.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

// 요청이나 응답을 수행했을 때 Servlet 컨테이너로 부터 알림을 받는 Observer 객체
@WebListener // Servlet 컨테이너에게 이 클래스가 Listener 임을 알린다.
public class MyServletRequestListener implements ServletRequestListener {

  public MyServletRequestListener() {
    System.out.println("MyServletRequestListener 객체 생성!");
  }

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    // Client 요청에 대한 응답을 완료하면
    // 요청을 처리하는 동안 스레드가 사용했던 SqlSession 객체를 스레드에서 제거한다.
    // SqlSessionFactoryProxy sqlSessionFactoryProxy = (SqlSessionFactoryProxy) sre.getServletContext().getAttribute("sqlSessionFactory");
    // sqlSessionFactoryProxy.clean();
  }
}
