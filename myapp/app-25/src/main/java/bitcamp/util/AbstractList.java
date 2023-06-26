package bitcamp.util;

public abstract class AbstractList<E> implements List<E> {

  protected int size;

  @Override
  public int size() {
    return this.size;
  }

  protected boolean isValid(int index) {
    return index >= 0 && index < this.size;
  }

  // 스태틱 메서드 -> 바깥 클래스의 인스턴스 주소를 받을 필드와 생성자가 존재하지 않음
  // static void m1() {
  // class X {
  // }
  // }

  // 인스턴스 메서드 -> 바깥 클래스의 인스턴스 주소를 받을 필드와 생성자가 존재
  // void m2() {
  // class Y {
  // }
  // }

  @Override
  public Iterator<E> iterator() {
    // 방법 1 : top level class
    // return new ListIterator<>(this);

    // 방법 2 : static nested class
    // return new ListIterator2<>(this);

    // 방법 3 : non-static nested class
    // return this.new ListIterator3<>();
    // 위 문장은 컴파일러가 return new ListIterator3<>(this) 문장으로 변경한다.

    // 방법 4 : local class
    // class ListIterator4<T> implements Iterator<T> {
    // // 여기 눈에는 안보이지만, 바깥 클래스의 인스턴스 주소를 받는 생성자와
    // // 그 주소를 저장할 인스턴스 필드가 자동으로 추가된다.
    // int cursor;
    //
    // @Override
    // public boolean hasNext() {
    // // 바깥 클래스 명.this
    // return cursor < AbstractList.this.size();
    // }
    //
    // @SuppressWarnings("unchecked")
    // @Override
    // public T next() {
    // // 바깥 클래스 명.this
    // return (T) AbstractList.this.get(cursor++);
    // }
    // }
    // return new ListIterator4<>();
    // 위 문장은 컴파일러가 return ListIterator4<>(this) 문장으로 변경한다.

    // 방법 5 : anonymous class
    return new Iterator<E>() {
      // 여기 눈에는 안보이지만, 컴파일러가 바깥 클래스의 인스턴스 주소를 받을 생성자와 필드를 추가한다.
      int cursor;

      @Override
      public boolean hasNext() {
        return cursor < AbstractList.this.size();
      }

      @Override
      public E next() {
        return AbstractList.this.get(cursor++);
      }
    };
  }

  // ListIterator 에서 복사 -> ListIterator2
  // static nested class
  static class ListIterator2<T> implements Iterator<T> {

    // 이 객체가 목록에서 값을 꺼낼 수 있도록
    // 목록 객체 주소를 보관한다.
    List<T> list;

    // 어느 인덱스의 항목을 꺼낼 것인지 그 인덱스를 보관한다.
    int cursor;

    public ListIterator2(List<T> list) {
      this.list = list;
    }

    @Override
    public boolean hasNext() {
      return cursor < list.size();
    }

    @Override
    public T next() {
      return list.get(cursor++);
    }
  }

  // non-static nested class = inner class
  class ListIterator3<T> implements Iterator<T> {

    // 바깥 클래스의 인스턴스 주소를 받아서 보관할 인스턴스 변수가 자동으로 추가된다.
    // List<E> list;

    // 어느 인덱스의 항목을 꺼낼 것인지 그 인덱스를 보관한다.
    int cursor;

    // 바깥 클래스의 인스턴스 주소를 받는 생성자가 자동으로 추가된다.
    // public ListIterator3(List<E> list) {
    // this.list = list;
    // }

    @Override
    public boolean hasNext() {
      // 바깥 클래스 명.this
      return cursor < AbstractList.this.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T next() {
      // 바깥 클래스 명.this
      return (T) AbstractList.this.get(cursor++);
    }
  }
}
