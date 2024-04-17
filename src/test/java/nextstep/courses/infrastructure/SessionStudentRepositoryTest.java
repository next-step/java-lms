package nextstep.courses.infrastructure;

import nextstep.courses.domain.student.SessionStudent;
import nextstep.courses.domain.student.SessionStudents;
import nextstep.courses.infrastructure.engine.SessionStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.fixture.SessionStudentFixture.student;
import static nextstep.courses.domain.fixture.SessionStudentsFixture.students;
import static nextstep.courses.domain.student.StudentEnrollmentStatus.APPROVAL;
import static nextstep.courses.domain.student.StudentEnrollmentStatus.PENDING;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SessionStudentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionStudentRepository sessionStudentRepository;

    @BeforeEach
    void setUp() {
        sessionStudentRepository = new JdbcSessionStudentRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("save()")
    void save() {
        SessionStudent student = student(SESSION_ID, 1L);

        int count = sessionStudentRepository.save(student);

        assertThat(count).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"0", "1"})
    @DisplayName("updateAll()")
    void updateAll(int index) {
        // Given
        SessionStudent student1 = student(1L, SESSION_ID, 1L, PENDING);
        sessionStudentRepository.save(student1);

        SessionStudent student2 = student(2L, SESSION_ID, 2L, PENDING);
        sessionStudentRepository.save(student2);

        SessionStudents findStudents = students(student1, student2);
        findStudents.toApproveStatus();

        // When
        int[] affectedRows = sessionStudentRepository.updateAll(findStudents);

        // Then
        assertThat(affectedRows).hasSize(2);

        SessionStudents findStudentsAfterUpdateAll = sessionStudentRepository.findAllBySessionId(SESSION_ID);
        assertThat(findStudentsAfterUpdateAll.get().get(index).getEnrollmentStatus()).isEqualTo(APPROVAL);

    }

    @ParameterizedTest(name = "[{index}] List<SessionStudent>.get({0}).getNsUserId() == {1}L")
    @CsvSource(value = {"0:1", "1:2"}, delimiter = ':')
    @DisplayName("findAllBySessionId()")
    void findAllBySessionId(int index, Long nsUserId) {
        SessionStudent student1 = student(SESSION_ID, 1L);
        sessionStudentRepository.save(student1);

        SessionStudent student2 = student(SESSION_ID, 2L);
        sessionStudentRepository.save(student2);

        SessionStudents students = sessionStudentRepository.findAllBySessionId(SESSION_ID);

        assertThat(students.get().get(index).getNsUserId()).isEqualTo(nsUserId);
    }

    @Nested
    @DisplayName("SessionStudentRepository 학습 테스트")
    class SessionStudentRepositoryLearningTest {

        @Test
        @DisplayName("[성공] 리스트 조회 시 ROW 가 없는 경우 빈 리스트를 반환한다.")
        void 빈_리스트() {
            SessionStudents students = sessionStudentRepository.findAllBySessionId(9999L);
            assertThat(students.get()).isEmpty();
        }

    }

}
