package nextstep.courses.infrastructure;

import nextstep.courses.domain.coverImage.CoverImage;
import nextstep.courses.domain.coverImage.CoverImageRepository;
import nextstep.courses.domain.session.*;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

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
    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, new JdbcEnrollmentRepository(jdbcTemplate));
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("강의 저장 테스트")
    void testSave() {
        CoverImage savedCoverImage = coverImageRepository.save(new CoverImage("이미지", 1024 * 1024, 300, 200, PNG));
        Session savedSession = sessionRepository.save(new Session("자바 강의", "자바 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, new CoverImages(List.of(savedCoverImage)), COURSE));
        Session foundSession = sessionRepository.findById(savedSession.getId());

        assertThat(foundSession).isNotNull();
        assertThat(savedSession.getId()).isEqualTo(foundSession.getId());
    }

    @Test
    @DisplayName("강의 상태 변경 테스트")
    void testUpdateStatus() {
        CoverImage savedCoverImage = coverImageRepository.save(new CoverImage("이미지", 1024 * 1024, 300, 200, PNG));
        Session savedSession = sessionRepository.save(new Session("자바 강의", "자바 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, new CoverImages(List.of(savedCoverImage)), COURSE));
        savedSession.updateStatusAs(SessionStatus.ON_GOING);
        Session updatedSession = sessionRepository.update(savedSession);
        Session foundSession = sessionRepository.findById(updatedSession.getId());

        assertThat(updatedSession.getSessionStatus()).isEqualTo(foundSession.getSessionStatus());
    }

    @Test
    @DisplayName("강의 모집 상태 변경 테스트")
    void testUpdateGatheringStatus() {
        CoverImage savedCoverImage = coverImageRepository.save(new CoverImage("이미지", 1024 * 1024, 300, 200, PNG));
        Session savedSession = sessionRepository.save(new Session("자바 강의", "자바 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, new CoverImages(List.of(savedCoverImage)), COURSE));
        savedSession.updateSessionGatheringStatusAs(SessionGatheringStatus.GATHERING);
        Session updatedSession = sessionRepository.update(savedSession);
        Session foundSession = sessionRepository.findById(updatedSession.getId());

        assertThat(updatedSession.getSessionGatheringStatus()).isEqualTo(foundSession.getSessionGatheringStatus());
    }

    @Test
    @DisplayName("수강 신청 테스트")
    void testEnroll() {
        CoverImage savedCoverImage = coverImageRepository.save(new CoverImage("이미지", 1024 * 1024, 300, 200, PNG));
        Session savedSession = sessionRepository.save(new Session("자바 강의", "자바 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, new CoverImages(List.of(savedCoverImage)), COURSE));
        savedSession.updateSessionGatheringStatusAs(SessionGatheringStatus.GATHERING);
        savedSession.enroll(ZIPJIGI, new Payment("p1", savedSession.getId(), ZIPJIGI.getId(), 100L));
        Session updatedSession = sessionRepository.update(savedSession);
        Session foundSession = sessionRepository.findById(updatedSession.getId());

        assertThat(enrolledUser(updatedSession)).isEqualTo(enrolledUser(foundSession));
    }

    private List<NsUser> enrolledUser(Session session) {
        return session.getEnrollments()
                .stream()
                .map(Enrollment::getEnrolledUser)
                .collect(Collectors.toList());
    }
}