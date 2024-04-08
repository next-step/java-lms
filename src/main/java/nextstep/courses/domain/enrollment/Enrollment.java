package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.BaseTime;
import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;

public class Enrollment extends BaseTime {

  public static final String REGISTER_IS_ONLY_POSSIBLE_IN_PROGRESS_STATUS = "수강신청은 강의 상태가 모집 중일 때만 가능합니다. sessionId: %s, status: %s";
  public static final String SESSION_AMOUNT_IS_NOT_CORRECT = "수강료가 일치하지 않습니다. input: %s, expected: %s";

  private Long id;
  private Long userId;
  private Long courseId;
  private Session registeredSession;
  private Payment payment;

  private Enrollment(Long id, Long userId, Long courseId, Session session, Payment payment) {
    this.id = id;
    this.userId = userId;
    this.courseId = courseId;
    this.registeredSession = session;
    this.payment = payment;
  }

  public static Enrollment register(Long id, Long userId, Long courseId, Session session, Long payAmount) {
    valid(session, payAmount);
    Payment payment = new Payment("1", session.getId(), userId, payAmount);
    return new Enrollment(id, userId, courseId, session, payment);
  }

  private static void valid(Session session, Long payAmount) {
    if (!session.checkRegisterPossibleStatus()) {
      throw new IllegalArgumentException(String.format(REGISTER_IS_ONLY_POSSIBLE_IN_PROGRESS_STATUS, session.getId(), session.getSessionStatus()));
    }

    if (!session.checkPayAmount(payAmount)) {
      throw new IllegalArgumentException(String.format(SESSION_AMOUNT_IS_NOT_CORRECT, payAmount, session.getSessionAmount()));
    }
    session.addStudentCount();
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getCourseId() {
    return courseId;
  }

  public Payment getPayment() {
    return payment;
  }
}
