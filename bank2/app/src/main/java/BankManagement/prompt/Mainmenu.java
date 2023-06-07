package BankManagement.prompt;

import java.util.Scanner;

import BankManagement.util.openAccount;

public class Mainmenu {

  // static 메소드 printTitle 선언
  public static void printTitle() {
    System.out.println("============================");
    System.out.println("은행 입출금 관리 시스템");
    System.out.println("============================");
  }

  // static 메소드 menu 선언
  public static void menu() {
    System.out.println("1. 계좌 개설");
    System.out.println("2. 입금");
    System.out.println("3. 출금");
    System.out.println("4. 잔액 조회");
    System.out.println("5. 종료");
    System.out.print("원하시는 메뉴를 선택하세요... >");
  }

  // static 메소드 startBank 선언
  public static void startBank(Scanner scanner, int choice) {
    // while 문 활용
    while (choice != 5) {

      choice = scanner.nextInt();
      scanner.nextLine();

      // switch 문 활용
      switch (choice) {
        case 1:
          System.out.println("계좌 개설 메뉴를 선택하셨습니다.");
          openAccount.openAcc();
          break;
        case 2:
          System.out.println("입금 메뉴를 선택하셨습니다.");
          break;
        case 3:
          System.out.println("출금 메뉴를 선택하셨습니다.");
          break;
        case 4:
          System.out.println("잔액 조회 메뉴를 선택하셨습니다.");
          break;
        case 5:
          System.out.println("프로그램을 종료합니다.");
          break;
        default:
          System.out.println("잘못된 메뉴 선택입니다. 다시 입력해주세요.");
      }
    }
  }
}
