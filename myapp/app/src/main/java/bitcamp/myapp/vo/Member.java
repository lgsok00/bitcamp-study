package bitcamp.myapp.vo;

public class Member {
  private int no;
  private String name;
  private String email;
  private String password;
  private char gender;

  // 생성자가 존재하면 컴파일러는 기본 생성자를 만들지 않는다.
  // public Member() {}

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
