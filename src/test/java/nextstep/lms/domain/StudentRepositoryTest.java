package nextstep.lms.domain;

import nextstep.lms.infrastructure.JdbcStudentRepository;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.lms.domain.StudentApprovedType.*;
import static nextstep.lms.domain.StudentRegisterType.CANCELED;
import static nextstep.lms.domain.StudentSelectedType.SELECTED;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class StudentRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("학생 저장 테스트")
    void saveTest() {
        NsUser sanjigi = NsUserTest.SANJIGI;
        Session session = SessionTest.CLASS_ONE;

        Student student = session.enroll(sanjigi);

        int count = studentRepository.save(student);

        assertThat(count)
                .isEqualTo(1);
    }

    @Test
    @DisplayName("강의 취소 테스트")
    void sessionCancelTest() {
        Student student = studentRepository
                .findByNsUserIdAndSessionId(1L, 1L)
                .orElseThrow(NotFoundException::new);

        student.sessionCancel();
        studentRepository.sessionCancel(student);

        Student canceledStudent = studentRepository
                .findByNsUserIdAndSessionId(1L, 1L)
                .orElseThrow(NotFoundException::new);

        assertThat(canceledStudent.getStudentRegisterType())
                .isEqualTo(CANCELED.toString());
    }

    @Test
    @DisplayName("학생 선발 변경 테스트")
    void studentSelectTest() {
        Student student = studentRepository
                .findByNsUserIdAndSessionId(1L, 2L)
                .orElseThrow(NotFoundException::new);

        student.changeStudentSelect();
        studentRepository.changeStudentSelectedType(student);

        Student findStudent = studentRepository
                .findByNsUserIdAndSessionId(1L, 2L)
                .orElseThrow(NotFoundException::new);

        assertThat(findStudent.getStudentSelectedType())
                .isEqualTo(SELECTED.toString());
    }

    @Test
    @DisplayName("학생 승인 변경 테스트")
    void studentApproveTest() {
        Student student = studentRepository
                .findByNsUserIdAndSessionId(1L, 2L)
                .orElseThrow(NotFoundException::new);

        student.changeStudentSelect();
        studentRepository.changeStudentSelectedType(student);
        student.changeApprovedStatus();
        studentRepository.changeStudentApprovedType(student);

        Student findStudent = studentRepository
                .findByNsUserIdAndSessionId(1L, 2L)
                .orElseThrow(NotFoundException::new);

        assertThat(findStudent.getStudentApprovedType())
                .isEqualTo(APPROVED.toString());
    }
}