package bitcamp.myapp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class AppWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  public AppWebApplicationInitializer() {
    System.out.println("AppWebApplicationInitializer 생성됨!");
  }

  @Override
  protected String getServletName() {
    return "app";
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    // ContextLoaderListener의 IoC Container가 사용할 java config 클래스를 지정한다.
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    // DispatcherServlet의 IoC Container가 사용할 java config 클래스를 지정한다.
    return new Class[] {AppConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    // DispatcherServlet의 URL을 지정한다.
    return new String[] {"/app/*"};
  }

  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement("temp", 10000000, 15000000, 1000000));
  }
}
