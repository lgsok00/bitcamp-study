package bitcamp.util;

import java.util.Scanner;

public class Prompt {

  private Scanner scanner;

  // default constructor 정의
  public Prompt() {
    this.scanner = new Scanner(System.in);
  }

  // 다른 입력 도구와 연결한다면
  public Prompt(InputStream in) {
    this.scanner = new Scanner(in);
  }

  // Value Object 추가
  public String inputString(String title, Object...args) {
    System.out.print(title, args);
    return scanner.nextLine();
  }

  public static int inputInt(String title, Object...args) {
    return Integer.parseInt(inputString(title, args));
  }

  public static void close() {
    scanner.close();
  }
}