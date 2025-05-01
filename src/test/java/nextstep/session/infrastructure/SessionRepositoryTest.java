package nextstep.session.infrastructure;

import nextstep.payments.domain.PaidPaymentPolicy;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.session.domain.image.CoverImage;
import nextstep.session.domain.image.CoverImages;
import nextstep.session.domain.session.*;
import nextstep.session.domain.student.EnrolledStudent;
import nextstep.session.domain.student.EnrolledStudents;
import nextstep.session.domain.student.EnrollmentStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Duration duration = new Duration(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5));
        PaymentPolicy policy = new PaidPaymentPolicy(800_000, 10);
        Session session = new SessionBuilder()
                .id(1L)
                .title("TestSession")
                .duration(duration)
                .paymentPolicy(policy)
                .enrolledStudents(null)
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .build();

        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getTitle()).isEqualTo(savedSession.getTitle());
        assertThat(session.getSessionStatus()).isEqualTo(SessionStatus.PREPARING);
        assertThat(session.getRecruitmentStatus()).isEqualTo(RecruitmentStatus.OPEN);

        LOGGER.debug("Session: title={}", savedSession.getTitle());
    }

    @Test
    void crudWithRelatedEntity() {
        Long sessionId = 1L;

        Duration duration = new Duration(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5));
        PaymentPolicy policy = new PaidPaymentPolicy(800_000, 10);

        CoverImages coverImages =getCoverImages(sessionId);
        EnrolledStudents enrolledStudents = getEnrolledStudents(sessionId);

        Session session = new SessionBuilder()
                .id(sessionId)
                .title("TestSession")
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(policy)
                .enrolledStudents(enrolledStudents)
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .build();

        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(sessionId);
        assertThat(session.getTitle()).isEqualTo(savedSession.getTitle());
        assertThat(session.getSessionStatus()).isEqualTo(SessionStatus.PREPARING);
        assertThat(session.getRecruitmentStatus()).isEqualTo(RecruitmentStatus.OPEN);
        assertThat(savedSession.getCoverImages().size()).isEqualTo(1);
        assertThat(savedSession.getEnrolledStudents().getStudents().get(0).getStudentId()).isEqualTo(NsUserTest.JAVAJIGI.getId());
        assertThat(savedSession.getEnrolledStudents().getStudents().get(1).getStudentId()).isEqualTo(NsUserTest.SANJIGI.getId());

        LOGGER.debug("Session: title={}", savedSession.getTitle());
        LOGGER.debug("CoverImage: fileName={}", savedSession.getCoverImages().get(0).getFileName());
    }

    private EnrolledStudents getEnrolledStudents(Long sessionId) {
        List<EnrolledStudent> students = new ArrayList<>();
        students.add(new EnrolledStudent(NsUserTest.JAVAJIGI.getId(), EnrollmentStatus.APPROVED));
        students.add(new EnrolledStudent(NsUserTest.SANJIGI.getId(), EnrollmentStatus.APPROVED));

        EnrolledStudents enrolledStudents = new EnrolledStudents(sessionId, students);
        return enrolledStudents;
    }

    private CoverImages getCoverImages(Long sessionId) {
        CoverImage coverImage = new CoverImage.Builder()
                .id(1L)
                .fileName("test cover image")
                .fileSize(100)
                .imageFormat("jpg")
                .imageSize(300, 200)
                .sessionId(sessionId)
                .build();

        List<CoverImage> coverImages = new ArrayList<>();
        coverImages.add(coverImage);

        return new CoverImages(coverImages);
    }
}
