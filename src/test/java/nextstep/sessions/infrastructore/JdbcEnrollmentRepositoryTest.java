package nextstep.sessions.infrastructore;

import nextstep.sessions.domain.Enrollment;
import nextstep.sessions.domain.EnrollmentState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@JdbcTest
class JdbcEnrollmentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;

    @BeforeEach
    void setUp() {
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
    }

    @Test
    void save() {
        long id = enrollmentRepository.save(new Enrollment(1L, 2L, EnrollmentState.AUTO_APPROVAL, LocalDateTime.now()));
        assertThat(id).isEqualTo(3L); // 더미 데이터 2건
    }

    @Test
    void saveAll() {
        List<Enrollment> data = List.of(
                new Enrollment(1L, 3L, EnrollmentState.AUTO_APPROVAL, LocalDateTime.now()),
                new Enrollment(1L, 4L, EnrollmentState.AUTO_APPROVAL, LocalDateTime.now())
        );

        assertThatNoException()
                .isThrownBy(() -> enrollmentRepository.saveAll(data));

        List<Enrollment> enrollments = enrollmentRepository.findBySessionId(1L);
        assertThat(enrollments).hasSize(3); // 더미 데이터 1건
    }

    @Test
    void findBySessionId() {
        List<Enrollment> enrollments = enrollmentRepository.findBySessionId(1L);
        assertThat(enrollments).hasSize(1);
    }

    @Test
    void findByNsUserId() {
        List<Enrollment> enrollments = enrollmentRepository.findByNsUserId(1L); // JAVAJIGI id
        assertThat(enrollments).hasSize(2);
    }

    @Test
    void findBy() {
        List<Enrollment> enrollments = enrollmentRepository.findBy(2L, 1L); // 2L 강의에 신청한 JAVAJIGI 정보 조회
        assertThat(enrollments).hasSize(1);
    }

    @Test
    void update() {
        List<Enrollment> enrollments = enrollmentRepository.findBy(2L, 1L); // 강의 (2L)에 수강 신청한 JAVAJIGI 조회
        Enrollment enrollment = enrollments.get(0);

        LocalDateTime updatedAt = LocalDateTime.now();
        Enrollment target = new Enrollment(3L, 2L, 1L, EnrollmentState.SELECTED, null, updatedAt);
        enrollment.update(target);

        int count = enrollmentRepository.update(enrollment);
        assertThat(count).isEqualTo(1);
    }

}
