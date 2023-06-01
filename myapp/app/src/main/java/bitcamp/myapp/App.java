package bitcamp.myapp;

// 코드 본문에서 사용할 클래스가 어떤 패키지의 클래스인지 지정한다.
import java.util.Scanner;

import javax.swing.text.DefaultEditorKit.DefaultKeyTypedAction;

public class App {
  public static void main(String[] args) {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------");

    // 키보드 스캐너 준비
    Scanner scanner = new Scanner(System.in);

    final int MAX_SIZE = 100; // final -> 상수 선언 (값을 바꿀 수 없음)
    int userId = 1; // 회원 번호
                    // 중간에 번호가 삭제되더라도 빈 곳을 메꾸지 않음
    int length = 0; // 입력받은 회원정보 수

    int[] no = new int[MAX_SIZE];
    String[] name = new String[MAX_SIZE];
    String[] email = new String[MAX_SIZE];
    String[] password = new String[MAX_SIZE];
    char[] gender = new char[MAX_SIZE];

    // 화원 정보 등록
    for (int i = 0; i < MAX_SIZE; i++) {

      System.out.print("이름? : ");
      name[i] = scanner.next(); // 사용자가 문자열을 입력하고 enter 입력 전까지 기다림

      System.out.print("이메일? : ");
      email[i] = scanner.next();

      System.out.print("암호? : ");
      password[i] = scanner.next();

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

        // if (menuNo.equals("1")) {
        // gender[i] = 'M';
        // break; // 무한 루프 이므로 break로 멈춰야 함.
        // } else if (menuNo.equals("2")) {
        // gender[i] = 'W';
        // break;
        // } else {
        // System.out.println("무효한 번호입니다.");
        // }

        // switch 문
        switch (menuNo) {
          case "1":
            gender[i] = 'M';
            break loop;
          case "2":
            gender[i] = 'W';
            break loop;
          default:
            System.out.println("무효한 번호입니다.");
        }
      }

      no[i] = userId++;
      length++;

      // 계속 할지 여부
      System.out.print("계속 하시겠습니까?(Y/n) ");
      scanner.nextLine(); // 이전 next() 를 실행한 후 남아있는 줄 바꿈 코드 제거
      String response = scanner.nextLine();
      if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
        // response.equals("") 가 참이 아니고,
        // response.equals("Y") 가 참이 아니라면
        break; // 반복 멈추는 명령
      }

    }

    System.out.println("------------------------------");
    System.out.print("번호, 이름, 이메일, 성별\n");
    System.out.println("------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s, %c\n", no[i], name[i], email[i], gender[i]);
    }
    scanner.close();
  }
}