package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;

public class MemberList {

  private static final int DEFAULT_SIZE = 100;
  private Member[] members = new Member[DEFAULT_SIZE];
  private int length;

  public void add(Member m) {
    if (this.length == members.length) {
      increase();
    }
    this.members[this.length++] = m;
  }

  private void increase() {
    Member[] arr = new Member[members.length + (members.length / 2)];

    for (int i = 0; i < members.length; i++) {
      arr[i] = members[i];
    }
    members = arr;
  }

  public Member[] list() {
    // 리턴할 값을 담을 배열을 생성
    Member[] arr = new Member[this.length];

    // 원본 배열에서 입력된 인스턴스 주소를 꺼내
    // 새 배열에 담는다.
    for (int i = 0; i < this.length; i++) {
      arr[i] = members[i];
    }
    // 새 배열을 리턴한다.
    return arr;
  }

  public Member get(int no) {
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  public boolean delete(int no) {
    int deletedIndex = indexOf(no);
    if (deletedIndex == -1) {
      return false;
    }
    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.members[i] = this.members[i + 1];
    }
    this.members[--this.length] = null;
    return true;
  }

  private int indexOf(int memberNo) {
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }
}
