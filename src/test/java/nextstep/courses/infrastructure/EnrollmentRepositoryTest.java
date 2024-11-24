package nextstep.courses.infrastructure;

import nextstep.courses.domain.cover.*;
import nextstep.courses.domain.session.*;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class EnrollmentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;
    private CoverImageRepository coverImageRepository;
    private SessionRepository sessionRepository;

    private Session session;
    private Long sessionId;

    @BeforeEach
    void setUp() {
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, enrollmentRepository, coverImageRepository);

        CoverImage coverImage = CoverImage.of("file.jpg", ImageSize.of(1000), ImageExtension.JPG.name(), ImageDimension.of(300, 200));
        SessionPeriod period = SessionPeriod.of(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        session = new FreeSession(
                1L, 1L, SessionBody.of("테스트 세션", period, List.of(coverImage)),
                SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.NOT_RECRUITING, EnrolledStudents.of(new HashSet<>()))
        );
        sessionRepository.save(session);
        sessionId = session.getId();
    }

    @DisplayName("사용자를 수강신청하고 수강신청된 사용자들을 조회할 수 있다.")
    @Test
    void enrollAndFindEnrolledStudentsBySessionId() {
        Student student = Student.of(NsUserTest.JAVAJIGI.getId(), sessionId);
        enrollmentRepository.save(sessionId, student);
        Student enrolledStudent = getSingleEnrolledStudent();

        assertThat(enrolledStudent)
                .usingRecursiveComparison()
                .isEqualTo(student);
    }

    @DisplayName("수강신청한 수강생의 수강 상태를 변경할 수 있다.")
    @Test
    void updateEnrollmentStatusTest() {
        Student student = Student.of(NsUserTest.JAVAJIGI.getId(), sessionId);

        session.enroll(student, null);
        enrollmentRepository.save(sessionId, student);

        Student enrolledStudent = getSingleEnrolledStudent();
        assertThat(enrolledStudent.getEnrollmentStatus()).isEqualTo(EnrollmentStatus.PENDING);

        session.approve(student);
        enrollmentRepository.updateEnrollmentStatus(sessionId, student);

        Student approvedStudent = getSingleEnrolledStudent();
        assertThat(approvedStudent.getEnrollmentStatus()).isEqualTo(EnrollmentStatus.APPROVED);

        session.reject(student);
        enrollmentRepository.updateEnrollmentStatus(sessionId, student);

        Student rejectedStudent = getSingleEnrolledStudent();
        assertThat(rejectedStudent.getEnrollmentStatus()).isEqualTo(EnrollmentStatus.REJECTED);
    }

    private Student getSingleEnrolledStudent() {
        Set<Student> enrolledStudents = enrollmentRepository.findEnrolledStudentsBySessionId(sessionId);
        assertThat(enrolledStudents).hasSize(1);
        return enrolledStudents.iterator().next();
    }

}