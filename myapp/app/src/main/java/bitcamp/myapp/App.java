package bitcamp.myapp;

// 코드 본문에서 사용할 클래스가 어떤 패키지의 클래스인지 지정한다.
import java.util.Scanner;

public class App {
  // 키보드 스캐너 준비
  static Scanner scanner = new Scanner(System.in);
  static final int MAX_SIZE = 100; // final -> 상수 선언 (값을 바꿀 수 없음)

  static int[] no = new int[MAX_SIZE];
  static String[] name = new String[MAX_SIZE];
  static String[] email = new String[MAX_SIZE];
  static String[] password = new String[MAX_SIZE];
  static char[] gender = new char[MAX_SIZE];

  static int userId = 1; // 회원 번호
                         // 중간에 번호가 삭제되더라도 빈 곳을 메꾸지 않음
  static int length = 0; // 입력받은 회원정보 수

  public static void main(String[] args) {

    printTitle();

    // 화원 정보 등록
    while (length < MAX_SIZE) {
      inputMember();
      if (!promptContinue()) {
        break;
      }
    }

    printMembers();

    scanner.close();
  }

  static void printTitle() {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------");
  }

  static void inputMember() {
    System.out.print("이름? : ");
    name[length] = scanner.next(); // 사용자가 문자열을 입력하고 enter 입력 전까지 기다림

    System.out.print("이메일? : ");
    email[length] = scanner.next();

    System.out.print("암호? : ");
    password[length] = scanner.next();

    // 무효한 번호를 눌렀을 시
    // while 문 - 무한 루프
    // loop: 라벨
    loop: while (true) {
      System.out.println("성별 : ");
      System.out.println(" 1. 남자");
      System.out.println(" 2. 여자");
      System.out.print("> ");

      // 성별을 숫자로 받기
      String menuNo = scanner.next();
      scanner.nextLine(); // 입력 값 (token)을 읽고 난 후에 남아 있는 줄 바꿈 코드를 제거

      // switch 문
      switch (menuNo) {
        case "1":
          gender[length] = 'M';
          break loop;
        case "2":
          gender[length] = 'W';
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
    no[length] = userId++;
    length++;
  }

  static boolean promptContinue() {
    // 계속 할지 여부
    System.out.print("계속 하시겠습니까?(Y/n) ");
    String response = scanner.nextLine();
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      // response.equals("") 가 참이 아니고,
      // response.equals("Y") 가 참이 아니라면
      return false;
    }
    return true;
  }

  static void printMembers() {
    System.out.println("------------------------------");
    System.out.print("번호, 이름, 이메일, 성별\n");
    System.out.println("------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s, %c\n", no[i], name[i], email[i], gender[i]);
    }
  }
}