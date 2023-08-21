package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;

@WebServlet("/board/add")

// MultipartConfig 추가
@MultipartConfig(maxFileSize = 1024 * 1024 * 50)
public class BoardAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    try {
      Board board = new Board();
      board.setWriter(loginUser);
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      board.setCategory(Integer.parseInt(request.getParameter("category")));
      System.out.println(board);

      String uploadDir = request.getServletContext().getRealPath("/upload/board/");
      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

      for (Part part : request.getParts()) {
        System.out.println(part.getName());
        if (part.getName().equals("files") && part.getSize() > 0) {
          String filename = UUID.randomUUID().toString();
          part.write(uploadDir + filename);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(filename);
          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.printf("<meta http-equiv='refresh' content='1;url=/board/list?category=%d'>\n",
          board.getCategory());
      out.println("<title>게시글</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>게시글 등록</h1>");

      try {
        InitServlet.boardDao.insert(board);

        // 첨부파일이 들어 있는 경우만 insert
        if (attachedFiles.size() > 0) {
          int count = InitServlet.boardDao.insertFiles(board);
          System.out.println(count);
        }
        InitServlet.sqlSessionFactory.openSession(false).commit();
        out.println("<p>등록 성공입니다!</p>");

      } catch (Exception e) {
        InitServlet.sqlSessionFactory.openSession(false).rollback();
        out.println("<p>등록 실패입니다!</p>");
        throw new RuntimeException(e);
      }

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}

