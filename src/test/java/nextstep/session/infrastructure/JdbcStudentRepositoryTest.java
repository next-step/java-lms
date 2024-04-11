package nextstep.session.infrastructure;

import nextstep.session.domain.Student;
import nextstep.session.domain.StudentRepository;
import nextstep.session.dto.StudentDto;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcStudentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @DisplayName("학생을 저장하고 조회할 수 있다.")
    @Test
    void saveAndFind() {
        // given
        long sessionId = 3L;
        Student student = new Student(sessionId, NsUserTest.SANJIGI);

        // when
        Long savedId = studentRepository.save(student.toDto());
        List<StudentDto> savedStudent = studentRepository.findBySessionId(sessionId);

        // then
        assertThat(savedStudent.size())
                .isEqualTo(1);
    }

    @DisplayName("다수의 학생을 저장하고 조회할 수 있다.")
    @Test
    void saveAndFindForMultiStudent() {
        // given
        long sessionId = 3L;
        Student student1 = new Student(sessionId, NsUserTest.SANJIGI);
        Student student2 = new Student(sessionId, NsUserTest.JAVAJIGI);

        // when
        Long savedId1 = studentRepository.save(student1.toDto());
        Long savedId2 = studentRepository.save(student2.toDto());
        List<StudentDto> savedStudent = studentRepository.findBySessionId(sessionId);

        // then
        assertThat(savedStudent.size())
                .isEqualTo(2);
    }

    @DisplayName("학생의 상태를 삭제로 변경한다.")
    @Test
    void updateDeleteStatus() {
        // given
        long sessionId = 3L;
        Student student = new Student(sessionId, NsUserTest.SANJIGI);

        // when
        Long savedId = studentRepository.save(student.toDto());
        int resultCount = studentRepository.updateDeleteStatus(sessionId, student.toDto().getUserId(), true);
        List<StudentDto> findStudent = studentRepository.findBySessionId(sessionId);

        // then
        assertThat(resultCount)
                .isEqualTo(1);
        assertThat(findStudent)
                .isEmpty();
    }
}

