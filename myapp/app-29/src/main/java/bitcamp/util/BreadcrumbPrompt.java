package bitcamp.util;

import java.util.Stack;

public class BreadcrumbPrompt extends Prompt {

  // Stack
  private Stack<String> breadcrumbs = new Stack<>();

  // 추가
  public void appendBreadcrumb(String title) {
    this.breadcrumbs.push(title);
  }

  // 삭제
  public void removeBreadcrumb() {
    this.breadcrumbs.pop();
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
    return this.inputString(titleBuilder.toString());
  }
}
