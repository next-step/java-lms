package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionCoverImage {

  private Long imageId;
  private String url;

  private LocalDateTime createdAt = LocalDateTime.now();

  public SessionCoverImage(Long imageId, String url) {
    this.imageId = imageId;
    this.url = url;
  }
}
