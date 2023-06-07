package BankManagement.util;

import java.util.Scanner;

import BankManagement.prompt.Mainmenu;

public class openAccount {
  static Scanner scanner = new Scanner(System.in);
  static int choice = 0;
  static final int MAX_SIZE = 100;

  // 배열 활용
  static String[] acc = new String[MAX_SIZE];
  static String[] name = new String[MAX_SIZE];
  static int[] password = new int[MAX_SIZE];
  static int[] initialDeposit = new int[MAX_SIZE];

  // static 메소드 openAccount 선언
  public static void openAcc() {

    // 반복문 활용
    for (int i = 0; i < MAX_SIZE; i++) {

      System.out.print("계좌번호 : ");
      acc[i] = scanner.next();

      System.out.print("이름 : ");
      name[i] = scanner.next();

      System.out.print("비밀번호 : ");
      password[i] = scanner.nextInt();

      // 초기 입금액 입력
      System.out.print("초기 입금액을 입력하세요: ");
      initialDeposit[i] = scanner.nextInt();
      scanner.nextLine();

      System.out.println("============================");
      System.out.printf("계좌번호 : %s\n", acc[i]);
      System.out.printf("이름 : %s\n", name[i]);
      System.out.printf("비밀번호 : %d\n", password[i]);
      System.out.printf("초기 입금액 : %d\n", initialDeposit[i]);
      System.out.printf("%s님 계좌 개설이 완료되었습니다.\n", name[i]);
      System.out.println("============================");

      Mainmenu.menu();
      scanner.next();
      Mainmenu.startBank(scanner, choice);
    }
  }
}