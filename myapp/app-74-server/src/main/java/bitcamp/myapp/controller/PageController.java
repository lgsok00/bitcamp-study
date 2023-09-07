package bitcamp.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Page Controller 사용 규칙 정의
public interface PageController {
  String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}