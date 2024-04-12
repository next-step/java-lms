package nextstep.session.service;

import nextstep.common.domain.BaseEntity;
import nextstep.exception.StudentsException;
import nextstep.session.domain.Student;
import nextstep.session.domain.StudentRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student(3L, 3L, NsUserTest.JAVAJIGI, new BaseEntity());
    }

    @DisplayName("삭제할 대상이 없으면, 오류가 발생한다.")
    @Test
    void throwExceptionOfNoUserToDelete() {
        when(studentRepository.updateDeleteStatus(student.toVO().getSessionId(), student.toVO().getUserId(), true))
                .thenReturn(0);

        assertThatThrownBy(() -> studentService.delete(NsUserTest.SANJIGI, student))
                .isInstanceOf(StudentsException.class);
    }

    @DisplayName("삭제시, 삭제 상태가 업데이트 된다.")
    @Test
    void updateDeleteStatusWhenDelete() {
        when(studentRepository.updateDeleteStatus(student.toVO().getSessionId(), student.toVO().getUserId(), true))
                .thenReturn(1);

        studentService.delete(NsUserTest.SANJIGI, student);

        assertThat(student.toVO().isDeleted()).isTrue();
    }
}
