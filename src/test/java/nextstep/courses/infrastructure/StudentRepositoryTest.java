package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.domain.student.ApprovalState;
import nextstep.courses.domain.student.Email;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.StudentName;
import nextstep.courses.domain.student.StudentType;
import nextstep.courses.entity.StudentEntity;
import nextstep.courses.infrastructure.impl.JdbcStudentEntityRepository;
import nextstep.payments.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class StudentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentEntityRepository(jdbcTemplate);
    }

    @Test
    void Image는_저장되어야_한다() {
        Student student = new Student(new StudentName("김남협"), new Email("namhyeop@gmail.com"),
            new Money(50000), ApprovalState.NON_APPROVAL, StudentType.NORMAL);

        int saveCount = studentRepository.save(StudentEntity.from(student));

        assertThat(saveCount).isEqualTo(1);
    }
}
