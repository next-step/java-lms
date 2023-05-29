package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionCoverImage {

  private Long id;
  private Long sessionId;
  private String url;

  private LocalDateTime createdAt = LocalDateTime.now();

  public SessionCoverImage(Long imageId, Long sessionId, String url) {
    this.id = imageId;
    this.sessionId = sessionId;
    this.url = url;
  }

  public Long getId() {
    return id;
  }
}
