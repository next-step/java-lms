package nextstep.users.infrastructure.entity;

import java.time.LocalDateTime;
import nextstep.courses.domain.BaseTimeEntity;
import nextstep.users.domain.NsUser;


public class NsUserEntity extends BaseTimeEntity {

  private Long id;

  private String userId;

  private String password;

  private String name;

  private String email;

  public NsUserEntity() {
  }

  /**
   * 주 생성자
   */
  public NsUserEntity(Long id, String userId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.userId = userId;
    this.password = password;
    this.name = name;
    this.email = email;
  }

  public NsUser toDomain() {
    return new NsUser(id, userId, password, name, email, createdAt, updatedAt);
  }

  public Long getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

}
