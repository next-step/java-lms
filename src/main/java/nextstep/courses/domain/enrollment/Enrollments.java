package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Enrollments {

  private static long INIT_NUM = 0;
  public static final String ALREADY_REGISTERED_USER = "해당 유저는 이미 수강신청을 완료했습니다. sessionId: %s, userId: %s";
  private List<Enrollment> enrollments = new ArrayList<>();

  private Enrollments(Session session, List<Enrollment> enrollments) {
    enrollments.forEach(enrollment -> validUserAlreadyRegistered(session, enrollment.getUser()));
    this.enrollments.addAll(enrollments);
  }

  private Enrollments(Session session, Enrollment enrollment) {
    validUserAlreadyRegistered(session, enrollment.getUser());
    this.enrollments.add(enrollment);
  }

  public static Enrollments register(Session session, NsUser user) {
    Enrollment enrollment = Enrollment.register(INIT_NUM++, user, session);
    return new Enrollments(session, enrollment);
  }

  public static Enrollments register(Session session, List<NsUser> users) {
    List<Enrollment> enrollments = users.stream()
        .map(user -> Enrollment.register(INIT_NUM++, user, session))
        .collect(Collectors.toList());
    return new Enrollments(session, enrollments);
  }

  public Enrollment get(int index) {
    return enrollments.get(index);
  }

  public Payment getPaymentInfo(Session session, NsUser user) {
    return new Payment(session.getId(), user.getId(), session.getSessionAmount());
  }

  public List<Payment> getAllPaymentInfos(Session session) {
    return enrollments.stream()
        .map(it -> new Payment(session.getId(), it.getUser().getId(), session.getSessionAmount()))
        .collect(Collectors.toList());
  }

  private void validUserAlreadyRegistered(Session session, NsUser user) {
    if (enrollments.stream().anyMatch(it -> it.getUser().getUserId().equals(user.getUserId()))) {
      throw new IllegalArgumentException(String.format(ALREADY_REGISTERED_USER, session.getId(), user.getUserId()));
    }
  }
}
