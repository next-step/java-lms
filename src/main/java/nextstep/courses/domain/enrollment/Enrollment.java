package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.BaseTime;
import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.HashMap;
import java.util.Map;

public class Enrollment extends BaseTime {

  public static final String REGISTER_IS_ONLY_POSSIBLE_IN_PROGRESS_STATUS = "수강신청은 강의 상태가 모집 중일 때만 가능합니다. sessionId: %s, status: %s";
  public static final String SESSION_AMOUNT_IS_NOT_CORRECT = "수강료가 일치하지 않습니다. input: %s, expected: %s";

  private Long id;
  private NsUser user;
  private Long courseId;
  private Map<Session, Payment> registeredSessionInfo;
  private String registerFailReason;

  private Enrollment(Long id, NsUser user, Long courseId, Map<Session, Payment> registeredSessionInfo, String registerFailReason) {
    this.id = id;
    this.user = user;
    this.courseId = courseId;
    this.registeredSessionInfo = registeredSessionInfo;
    this.registerFailReason = registerFailReason;
  }

  public static Enrollment register(Long id, NsUser user, Long courseId, Map<Session, Integer> registerRequestInfo) {
    valid(registerRequestInfo);

    StringBuilder registerFailReason = new StringBuilder();
    Map<Session, Payment> registeredSessionInfo = new HashMap<>();

    for (Session session : registerRequestInfo.keySet()) {
      String failReason = checkSessionRegisterPossible(session);
      if (failReason != null) {
        registerFailReason.append(failReason);
        continue;
      }
      registeredSessionInfo.put(session, new Payment("1", session.getId(), user.getId(), session.getSessionAmount()));
    }

    return new Enrollment(id, user, courseId, registeredSessionInfo, registerFailReason.toString());
  }

  private static void valid(Map<Session, Integer> registeredSessionInfo) {
    registeredSessionInfo.entrySet().forEach(Enrollment::validateSessionAmount);
  }

  private static void validateSessionAmount(Map.Entry<Session, Integer> entry) {
    Session session = entry.getKey();
    Integer payAmount = entry.getValue();

    if (!session.checkRegisterPossibleStatus()) {
      throw new IllegalArgumentException(String.format(REGISTER_IS_ONLY_POSSIBLE_IN_PROGRESS_STATUS, session.getId(), session.getSessionStatus()));
    }

    if (!session.checkPayAmount(payAmount)) {
      throw new IllegalArgumentException(String.format(SESSION_AMOUNT_IS_NOT_CORRECT, payAmount, session.getSessionAmount()));
    }
  }

  private static String checkSessionRegisterPossible(Session session) {
    try {
      session.addStudentCount();
    } catch (Exception e) {
      return e.getMessage();
    }
    return null;
  }

  public Long getId() {
    return id;
  }

  public NsUser getUser() {
    return user;
  }

  public Long getCourseId() {
    return courseId;
  }

  public String getRegisterFailReason() {
    return registerFailReason;
  }
}
