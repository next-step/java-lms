package nextstep.session.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionProgressStatus;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionStudentRepository;
import nextstep.session.domain.Student;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class SessionStudentServiceTest {

    @Mock
    private SessionStudentRepository sessionStudentRepository;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionStudentService sessionStudentService;

    private Student student;
    private Session session;
    private NsUser user;

    @BeforeEach
    public void setUp() {
        user = new NsUser(0L, "somin", "1111", "박소민", "test@naver.com");
        session = Session.createSessionWithProgressStatusAndFee(
            SessionProgressStatus.IN_PROGRESS, 50000);
        student = session.enroll(user, 50000);
    }

    @Test
    public void 수강_승인() {
        when(sessionRepository.findById(student.getSession_id())).thenReturn(
            session);
        assertThat(student.isApproved()).isFalse();
        sessionStudentService.approveStudent(student.getSession_id(), user, student);
        assertThat(student.isApproved()).isTrue();
    }

    @Test
    public void 수강_취소() {
        when(sessionRepository.findById(student.getSession_id())).thenReturn(
            session);
        assertThat(student.isApproved()).isFalse();
        sessionStudentService.cancelStudent(student.getSession_id(), user, student);
        assertThat(student.isApproved()).isFalse();
    }

    @Test
    public void 권한_없을시_수강_승인_불가능() {
        when(sessionRepository.findById(student.getSession_id())).thenReturn(
            session);
        user = new NsUser(1L, "somin", "1111", "박소민", "test@naver.com");
        assertThat(student.isApproved()).isFalse();
        assertThatThrownBy(() -> {
            sessionStudentService.approveStudent(student.getSession_id(), user, student);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("해당 강의에 권한이 없습니다.");

    }

    @Test
    public void 권한_없을시_수강_취소_불가능() {
        when(sessionRepository.findById(student.getSession_id())).thenReturn(
            session);
        user = new NsUser(1L, "somin", "1111", "박소민", "test@naver.com");
        assertThat(student.isApproved()).isFalse();
        assertThatThrownBy(() -> {
            sessionStudentService.cancelStudent(student.getSession_id(), user, student);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("해당 강의에 권한이 없습니다.");
    }


}
