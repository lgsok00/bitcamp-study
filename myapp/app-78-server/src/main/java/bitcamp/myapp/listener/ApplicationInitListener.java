package bitcamp.myapp.listener;

import bitcamp.myapp.config.AppConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

// @WebListener
public class ApplicationInitListener implements ServletContextListener {

  public ApplicationInitListener() {
    System.out.println("ApplicationInitListener 생성됨!");
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // Servlet Container가 Web Application을 로딩할 때 이 메서드를 호출한다.
    ServletContext sc = sce.getServletContext();

    // DispatcherServlet이 사용할 IoC Container를 준비한다.
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(AppConfig.class);

    // DispatcherServlet을 생성하여 Servlet Container에 등록한다.
    DispatcherServlet servlet = new DispatcherServlet(context);
    ServletRegistration.Dynamic registration = sc.addServlet("app", servlet);
    registration.setLoadOnStartup(1);
    registration.addMapping("/app/*");
    registration.setMultipartConfig(new MultipartConfigElement("temp", 10000000, 15000000, 1000000));
  }
}
