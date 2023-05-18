package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.courses.exception.InvalidSessionDateTimeException;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.users.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @DisplayName("강의 시작일이 종료일보다 늦으면 에외를 던진다.")
    @Test
    void create_session_with_invalid_date_time_should_throw_exception() {
        LocalDateTime openingDateTime = LocalDateTime.of(2023, 5, 1, 0, 0);
        LocalDateTime closingDateTime = LocalDateTime.of(2023, 4, 1, 0, 0);

        assertThrows(InvalidSessionDateTimeException.class, () ->
                Session.create("1st", new Image(), openingDateTime, closingDateTime,
                        SessionType.FREE, SessionStatus.RECRUITING, 100));
    }

    @DisplayName("강의가 모집중이 아닌데 수강 등록을 하면 예외를 던진다.")
    @Test
    void enroll_when_session_is_not_recruiting_should_throw_exception() throws InvalidSessionDateTimeException {
        LocalDateTime openingDateTime = LocalDateTime.of(2023, 5, 1, 0, 0);
        LocalDateTime closingDateTime = LocalDateTime.of(2023, 6, 1, 0, 0);
        Session session = Session.create("1st", new Image(), openingDateTime, closingDateTime,
                SessionType.FREE, SessionStatus.ENDED, 100);
        User user = new User(1L, "username", "password", "name", "email");

        assertThrows(SessionEnrollmentException.class, () -> session.enroll(user));
    }

    @DisplayName("강의 최대 수강 인원을 넘어선 수강신청을 하면 예외를 던진다.")
    @Test
    void enroll_when_session_is_full_should_throw_exception() throws InvalidSessionDateTimeException, SessionEnrollmentException {
        LocalDateTime openingDateTime = LocalDateTime.of(2023, 5, 1, 0, 0);
        LocalDateTime closingDateTime = LocalDateTime.of(2023, 6, 1, 0, 0);
        Session session = Session.create("1st", new Image(), openingDateTime, closingDateTime,
                SessionType.FREE, SessionStatus.RECRUITING, 0);
        User user = new User(1L, "username", "password", "name", "email");


        assertThrows(SessionEnrollmentException.class, () -> session.enroll(user));
    }

}