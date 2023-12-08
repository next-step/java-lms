package nextstep.courses.service;

import nextstep.courses.domain.session.EnrolmentInfo;
import nextstep.courses.domain.session.PayType;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.repository.SessionRepository;
import nextstep.courses.domain.session.repository.StudentRepository;
import nextstep.courses.domain.session.student.Student;

import static nextstep.courses.domain.session.PayType.*;

public class FreeSessionService implements SessionService {

    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;

    public FreeSessionService(SessionRepository sessionRepository, StudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean supports(PayType payType) {
        return FREE.equals(payType);
    }

    @Override
    public void enroll(EnrolmentInfo enrolmentInfo) {
        Session session = sessionRepository.findBy(enrolmentInfo.sessionId())
            .orElseThrow(() -> new IllegalArgumentException("일치하는 강의가 없습니다. 강의 아이디 :: " + enrolmentInfo.sessionId()));

        Student student = session.enroll(enrolmentInfo);
        studentRepository.save(student);
    }
}
