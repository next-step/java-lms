package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.BaseTime;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class Enrollment extends BaseTime {

  public static final String REGISTER_IS_ONLY_POSSIBLE_IN_PROGRESS_STATUS = "수강신청은 강의 상태가 모집 중일 때만 가능합니다. sessionId: %s, status: %s";
  public static final String SESSION_AMOUNT_IS_NOT_CORRECT = "수강료가 일치하지 않습니다. input: %s, expected: %s";

  private Long id;
  private NsUser user;

  private Enrollment(Long id, NsUser user) {
    this.id = id;
    this.user = user;
  }

  public static Enrollment register(Long id, NsUser user, Session session) {
    validSessionStatus(session);
    validSessionRegisterPossible(session);
    return new Enrollment(id, user);
  }

  public Long getId() {
    return id;
  }

  public NsUser getUser() {
    return user;
  }

  private static void validSessionStatus(Session session) {
    if (!session.checkRegisterPossibleStatus()) {
      throw new IllegalArgumentException(String.format(REGISTER_IS_ONLY_POSSIBLE_IN_PROGRESS_STATUS, session.getId(), session.getSessionStatus()));
    }

    // 결제가 완료되었다고 판단하고, 유료강의 수강 신청 로직 작성하기
//    if (!session.checkPayAmount(payAmount)) {
//      throw new IllegalArgumentException(String.format(SESSION_AMOUNT_IS_NOT_CORRECT, payAmount, session.getSessionAmount()));
//    }
  }

  private static void validSessionRegisterPossible(Session session) {
    session.addStudentCount();
  }
}
