package bitcamp.myapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import bitcamp.myapp.handler.BoardAddListener;
import bitcamp.myapp.handler.BoardDeleteListener;
import bitcamp.myapp.handler.BoardDetailListener;
import bitcamp.myapp.handler.BoardListListener;
import bitcamp.myapp.handler.BoardUpdateListener;
import bitcamp.myapp.handler.FooterListener;
import bitcamp.myapp.handler.HeaderListener;
import bitcamp.myapp.handler.HelloListener;
import bitcamp.myapp.handler.MemberAddListener;
import bitcamp.myapp.handler.MemberDeleteListener;
import bitcamp.myapp.handler.MemberDetailListener;
import bitcamp.myapp.handler.MemberListListener;
import bitcamp.myapp.handler.MemberUpdateListener;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;

public class App {

  // ArrayList 위로 올리기
  ArrayList<Member> memberList = new ArrayList<>();
  LinkedList<Board> boardList = new LinkedList<>();
  LinkedList<Board> readingList = new LinkedList<>();

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  // 생성자
  public App() {
    // menu 준비
    prepareMenu();
  }

  public static void main(String[] args) {
    // app 클래스
    new App().execute();
  }

  static void printTitle() {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------------");
  }

  public void execute() {
    printTitle();
    // data load
    loadData();
    mainMenu.execute(prompt);
    // data save
    saveData();
    prompt.close();
  }

  // loadData
  private void loadData() {
    loadMember();
    loadBoard();
    // loadReading();
  }

  // saveData
  private void saveData() {
    saveMember();
    saveBoard();
    // saveReading();
  }

  // 서브 메뉴 -> 메인 메뉴 추가
  private void prepareMenu() {
    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberList)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberList)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberList)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberList)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberList)));
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardList)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardList)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardList)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardList)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardList)));
    mainMenu.add(boardMenu);

    MenuGroup readingMenu = new MenuGroup("독서록");
    readingMenu.add(new Menu("등록", new BoardAddListener(readingList)));
    readingMenu.add(new Menu("목록", new BoardListListener(readingList)));
    readingMenu.add(new Menu("조회", new BoardDetailListener(readingList)));
    readingMenu.add(new Menu("변경", new BoardUpdateListener(readingList)));
    readingMenu.add(new Menu("삭제", new BoardDeleteListener(readingList)));
    mainMenu.add(readingMenu);


    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new FooterListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new HeaderListener());
    mainMenu.add(helloMenu);
  }

  static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 회원\n");
    menu.append("2. 게시글\n");
    menu.append("3. 독서록\n");
    menu.append("0. 종료");

    return menu.toString();
  }

  private void loadMember() {
    try {
      FileInputStream in = new FileInputStream("member.data");
      int size = in.read() << 8;
      size |= in.read();

      byte[] buf = new byte[1000];

      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setName(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setEmail(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setPassword(new String(buf, 0, length, "UTF-8"));

        member.setGender((char) (in.read() << 8 | in.read()));
        memberList.add(member);
      }
      in.close();

    } catch (Exception e) {
      System.out.println("회원 정보를 읽는 중 오류 발생 !");
    }
  }

  // 회원정보 출력
  private void saveMember() {
    try {
      FileOutputStream out = new FileOutputStream("member.data");

      // 저정할 데이터의 개수를 먼저 출력한다.
      int size = memberList.size();
      out.write(size >> 8);
      out.write(size);

      for (Member member : memberList) {
        int no = member.getNo();
        out.write(no >> 24);
        out.write(no >> 16);
        out.write(no >> 8);
        out.write(no);

        byte[] bytes = member.getName().getBytes("UTF-8");
        // 출력할 바이트의 개수를 2 바이트로 표시한다.
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        // 문자열의 바이트를 출력한다.
        out.write(bytes);

        bytes = member.getEmail().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getPassword().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        char gender = member.getGender();
        out.write(gender >> 8);
        out.write(gender);
      }
      out.close();
    } catch (Exception e) {
      System.out.println("회원 정보를 가져오는 중 오류 발생!");
    }
  }

  private void loadBoard() {
    try {
      FileInputStream in = new FileInputStream("member.data");
      int size = in.read() << 8;
      size |= in.read();

      byte[] buf = new byte[1000];

      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setName(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setEmail(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setPassword(new String(buf, 0, length, "UTF-8"));

        member.setGender((char) (in.read() << 8 | in.read()));
        memberList.add(member);
      }
      in.close();

    } catch (Exception e) {
      System.out.println("회원 정보를 읽는 중 오류 발생 !");
    }
  }

  // 게시글 출력
  private void saveBoard() {
    try {
      FileOutputStream out = new FileOutputStream("board.data");

      // 저정할 데이터의 개수를 먼저 출력한다.
      int size = memberList.size();
      out.write(size >> 8);
      out.write(size);

      for (Member member : memberList) {
        int no = member.getNo();
        out.write(no >> 24);
        out.write(no >> 16);
        out.write(no >> 8);
        out.write(no);

        byte[] bytes = member.getName().getBytes("UTF-8");
        // 출력할 바이트의 개수를 2 바이트로 표시한다.
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        // 문자열의 바이트를 출력한다.
        out.write(bytes);

        bytes = member.getEmail().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getPassword().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        char gender = member.getGender();
        out.write(gender >> 8);
        out.write(gender);
      }
      out.close();
    } catch (Exception e) {
      System.out.println("회원 정보를 가져오는 중 오류 발생!");
    }
  }


}
