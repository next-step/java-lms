package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.List;

public class FreeSession extends Session {

  public FreeSession(Long courseId, LocalDate startDate, LocalDate endDate, List<SessionImage> images,
                     OpenStatus openStatus, RecruitStatus recruitStatus) {
    this(0L, courseId, startDate, endDate, images, openStatus, recruitStatus, List.of());
  }

  public FreeSession(Long id, Long courseId, LocalDate startDate, LocalDate endDate, List<SessionImage> images,
                     OpenStatus openStatus, RecruitStatus recruitStatus) {
    this(id, courseId, startDate, endDate, images, openStatus, recruitStatus, List.of());
  }

  public FreeSession(Long id, Long courseId, LocalDate startDate, LocalDate endDate, List<SessionImage> images, OpenStatus openStatus,
                     RecruitStatus recruitStatus, List<NsUser> students) {
    super(id, courseId, startDate, endDate, images, openStatus, recruitStatus, students);
  }

  @Override
  public String getType() {
    return "FREE";
  }

  @Override
  protected void validateAddition(final NsUser student) {
  }
}
