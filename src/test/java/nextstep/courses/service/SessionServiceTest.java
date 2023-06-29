package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.registration.Student;
import nextstep.courses.domain.registration.StudentRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static nextstep.Fixtures.*;
import static nextstep.courses.domain.registration.SessionRegistrationMother.aSessionRegistration;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SessionServiceTest {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @DisplayName("Session 저장")
    @Test
    void save() {
        sessionService.save(aSession().build());
        Session foundSession = sessionRepository.findById(1L);
        assertThat(foundSession.getId()).isNotNull();
    }

    @DisplayName("Session 조회")
    @Test
    void findById() {
        sessionRepository.save(aSession().withId(1L).build());
        Session foundSession = sessionService.findById(1L);
        assertThat(foundSession.getId()).isEqualTo(1L);
    }

    @DisplayName("Session(강의) 수강등록")
    @Test
    void register() {
        Session session = aSession().withId(1L).build();
        long sessionId = sessionRepository.save(session);

        Session foundSession = sessionRepository.findById(sessionId);
        sessionService.register(foundSession.getId(), NsUserTest.JAVAJIGI);
        sessionService.register(foundSession.getId(), NsUserTest.SANJIGI);

        List<Student> students = studentRepository.findAllBySessionId(sessionId);
        assertThat(students).hasSize(2);
    }

    @DisplayName("초과 등록")
    @Test
    void register_실패_인원초과() {
        Session session = aSession().withId(1L)
                .withSessionRegistration(aSessionRegistration().build())
                .build();

        long sessionId = sessionRepository.save(session);
        studentRepository.save(new Student(NsUserTest.JAVAJIGI.getId(), sessionId));
        studentRepository.save(new Student(NsUserTest.SANJIGI.getId(), sessionId));

        List<Student> students = studentRepository.findAllBySessionId(sessionId);

        assertThat(students).hasSizeGreaterThan(session.getMaxUserCount());

    }

    @DisplayName("중복 수강신청")
    @Test
    void register_중복신청() {
        Session session = aSession().withId(1L).withSessionRegistration(
                aSessionRegistrationBuilder().withStudents(
                        aStudentsBuilder()
                                .build()
                ).build()
        ).build();

        long sessionId = sessionService.save(session);
        sessionService.register(sessionId, NsUserTest.JAVAJIGI);

        List<Student> students = studentRepository.findAllBySessionId(sessionId);

        assertThat(students.contains(new Student(NsUserTest.JAVAJIGI.getId(), sessionId))).isTrue();

    }
}
