package nextstep.students.infrastructure;

import nextstep.students.domain.Student;
import nextstep.students.domain.StudentApprovalType;
import nextstep.students.domain.StudentRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class StudentRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(StudentRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("Student 정보를 테이블에 저장하고 조회합니다.")
    void crud() {
        Student student = new Student(NsUserTest.JAVAJIGI.getUserId(), 1L);

        log.debug("STUDENT SAVE: {}", student);
        long generatedStudentId = studentRepository.save(student);

        Student savedStudent = studentRepository.findById(1L).get();
        log.debug("STUDENT READ: {}", savedStudent);
        List<Student> savedStudents = studentRepository.findAllBySessionId(student.getSessionId());
        log.debug("STUDENTS READ(SESSION_ID): {}", savedStudents);

        savedStudent.approved();
        studentRepository.update(savedStudent);
        Student updatedStudent = studentRepository.findById(1L).get();

        assertAll(
                () -> assertThat(generatedStudentId).isPositive(),
                () -> assertThat(savedStudent.getSessionId()).isEqualTo(student.getSessionId()),
                () -> assertThat(savedStudent.getUserId()).isEqualTo(student.getUserId()),
                () -> assertThat(savedStudent.getCreatedAt()).isEqualTo(student.getCreatedAt()),
                () -> assertThat(updatedStudent.getApprovalType()).isEqualTo(StudentApprovalType.APPROVED)
        );
    }

}
