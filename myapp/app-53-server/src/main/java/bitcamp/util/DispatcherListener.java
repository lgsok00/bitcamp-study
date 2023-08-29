package bitcamp.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.MySQLBoardDao;
import bitcamp.myapp.dao.MySQLMemberDao;
import bitcamp.myapp.controller.BoardAddListener;
import bitcamp.myapp.controller.BoardDeleteListener;
import bitcamp.myapp.controller.BoardDetailListener;
import bitcamp.myapp.controller.BoardListListener;
import bitcamp.myapp.controller.BoardUpdateListener;
import bitcamp.myapp.controller.LoginListener;
import bitcamp.myapp.controller.MemberAddListener;
import bitcamp.myapp.controller.MemberDeleteListener;
import bitcamp.myapp.controller.MemberDetailListener;
import bitcamp.myapp.controller.MemberListListener;
import bitcamp.myapp.controller.MemberUpdateListener;

public class DispatcherListener implements ActionListener {

  // 객체 보관소
  Map<String,Object> beanContainer = new HashMap<>();

  public DispatcherListener() throws Exception {

    // Mybatis 준비
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
        new SqlSessionFactoryBuilder().build(
            Resources.getResourceAsStream("bitcamp/myapp/config/mybatis-config.xml")));
    beanContainer.put("sqlSessionFactory", sqlSessionFactory);

    // DAO 준비
    MemberDao memberDao = new MySQLMemberDao(sqlSessionFactory);
    BoardDao boardDao = new MySQLBoardDao(sqlSessionFactory);
    beanContainer.put("memberDao", memberDao);
    beanContainer.put("boardDao", boardDao);

    // Listener 준비
    beanContainer.put("login", new LoginListener(memberDao));

    beanContainer.put("member/add", new MemberAddListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/list", new MemberListListener(memberDao));
    beanContainer.put("member/detail", new MemberDetailListener(memberDao));
    beanContainer.put("member/update", new MemberUpdateListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/delete", new MemberDeleteListener(memberDao, sqlSessionFactory));

    beanContainer.put("board/add", new BoardAddListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/list", new BoardListListener(1, boardDao));
    beanContainer.put("board/detail", new BoardDetailListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/update", new BoardUpdateListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/delete", new BoardDeleteListener(1, boardDao, sqlSessionFactory));

    beanContainer.put("reading/add", new BoardAddListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("reading/list", new BoardListListener(2, boardDao));
    beanContainer.put("reading/detail", new BoardDetailListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("reading/update", new BoardUpdateListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("reading/delete", new BoardDeleteListener(2, boardDao, sqlSessionFactory));
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    ActionListener listener = (ActionListener) beanContainer.get(prompt.getAttribute("menuPath"));
    if (listener == null) {
      throw new RuntimeException("해당 요청을 처리할 수 없습니다.");
    }
    listener.service(prompt);
  }

  public Object getBean(String name) {
    return beanContainer.get(name);
  }
}
