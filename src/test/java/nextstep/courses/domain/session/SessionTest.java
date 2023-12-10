package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.courses.exception.InvalidSessionDateException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    @DisplayName("강의 시작일은 종료일보다 늦어질 수 없다")
    void session_start_and_end() {
        assertThatThrownBy(() -> {
            new Session(1L, Type.PAID, Status.PREPARING, 800_000L, 10,
                LocalDateTime.now().plusSeconds(1),
                LocalDateTime.now());
        }).isInstanceOf(InvalidSessionDateException.class);
    }

    @Test
    @DisplayName("강의 오픈 여부를 확인한다")
    void session_isOpen() throws InvalidSessionDateException {
        Session sessionOpen = new Session(1L, Type.PAID, Status.OPEN, 800_000L, 2, LocalDateTime.now(),
            LocalDateTime.now().plusMonths(1));
        assertThat(sessionOpen.isOpen()).isTrue();

        Session sessionPreparing = new Session(1L, Type.PAID, Status.PREPARING, 800_000L, 2, LocalDateTime.now(),
            LocalDateTime.now().plusMonths(1));
        assertThat(sessionPreparing.isOpen()).isFalse();

        Session sessionClose = new Session(1L, Type.PAID, Status.CLOSE, 800_000L, 2, LocalDateTime.now(),
            LocalDateTime.now().plusMonths(1));
        assertThat(sessionClose.isOpen()).isFalse();
    }

    @Test
    @DisplayName("강의 중복 수강신청 여부를 확인한다")
    void session_duplicate_user() throws InvalidSessionDateException {
        Session session = new Session(1L, Type.PAID, Status.OPEN, 800_000L, 2, LocalDateTime.now(),
            LocalDateTime.now().plusMonths(1));
        session.addStudent(NsUserTest.JAVAJIGI);
        assertThat(session.duplicateUser(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다")
    void session_max_students() throws InvalidSessionDateException {
        Session session = new Session(1L, Type.PAID, Status.OPEN, 800_000L, 2, LocalDateTime.now(),
            LocalDateTime.now().plusMonths(1));
        session.addStudent(NsUserTest.JAVAJIGI);
        session.addStudent(NsUserTest.SANJIGI);
        assertThat(session.isMaxStudents()).isTrue();
    }
}
