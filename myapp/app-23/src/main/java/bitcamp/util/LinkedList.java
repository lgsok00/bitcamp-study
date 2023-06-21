package bitcamp.util;

public class LinkedList implements List {

  Node head; // 첫 노드 주소
  Node tail;
  int size;


  // LinkedList Test
  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.add(Integer.valueOf(100)); // index : 0
    list.add(Integer.valueOf(200));// index : 1
    list.add(Integer.valueOf(300));// index : 2
    list.add(Integer.valueOf(400));// index : 3
    list.add(Integer.valueOf(500));// index : 4

    print(list);

    // System.out.println(list.remove(Integer.valueOf(300)));
    // System.out.println(list.remove(Integer.valueOf(500)));
    // System.out.println(list.remove(Integer.valueOf(100)));
    // System.out.println(list.remove(Integer.valueOf(200)));
    // System.out.println(list.remove(Integer.valueOf(400)));
    // System.out.println(list.remove(Integer.valueOf(600)));

    System.out.println(list.remove(2)); // 300
    System.out.println(list.remove(3)); // 500
    System.out.println(list.remove(0)); // 100
    System.out.println(list.remove(0)); // 200
    System.out.println(list.remove(0)); // 400

    list.add(Integer.valueOf(1000));
    list.add(Integer.valueOf(2000));

    print(list);

    // System.out.println(list.retrieve(100));
    // System.out.println(list.retrieve(300));
    // System.out.println(list.retrieve(500));
    // System.out.println(list.retrieve(600));
  }

  static void print(LinkedList list) {
    Object[] arr = list.toArray();
    for (Object obj : arr) {
      System.out.print(obj);
      System.out.print(", ");
    }
    System.out.println();
  }


  @Override
  public boolean add(Object value) { // 어떤 타입을 받을지 모르므로 -> Objcet
    // 1. 새 노드를 생성한다.
    Node node = new Node();
    // 2. 새 노드에 값 저장
    node.value = value;

    if (head == null) {
      head = node; // 첫 노드의 주소값 head 에 저장
    } else if (this.tail != null) { // 3. 리스트의 마지막 노드에 새 노드를 연결
      this.tail.next = node; // tail 이 null 이 아닐 때 다음 노드를 가리킴
    }

    this.tail = node; // tail 이 null 이면 방금 만들어진 노드를 가리킴
    this.size++; // node 배열 사이즈
    return true;
  }

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[this.size];

    Node cursor = this.head;
    for (int i = 0; i < this.size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next; // 다음 노드로 이동
    }
    return arr;
  }

  @Override
  public Object get(int index) {
    if (!isValid(index)) {
      return null;
    }
    Node cursor = this.head;

    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    return cursor.value;
  }

  @Override
  public boolean remove(Object value) {
    Node prev = null;
    Node cursor = this.head;

    while (cursor != null) {
      if (cursor.value.equals(value)) {

        if (prev == null) {
          head = cursor.next;
          if (head == null) {
            tail = null;
          }

        } else if (cursor.next == null) {
          tail = prev;
          tail.next = null;
        } else {
          prev.next = cursor.next;
        }

        size--;
        cursor.next = null;
        cursor.value = null;

        return true;
      }
      prev = cursor;
      cursor = cursor.next;
    }
    return false;
  }

  @Override
  public Object remove(int index) {
    if (!isValid(index)) {
      return null;
    }
    // 삭제 하려는 값이 있는 노드까지 이동한다.
    Node prev = null;
    Node cursor = this.head;

    for (int i = 0; i < index; i++) {
      prev = cursor; // 다음 노드로 이동하기 전에 현재 커서가 가리키는 노드를 prev에 보관한다.
      cursor = cursor.next; // 커서를 다음 노드로 이동시킨다.
    }

    // 삭제할 값을 리턴할 수 있도록 보관한다.
    Object old = cursor.value;

    if (prev == null) {
      head = cursor.next;
      if (head == null) {
        tail = null;
      }
    } else if (cursor.next == null) {
      tail = prev;
      tail.next = null;
    } else {
      prev.next = cursor.next; // 현재 커서의 다음 노드를 현재 커서의 이전 노드와 연결
    }
    size--;
    cursor.next = null;
    cursor.value = null;
    return old;
  }

  @Override
  public int size() {
    return this.size;
  }

  private boolean isValid(int index) {
    return index >= 0 && index < this.size;
  }

  // static 중첩 클래스
  static class Node {
    // 자바 최상위 객체 (Object) 타입으로 선언
    Object value;
    // Node 가 정의되기 전 선언해도 문제 없음
    Node next;
  }
}
