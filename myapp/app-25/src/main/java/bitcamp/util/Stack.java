package bitcamp.util;

public class Stack<E> extends LinkedList<E> {

  public static void main(String[] args) {
    Stack<String> s = new Stack<>();
    s.push("홍길동");
    s.push("임꺽정");
    s.push("유관순");
    s.push("안중근");
    s.push("윤봉길");

    Iterator<String> iterator = s.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
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

  @Override
  public Iterator<E> iterator() {
    // Iterator 인터페이스에 따라 익명 클래스를 만듬
    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        // 거꾸로 뽑기
        return !Stack.this.empty();
      }

      @Override
      public E next() {
        return Stack.this.pop();
      }
    };
  }
}
