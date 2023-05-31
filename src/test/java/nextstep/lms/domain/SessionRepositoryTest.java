package nextstep.lms.domain;

import nextstep.lms.infrastructure.JdbcSessionRepository;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private Session session;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        session = sessionSetUp();
    }

    private Session sessionSetUp() {
        LocalDate startDate = LocalDate.of(2023, 5, 20);
        LocalDate endDate = LocalDate.of(2023, 5, 25);
        Long imageCover = 1L;
        SessionPaidType sessionPaidType = SessionPaidType.FREE;
        int studentCapacity = 5;

        return Session.createSession(startDate, endDate, imageCover, sessionPaidType, studentCapacity);
    }

    @Test
    @DisplayName("수강 개설 및 저장 테스트")
    void saveTest() {
        int count = sessionRepository.save(session);

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("강의 이미지 커버 수정 테스트")
    void changeImageCoverTest() {
        Session findSession = sessionRepository
                .findById(1L)
                .orElseThrow(NotFoundException::new);

        Long imageCover = 2L;
        findSession.changeImageCover(imageCover);
        sessionRepository.changeImage(findSession);

        Session newSession = sessionRepository
                .findById(1L)
                .orElseThrow(NotFoundException::new);

        assertThat(newSession.getImageId())
                .isEqualTo(2L);
    }

    @Test
    @DisplayName("강의 상태 모집중으로 변경 테스트")
    void sessionStateChangeRecruitingTest() {
        Session findSession = sessionRepository
                .findById(1L)
                .orElseThrow(NotFoundException::new);

        findSession.changeProgressState();
        sessionRepository.changeSessionState(findSession);

        Session newSession = sessionRepository
                .findById(1L)
                .orElseThrow(NotFoundException::new);

        assertThat(newSession.getSessionState())
                .isEqualTo(SessionState.PROGRESS.toString());
    }

    @Test
    @DisplayName("학생 강의 수강 신청 테스트")
    void studentRegisterTest() {
        Session findSession = sessionRepository
                .findById(2L)
                .orElseThrow(NotFoundException::new);

        findSession.enroll(NsUserTest.JAVAJIGI);
        sessionRepository.updateRegisteredStudent(findSession);

        Session newSession = sessionRepository
                .findById(2L)
                .orElseThrow(NotFoundException::new);

        assertThat(newSession.getStudentCapacity().getRegisteredStudent())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("학생 강의 수강 취소 테스트")
    void studentCancelTest() {
        Session findSession = sessionRepository
                .findById(3L)
                .orElseThrow(NotFoundException::new);

        Student student = Student.init(NsUserTest.JAVAJIGI, findSession);
        findSession.cancel(student);
        sessionRepository.updateRegisteredStudent(findSession);

        Session newSession = sessionRepository
                .findById(3L)
                .orElseThrow(NotFoundException::new);

        assertThat(newSession.getStudentCapacity().getRegisteredStudent())
                .isEqualTo(4);
    }

    @Test
    @DisplayName("강의 유료 무료 타입 수정 기능")
    void studentTypeChangeTest() {
        Session findSession = sessionRepository
                .findById(1L)
                .orElseThrow(NotFoundException::new);

        findSession.changeSessionType(SessionPaidType.PAID);
        sessionRepository.changeSessionPaidType(findSession);

        Session newSession = sessionRepository
                .findById(1L)
                .orElseThrow(NotFoundException::new);

        assertThat(newSession.getSessionPaidType())
                .isEqualTo(SessionPaidType.PAID.toString());
    }

    @Test
    @DisplayName("강의 모집 상태 변경 기능")
    void sessionRecruitingTypeChangeTest() {
        Session findSession = sessionRepository
                .findById(1L)
                .orElseThrow(NotFoundException::new);

        findSession.changeStoppedState();
        sessionRepository.changeSessionRecruitingState(findSession);

        Session newSession = sessionRepository
                .findById(1L)
                .orElseThrow(NotFoundException::new);

        assertThat(newSession.getSessionRecruitingState())
                .isEqualTo(SessionRecruitingState.STOPPED.toString());
    }

}