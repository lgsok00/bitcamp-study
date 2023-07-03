package bitcamp.myapp.vo;

import java.io.Serializable;

public class Member implements Serializable, CsvObject {
  // 버전번호
  private static final long serialVersionUID = 1L;

  public static int userId = 1;

  public static final char MALE = 'M';
  public static final char FEMALE = 'W';

  private int no;
  private String name;
  private String email;
  private String password;
  private char gender;

  public Member() {
    this.no = userId++;
  }

  // 같은 기능을 수행하는 생성자가 위에 있다.
  // 다만 파라미터가 다를 뿐이다.
  // => "생성자 오버로딩(Overloading)"
  public Member(int no) {
    this.no = no;
  }

  public static Member fromCsv(String csv) {
    String[] values = csv.split(",");

    Member member = new Member(Integer.parseInt(values[0]));
    member.setName(values[1]);
    member.setEmail(values[2]);
    member.setPassword(values[3]);
    member.setGender(values[4].charAt(0));

    if (Member.userId <= member.getNo()) {
      Member.userId = member.getNo() + 1;
    }

    return member;
  }

  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%c", this.getNo(), this.getName(), this.getEmail(),
        this.getPassword(), this.getGender());
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) { // getClass() -> 현재 인스턴스의 클래스정보
      return false;
    }

    // 위 조건에서 this 가 가리키는 인스턴스의 클래스와
    // 파라미터 obj 가 가리키는 인스턴스의 클래스가
    // 같다고 결론이 났기 때문에 다음과 같이
    // obj 를 Member 타입으로 형변환한다.
    Member m = (Member) obj;

    if (this.getNo() != m.getNo()) {
      return false;
    }
    return true;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }
}
