package nextstep.courses.service;

import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;

@Service
public class SessionEnrollService {
  private CourseRepository courseRepository;

  public void enroll(Session session, Payment payment){
    session.enroll(payment.getAmount());
  }

}
