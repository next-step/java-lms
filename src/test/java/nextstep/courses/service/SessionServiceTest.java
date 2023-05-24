package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.courses.exception.InvalidSessionDateTimeException;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.users.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("SessionService 테스트")
@ExtendWith(MockitoExtension.class)
class SessionServiceTest {
    private static final Long SESSION_ID = 1L;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionService sessionService;

    @Mock
    private Session session;
    @Mock
    private User user;

    @Test
    @DisplayName("세션 ID를 이용하여 세션을 조회할 수 있다.")
    void getSessionById() {
        when(sessionRepository.findById(SESSION_ID)).thenReturn(session);

        Session retrievedSession = sessionService.getSessionById(SESSION_ID);

        assertThat(retrievedSession).isEqualTo(session);
        verify(sessionRepository).findById(SESSION_ID);
    }

    @Test
    @DisplayName("사용자를 세션에 등록할 수 있다.")
    void enrollSession() throws SessionEnrollmentException {
        when(sessionRepository.findById(SESSION_ID)).thenReturn(session);

        sessionService.enrollSession(SESSION_ID, user);

        verify(session).enroll(user);
        verify(sessionRepository).update(session);
    }

    @Test
    @DisplayName("모든 세션을 조회할 수 있다.")
    void getAllSessions() {
        List<Session> sessions = new ArrayList<>();
        sessions.add(session);
        when(sessionRepository.findAll()).thenReturn(sessions);

        List<Session> retrievedSessions = sessionService.getAllSessions();

        assertThat(retrievedSessions).isEqualTo(sessions);
        verify(sessionRepository).findAll();
    }

    @Test
    @DisplayName("세션을 생성할 수 있다.")
    void createSession() throws InvalidSessionDateTimeException {
        sessionService.createSession(session);

        verify(sessionRepository).save(session);
    }

    @Test
    @DisplayName("세션을 수정할 수 있다.")
    void updateSession() {
        sessionService.updateSession(session);

        verify(sessionRepository).update(session);
    }

    @Test
    @DisplayName("세션을 삭제할 수 있다.")
    void deleteSession() {
        sessionService.deleteSession(SESSION_ID);

        verify(sessionRepository).delete(SESSION_ID);
    }

    @Test
    @DisplayName("사용자를 세션에 등록할 때, 세션의 허용 인원을 초과하는 경우 예외를 던진다.")
    void enrollSession_EnrollmentLimitExceeded() throws SessionEnrollmentException {
        when(sessionRepository.findById(SESSION_ID)).thenReturn(session);
        doThrow(SessionEnrollmentException.class).when(session).enroll(user);

        assertThatThrownBy(() -> sessionService.enrollSession(SESSION_ID, user))
                .isInstanceOf(SessionEnrollmentException.class);
    }
}
