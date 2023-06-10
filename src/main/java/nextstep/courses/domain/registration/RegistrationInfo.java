package nextstep.courses.domain.registration;

import java.time.LocalDateTime;

public class RegistrationInfo {

  private Long id;

  private LocalDateTime createdDate = LocalDateTime.now();

  private LocalDateTime updatedDate;

  public RegistrationInfo(Long id, LocalDateTime createdDate, LocalDateTime updatedDate) {
    this.id = id;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
  }
}
