package bitcamp.util;

// caller : Menu
// Callee : Menu 를 처리할 객

public interface ActionListener {

  // 사용자가 Menu 를 선택 했을 때 호출된다.
  void service(BreadcrumbPrompt prompt);
}
