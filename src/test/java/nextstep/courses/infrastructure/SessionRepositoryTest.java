package nextstep.courses.infrastructure;

import nextstep.courses.domain.cover.*;
import nextstep.courses.domain.session.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private EnrollmentRepository enrollmentRepository;
    private CoverImageRepository coverImageRepository;

    private Session paidSession;
    private Session freeSession;

    @BeforeEach
    void setUp() {
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, enrollmentRepository, coverImageRepository);

        CoverImage coverImage = CoverImage.of("nextstep", ImageSize.of(1000), ImageExtension.JPG.name(), ImageDimension.of(300, 200));
        SessionPeriod period = SessionPeriod.of(LocalDateTime.now(), LocalDateTime.now().plusDays(7));


        paidSession = new PaidSession(1L, 1L, SessionBody.of("유료 세션", period, List.of(coverImage)),
                SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.NOT_RECRUITING, EnrolledStudents.of(new HashSet<>())), 10000L, 100);

        freeSession = new FreeSession(2L, 1L, SessionBody.of("무료 세션", period, List.of(coverImage)),
                SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.NOT_RECRUITING, EnrolledStudents.of(new HashSet<>())));
    }

    @DisplayName("유료 강의를 저장하고 조회할 수 있다.")
    @Test
    void saveAndFindPaidSession() {
        sessionRepository.save(paidSession);
        Optional<Session> foundSession = sessionRepository.findById(paidSession.getId());

        assertThat(foundSession).isPresent();
        assertThat(foundSession.get()).isInstanceOf(PaidSession.class);

        PaidSession retrievedSession = (PaidSession) foundSession.get();
        assertAll(
                () -> assertThat(retrievedSession.getId()).isEqualTo(paidSession.getId()),
                () -> assertThat(retrievedSession.getTitle()).isEqualTo(paidSession.getTitle()),
                () -> assertThat(retrievedSession.getFee()).isEqualTo(paidSession.getFee()),
                () -> assertThat(retrievedSession.getMaxEnrollments()).isEqualTo(paidSession.getMaxEnrollments()),
                () -> assertThat(retrievedSession.getProgressStatus()).isEqualTo(paidSession.getProgressStatus()),
                () -> assertThat(retrievedSession.getRecruitmentStatus()).isEqualTo(paidSession.getRecruitmentStatus())
        );
    }

    @DisplayName("무료 강의를 저장하고 조회할 수 있다.")
    @Test
    void saveAndFindFreeSession() {
        sessionRepository.save(freeSession);
        Optional<Session> foundSession = sessionRepository.findById(freeSession.getId());

        assertThat(foundSession).isPresent();
        assertThat(foundSession.get()).isInstanceOf(FreeSession.class);

        FreeSession retrievedSession = (FreeSession) foundSession.get();
        assertAll(
                () -> assertThat(retrievedSession.getId()).isEqualTo(freeSession.getId()),
                () -> assertThat(retrievedSession.getTitle()).isEqualTo(freeSession.getTitle()),
                () -> assertThat(retrievedSession.getFee()).isEqualTo(freeSession.getFee()),
                () -> assertThat(retrievedSession.getMaxEnrollments()).isEqualTo(freeSession.getMaxEnrollments()),
                () -> assertThat(retrievedSession.getProgressStatus()).isEqualTo(freeSession.getProgressStatus()),
                () -> assertThat(retrievedSession.getRecruitmentStatus()).isEqualTo(freeSession.getRecruitmentStatus())
        );
    }



}
