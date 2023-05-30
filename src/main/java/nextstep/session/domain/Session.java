package nextstep.session.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.domain.Course;
import nextstep.session.CannotApplySession;
import nextstep.users.domain.NsUser;

public class Session {

  private SessionDate sessionDate;

  private String imageUrl;

  private boolean isBilling;

private SessionApply sessionApply;

private List<NsUser> enrollmentUsers;

private List<Long> courcesIds;

  public Session(SessionDate sessionDate) {
    this.sessionDate = sessionDate;
  }

  public Session(SessionApply sessionApply) {
    this.sessionApply = sessionApply;
    enrollmentUsers = new ArrayList<>();
    courcesIds = new ArrayList<>();
  }

  void apply(NsUser user) {
    if(isApply(user)) {
      throw new CannotApplySession("이미 수강신청이 완료된 강의입니다.");
    }

    if (!sessionApply.isRecruiting()) {
      throw new CannotApplySession("강의 모집 중에만 수강 신청이 가능합니다.");
    }

    if (sessionApply.isMaxEnrollment()) {
      throw new CannotApplySession("수강 가능한 인원이 다 찼습니다.");
    }

    enrollmentUsers.add(user);
    sessionApply.enrollment();
  }

  private boolean isApply(NsUser user) {
    return enrollmentUsers.contains(user);
  }

  public void mapping(Long courseId) {
    courcesIds.add(courseId);
  }
}
