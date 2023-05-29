package nextstep.lms.service;

import nextstep.lms.AlreadyEnrolledException;
import nextstep.lms.domain.*;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LmsServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private LmsService lmsService;

    private NsUser nsUser;
    private Student student;
    private Session session;

    @BeforeEach
    void setUp() {
        nsUser = NsUserTest.JAVAJIGI;
        session = SessionTest.CLASS_ONE;
        student = session.enroll(nsUser);
    }

    @Test
    @DisplayName("학생 수강 신청 테스트")
    void enrollSuccessTest() {
        NsUser sanjigi = NsUserTest.SANJIGI;
        Session session = SessionTest.CLASS_ONE;

        Student student = lmsService.enrollStudent(sanjigi, session);

        verify(sessionRepository).updateRegisteredStudent(session);
        verify(studentRepository).save(student);
    }

    @Test
    @DisplayName("학생 수강 신청 실패 테스트")
    void enrollFailTest() {
        when(studentRepository.findByNsUserIdAndSessionId(nsUser.getId(), session.getId()))
                .thenReturn(Optional.of(student));

        assertThatThrownBy(() -> lmsService.enrollStudent(nsUser, session))
                .isInstanceOf(AlreadyEnrolledException.class);
    }

    @Test
    @DisplayName("학생 수강 취소 테스트")
    void cancelSuccessTest() {
        when(studentRepository.findByNsUserIdAndSessionId(nsUser.getId(), session.getId()))
                .thenReturn(Optional.of(student));

        Student student = lmsService.cancelStudent(nsUser, session);
        verify(studentRepository).sessionCancel(student);
        verify(sessionRepository).updateRegisteredStudent(session);
    }

    @Test
    @DisplayName("학생 수강 취소 시 없는 학생 테스트")
    void cancelNotFoundTest() {
        assertThatThrownBy(() -> lmsService.cancelStudent(nsUser, session))
                .isInstanceOf(NotFoundException.class);
    }

}