package bitcamp.util;

public class Stack<E> extends LinkedList<E> {

  public static void main(String[] args) {
    Stack<String> s = new Stack<>();
    s.push("홍길동");
    s.push("임꺽정");
    s.push("유관순");
    s.push("안중근");
    s.push("윤봉길");

    System.out.println(s.pop());
    System.out.println(s.pop());
    System.out.println(s.pop());
    System.out.println(s.pop());
    System.out.println(s.pop());

    System.out.println(s.pop()); // null
  }

  public void push(E value) {
    // 목록 맨 끝에 추가한다.
    // 따로 만들 필요가 없다.
    // super 클래스에 있는 메서드를 이용하여 기능을 구현한다.
    this.add(value); // 상속 받은 메서드 = sub 클래스에서 사용할 수 있는 super 클래스의 메서드
  }

  public E pop() {
    if (this.empty()) {
      return null;
    }
    // 목록에서 제거 O
    return this.remove(this.size() - 1);
  }

  public E peek() {
    if (this.empty()) {
      return null;
    }
    // 목록에서 제거 X -> 조회
    return this.get(this.size() - 1);
  }

  public boolean empty() {
    return this.size() == 0;
  }
}
