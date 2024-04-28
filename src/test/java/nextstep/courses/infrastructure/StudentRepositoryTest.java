package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;
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
    void Student는_저장되어야_한다() {
        Student student = new Student(null, new StudentName("김남협"), new Email("namhyeop@gmail.com"),
            new Money(50000), ApprovalState.NON_APPROVAL, StudentType.NORMAL);

        int saveCount = studentRepository.save(StudentEntity.from(student));

        assertThat(saveCount).isEqualTo(1);
    }

    @Test
    void Student는_조회되어야_한다() {
        Optional<StudentEntity> studentOptional = studentRepository.findById(1L);
        assertThat(studentOptional.isPresent()).isTrue();
        assertThat(studentOptional.get()).extracting("id", "studentName", "email", "paymentAmount",
                "approvalState", "studentType", "createdAt", "updatedAt")
            .containsExactly(1L, "namhyeop", "namhyeop@gmail.com", 100, "NON_APPROVAL", "NORMAL",
                LocalDateTime.parse("2024-01-01T00:00:00"), null);
    }

    @Test
    void Student의_승인상태는_업데이트_되어야한다() {
        Optional<StudentEntity> studentOptional = studentRepository.findById(3L);
        assertThat(studentOptional.isPresent()).isTrue();

        StudentEntity studentEntity = studentOptional.get();
        Student student = studentEntity.toStudent();

        Student approvedStudent = student.approveStudent();
        System.out.println("approvedStudent = " + approvedStudent);

        int approvedCount = studentRepository.updateApprovalState(
            studentEntity.from(approvedStudent));

        assertThat(approvedCount).isEqualTo(1);
    }
}
