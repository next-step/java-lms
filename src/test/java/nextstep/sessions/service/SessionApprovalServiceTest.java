package nextstep.sessions.service;

import nextstep.sessions.domain.SessionBuilder;
import nextstep.sessions.domain.SessionCoverImage;
import nextstep.sessions.domain.SessionDuration;
import nextstep.sessions.domain.SessionPaymentType;
import nextstep.sessions.domain.SessionRecruitmentStatus;
import nextstep.sessions.domain.SessionRegistrationBuilder;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import nextstep.students.domain.Student;
import nextstep.students.domain.StudentRepository;
import nextstep.students.domain.Students;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@SpringBootTest
class SessionApprovalServiceTest {

    @Autowired
    private SessionApprovalService sessionApprovalService;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void test01() {
        long sessionId = 1L;
        sessionRepository.save(
                SessionBuilder.aSession()
                        .withId(sessionId)
                        .withDuration(new SessionDuration(LocalDateTime.now(),
                                LocalDateTime.now()))
                        .withCoverImage(SessionCoverImage.create("http://test.com/image"))
                        .withPaymentType(SessionPaymentType.PAID)
                        .with(SessionRegistrationBuilder.aRegistration()
                                .withStatus(SessionStatus.PREPARING)
                                .withRecruitmentStatus(SessionRecruitmentStatus.RECRUITING)
                                .withStudents(new Students())
                                .withStudentCapacity(10))
                        .withCreatedAt(LocalDateTime.now())
                        .build());
        studentRepository.save(
                new Student(NsUserTest.SANJIGI.getUserId(), sessionId)
        );


        sessionApprovalService.approve(NsUserTest.SANJIGI.getUserId(), sessionId);
    }

}
