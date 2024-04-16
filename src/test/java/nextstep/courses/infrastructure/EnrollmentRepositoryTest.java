package nextstep.courses.infrastructure;

import nextstep.courses.domain.coverImage.CoverImage;
import nextstep.courses.domain.coverImage.CoverImageRepository;
import nextstep.courses.domain.session.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static nextstep.courses.domain.course.CourseTest.COURSE;
import static nextstep.courses.domain.coverImage.ImageType.PNG;
import static nextstep.courses.domain.session.PeriodTest.PERIOD_OF_SESSION;
import static nextstep.courses.domain.session.SessionTypeTest.PAID_SESSION_TYPE;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class EnrollmentRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;
    private SessionRepository sessionRepository;
    private CoverImageRepository coverImageRepository;
    private Session session;

    @BeforeEach
    void setUp() {
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, enrollmentRepository);
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);

        CoverImage savedCoverImage = coverImageRepository.save(new CoverImage("이미지", 1024 * 1024, 300, 200, PNG));
        session = sessionRepository.save(new Session("자바 강의", "자바 강의다", PAID_SESSION_TYPE, PERIOD_OF_SESSION, new CoverImages(List.of(savedCoverImage)), COURSE));
    }

    @Test
    @DisplayName("수강신청 저장 테스트")
    void testSave() {
        Enrollment savedEnrollment = enrollmentRepository.save(new Enrollment(session, SANJIGI));
        Enrollment foundEnrollment = enrollmentRepository.findById(savedEnrollment.getId());

        assertThat(foundEnrollment).isNotNull();
        assertThat(savedEnrollment.getId()).isEqualTo(foundEnrollment.getId());
    }

    @Test
    @DisplayName("수강신청 승인 변경 테스트")
    void testUpdate() {
        Enrollment savedEnrollment = enrollmentRepository.save(new Enrollment(session, SANJIGI));
        savedEnrollment.approve(JAVAJIGI);
        Enrollment updatedEnrollment = enrollmentRepository.update(savedEnrollment);
        Enrollment foundEnrollment = enrollmentRepository.findById(savedEnrollment.getId());

        assertThat(updatedEnrollment.getStatus()).isEqualTo(foundEnrollment.getStatus());
        assertThat(updatedEnrollment.getUpdatedAt()).isEqualTo(foundEnrollment.getUpdatedAt());
    }

}