package nextstep.courses.infrastructure;

import nextstep.courses.domain.coverImage.CoverImage;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionGatheringStatus;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.course.CourseTest.COURSE;
import static nextstep.courses.domain.coverImage.ImageType.PNG;
import static nextstep.courses.domain.session.PeriodTest.PERIOD_OF_SESSION;
import static nextstep.courses.domain.session.SessionTypeTest.PAID_SESSION_TYPE;
import static nextstep.users.domain.NsUserTest.ZIPJIGI;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("강의 저장 테스트")
    void testSave() {
        Session savedSession = sessionRepository.save(new Session("자바 강의", "자바 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, new CoverImage("이미지", 1024 * 1024, 300, 200, PNG), COURSE));
        Session foundSession = sessionRepository.findById(savedSession.getId());

        assertThat(foundSession).isNotNull();
        assertThat(savedSession.getId()).isEqualTo(foundSession.getId());
    }

    @Test
    @DisplayName("강의 상태 변경 테스트")
    void testUpdateStatus() {
        Session savedSession = sessionRepository.save(new Session("자바 강의", "자바 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, new CoverImage("이미지", 1024 * 1024, 300, 200, PNG), COURSE));
        savedSession.updateStatusAs(SessionStatus.ON_GOING);
        Session updatedSession = sessionRepository.update(savedSession);
        Session foundSession = sessionRepository.findById(updatedSession.getId());

        assertThat(updatedSession.getSessionStatus()).isEqualTo(foundSession.getSessionStatus());
    }

    @Test
    @DisplayName("강의 모집 상태 변경 테스트")
    void testUpdateGatheringStatus() {
        Session savedSession = sessionRepository.save(new Session("자바 강의", "자바 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, new CoverImage("이미지", 1024 * 1024, 300, 200, PNG), COURSE));
        savedSession.updateSessionGatheringStatusAs(SessionGatheringStatus.GATHERING);
        Session updatedSession = sessionRepository.update(savedSession);
        Session foundSession = sessionRepository.findById(updatedSession.getId());

        assertThat(updatedSession.getSessionGatheringStatus()).isEqualTo(foundSession.getSessionGatheringStatus());
    }

    @Test
    @DisplayName("수강 신청 테스트")
    void testEnroll() {
        Session savedSession = sessionRepository.save(new Session("자바 강의", "자바 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, new CoverImage("이미지", 1024 * 1024, 300, 200, PNG), COURSE));
        savedSession.updateSessionGatheringStatusAs(SessionGatheringStatus.GATHERING);
        savedSession.enroll(ZIPJIGI, new Payment("p1", savedSession.getId(), ZIPJIGI.getId(), 100L));
        Session updatedSession = sessionRepository.update(savedSession);
        Session foundSession = sessionRepository.findById(updatedSession.getId());

        assertThat(updatedSession.getEnrolledUsers()).isEqualTo(foundSession.getEnrolledUsers());
    }
}