package BankManagement;

import java.util.Scanner;
import BankManagement.prompt.Mainmenu;

public class Mainpage {

  // static 변수 선언
  // 상수 활용
  static final int MAX_SIZE = 100;

  // 키보드 스캐너 준비
  static Scanner scanner = new Scanner(System.in);
  static int choice = 0;

  // 배열 활용
  static String[] acc = new String[MAX_SIZE];
  static String[] name = new String[MAX_SIZE];
  static int[] password = new int[MAX_SIZE];
  static int[] initialDeposit = new int[MAX_SIZE];

  public static void main(String[] args) {

    Mainmenu.printTitle();

    Mainmenu.menu();

    Mainmenu.startBank(scanner, choice);

    scanner.close();
  }
}