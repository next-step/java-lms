package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.List;

public class FreeSession extends Session {

  public FreeSession(Long courseId, LocalDate startDate, LocalDate endDate, List<SessionImage> images, SessionStatus status) {
    this(0L, courseId, startDate, endDate, images, status, List.of());
  }

  public FreeSession(Long id, Long courseId, LocalDate startDate, LocalDate endDate, List<SessionImage> images, SessionStatus status) {
    this(id, courseId, startDate, endDate, images, status, List.of());
  }

  public FreeSession(Long id, Long courseId, LocalDate startDate, LocalDate endDate, List<SessionImage> images, SessionStatus status, List<NsUser> students) {
    super(id, courseId, startDate, endDate, images, status, students);
  }

  @Override
  public String getType() {
    return "FREE";
  }

  @Override
  protected void validateAddition(final NsUser student) {
  }
}
