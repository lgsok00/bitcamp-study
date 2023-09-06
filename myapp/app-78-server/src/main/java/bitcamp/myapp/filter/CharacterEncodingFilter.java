package bitcamp.myapp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

  public CharacterEncodingFilter() {
    System.out.println("CharacterEncodingFilter 생성됨!");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    // System.out.println("CharacterEncodingFilter.doFilter() 호출됨!");

    // chain에 연결된 다음 작업을 수행하기 전에 해야할 일
    request.setCharacterEncoding("UTF-8");

    // 다음 작업(Filter 또는 Servlet)을 수행
    chain.doFilter(request, response);
  }
}
