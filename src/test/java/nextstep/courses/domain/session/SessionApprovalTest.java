package nextstep.courses.domain.session;

import nextstep.courses.domain.registration.RegistrationOpenType;
import nextstep.courses.domain.registration.RegistrationStatus;
import nextstep.users.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static nextstep.courses.domain.session.SessionTest.createSession;

import static org.assertj.core.api.Assertions.*;

public class SessionApprovalTest {
    @Test
    @DisplayName("수강 승인 예외_학생의 수강 승인이 완료된경우")
    void validate_approvalException() {
        Student student = new Student(1L, 1L, RegistrationStatus.APPROVED);
        Sessions selectionSessions = new Sessions(Arrays.asList(createSession(1L, SessionCostType.FREE, RegistrationOpenType.CLOSE, SessionState.READY, 30)
                        , createSession(2L, SessionCostType.FREE, RegistrationOpenType.CLOSE, SessionState.READY, 30)));
        Sessions studentSessions = new Sessions(Arrays.asList(createSession(3L, SessionCostType.FREE, RegistrationOpenType.CLOSE, SessionState.READY, 30)));

        SessionApproval sessionApproval = new SessionApproval(student, studentSessions, selectionSessions);

        assertThatThrownBy(() -> {
            sessionApproval.validateRegistrationStatus();
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("수강 승인 완료되어 승인 불가합니다.");
    }

    @Test
    @DisplayName("수강 승인 예외_학생의 수강 취소가 완료된경우")
    void validate_declineException() {
        Student student = new Student(1L, 1L, RegistrationStatus.CANCELED);
        Sessions selectionSessions = new Sessions(Arrays.asList(createSession(1L, SessionCostType.FREE, RegistrationOpenType.CLOSE, SessionState.READY, 30)
                , createSession(2L, SessionCostType.FREE, RegistrationOpenType.CLOSE, SessionState.READY, 30)));
        Sessions studentSessions = new Sessions(Arrays.asList(createSession(3L, SessionCostType.FREE, RegistrationOpenType.CLOSE, SessionState.READY, 30)));

        SessionApproval sessionApproval = new SessionApproval(student, studentSessions, selectionSessions);

        assertThatThrownBy(() -> {
            sessionApproval.validateRegistrationStatus();
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("수강 취소되어 승인 불가합니다.");
    }

    @Test
    @DisplayName("수강 승인 예외_선발된 학생이 아닌 경우")
    void validate_notSelectionStudentException() {
        Student student = new Student(1L, 1L, RegistrationStatus.CANCELED);
        Sessions selectionSessions = new Sessions(Arrays.asList(createSession(1L, SessionCostType.FREE, RegistrationOpenType.CLOSE, SessionState.READY, 30)
                , createSession(2L, SessionCostType.FREE, RegistrationOpenType.CLOSE, SessionState.READY, 30)));
        Sessions studentSessions = new Sessions(Arrays.asList(createSession(3L, SessionCostType.FREE, RegistrationOpenType.CLOSE, SessionState.READY, 30)));


        SessionApproval sessionApproval = new SessionApproval(student, studentSessions, selectionSessions);

        assertThatThrownBy(() -> {
            sessionApproval.validateSelectionSessions();
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("해당 수강 대기생은 선발된 인원이 아닙니다.");
    }
}
