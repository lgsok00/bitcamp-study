package bitcamp.util;

public class MenuPrompt extends Prompt {

  private Queue commandHistory = new Queue();
  private Stack menus = new Stack();
  private Stack breadcrumbs = new Stack();

  // 추가
  public void appendBreadcrumb(String title, String menu) {
    this.breadcrumbs.push(title);
    this.menus.push(menu);
  }

  // 삭제
  public void removeBreadcrumb() {
    this.breadcrumbs.pop();
    this.menus.pop();
  }

  public void printMenu() {
    System.out.println(menus.peek());
  }

  // 명령 기록 출력
  public void printCommandHistory() {
    for (int i = 0; i < commandHistory.size(); i++) {
      System.out.println(commandHistory.get(i));
    }
  }

  // breadcrumb 메뉴 순서대로 출력
  public String inputMenu() {
    // StringBuilder 사용
    StringBuilder titleBuilder = new StringBuilder(); // 예) 메인/회원>
    for (int i = 0; i < this.breadcrumbs.size(); i++) {
      // 기존에 title 이 있다면 사이에 "/" 추가
      if (titleBuilder.length() > 0) {
        titleBuilder.append("/");
      }
      titleBuilder.append(this.breadcrumbs.get(i));
    }
    titleBuilder.append("> ");

    String command = null;

    while (true) {
      command = this.inputString(titleBuilder.toString());
      if (command.equals("history")) {
        printCommandHistory();
      } else if (command.equals("menu")) {
        this.printMenu();
      } else if (findMenuItem(command) == null) {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      } else {
        break;
      }
    }

    // 사용자가 입력한 명령어를 history 에 보관
    if (this.commandHistory.size() == 10) {
      // 명령어 목록은 최대 10개만 유지한다.
      // 10개를 초과할 경우 맨 앞의 기록을 삭제한다.
      this.commandHistory.poll();
    }
    // menu 에서 command 를 찾기
    String menuItem = findMenuItem(command);
    if (menuItem != null) {
      this.commandHistory.offer(titleBuilder.toString() + ": " + menuItem);
    } else {
      this.commandHistory.offer(command);
    }
    return command;
  }

  private String findMenuItem(String command) {
    String menuTitle = null;

    // menu 에서 command 에 해당하는 메뉴가 있다면 그 menu 이름을 return 하고
    // 없다면 null 을 return 한다.

    // Stack 맨 위 -> 현재 menu
    // 1) 현재 menu 를 알아낸다. menu Stack 에서 맨 마지막에 입력한 menu 를 조회(peek) 한다.
    String menu = (String) this.menus.peek();

    // 2) 꺼낸 menu 에서 해당 번호의 menu 를 찾는다.
    // menu - \n 로 구분되있음.
    String[] menuItems = menu.split("\n");
    for (String menuItem : menuItems) {
      // 입력한 menuItem 에 맞는 command 찾기
      // startsWith
      if (menuItem.startsWith(command)) {
        return menuItem;
      }
    }
    return menuTitle;
  }
}
