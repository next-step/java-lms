package nextstep.courses.infrastructure;


import nextstep.courses.domain.ApprovalState;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class StudentRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Student student = new Student(1L, 1L);
        int count = studentRepository.save(student);
        assertThat(count).isEqualTo(1);

        List<Student> students = studentRepository.findBySessionId(1L);
        assertThat(students).contains(student);

        student = studentRepository.findById(1L);
        assertThat(student.isApproved()).isFalse();

        studentRepository.updateApprovalState(1L, 1L, ApprovalState.APPROVAL);
        student = studentRepository.findById(1L);

        assertThat(student.isApproved()).isTrue();
        LOGGER.debug("Students: {}", students);
    }
}
