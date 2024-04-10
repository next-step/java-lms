package nextstep.courses.domain.user;

import nextstep.courses.domain.BaseTime;

import java.util.Objects;

public class User extends BaseTime {

  public static final String USER_INFO_IS_INCORRECT = "유저 정보가 올바르지 않습니다. 다시 확인해주세요. name: %s, phone: %s";

  private Long id;
  private String name;
  private String phoneNumber;

  public User(Long id, String name, String phoneNumber) {
    valid(name, phoneNumber);
    this.id = id;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  private void valid(String name, String phoneNumber) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(String.format(USER_INFO_IS_INCORRECT, name, phoneNumber));
    }

    if (phoneNumber == null || phoneNumber.isBlank()) {
      throw new IllegalArgumentException(String.format(USER_INFO_IS_INCORRECT, name, phoneNumber));
    }
  }

  public Long getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(phoneNumber, user.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, phoneNumber);
  }
}
