package nextstep.courses.service;

import nextstep.courses.dto.EnrolmentInfo;
import nextstep.courses.domain.session.PayType;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.repository.SessionRepository;
import nextstep.courses.domain.session.repository.StudentRepository;
import nextstep.courses.domain.session.student.Student;

import static nextstep.courses.domain.session.PayType.*;

public class PaySessionService implements SessionService {

    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;

    public PaySessionService(SessionRepository sessionRepository, StudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean supports(PayType payType) {
        return PAY.equals(payType);
    }

    @Override
    public void enroll(EnrolmentInfo enrolmentInfo) {
        Session session = sessionRepository.findPaySessionById(enrolmentInfo.getSessionId())
            .orElseThrow(() -> new IllegalArgumentException("일치하는 강의가 없습니다. 강의 아이디 :: " + enrolmentInfo.getSessionId()));

        Student student = session.enroll(enrolmentInfo);
        studentRepository.save(student);
    }
}
