package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/auth/login")
public class LoginController implements PageController {

  MemberDao memberDao;

  public LoginController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/auth/form.jsp";
    }

    Member m = new Member();
    m.setEmail(request.getParameter("email"));
    m.setPassword(request.getParameter("password"));

    // Cookie
    if (request.getParameter("saveEmail") != null) {
      Cookie cookie = new Cookie("email", m.getEmail());
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    Member loginUser = memberDao.findByEmailAndPassword(m);

    if (loginUser == null) {
      request.setAttribute("refresh", "2;url=/app/auth/login");
      new Exception("회원 정보가 일치하지 않습니다.");
    }

    // 로그인 정보를 다른 요청에서도 사용할 있도록 세션 보관소에 담아 둔다.
    request.getSession().setAttribute("loginUser", loginUser);
    return "redirect:/";
  }
}
