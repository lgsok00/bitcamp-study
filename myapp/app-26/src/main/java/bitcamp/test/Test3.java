package bitcamp.test;

import bitcamp.test.p1.A;

public class Test3 extends A {

  public static void main(String[] args) {
    A obj = new A();

    // obj.v1 = 100; // private 접근 불가!
    // obj.v2 = 200; // default 접근 불가!
    // obj.v3 = 300; // protected 접근 불가! <== 상속 받은 멤버가 아니다.
    obj.v4 = 400;
    // obj.m(); // 접근 불가! <== 상속 받으 멤버가 아니다.

    m2(); // 호출 가능! -> 같은 스태틱 멤버
    // m3(); // 호출 불가! -> m3 = 논 스태틱

    Test3 obj2 = new Test3();
    obj2.m3(); // 호출 가능! -> 인스턴스 주소 줘야함

    // obj2.v1 = 100; // 접근 불가! <== v1 = private
    // obj2.v2 = 200; // 접근 불가! <== v2 = default (같은 패키지X)
    obj2.v3 = 300; // 접근 가능! <== v3 = Test3 가 A 로부터 상속 받아서 만든 필드
                   // 자식 클래스가 접근 가능!
    obj2.v4 = 400;
    obj2.m(); // 자식 클래스가 상속 받아서 사용하는 멤버!
  }

  static void m2() {

  }

  void m3() {

  }
}
