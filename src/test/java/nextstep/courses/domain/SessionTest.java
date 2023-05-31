package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.courses.exception.InvalidSessionDateTimeException;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.users.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SessionTest {

    private static final LocalDateTime VALID_OPENING_DATE_TIME = LocalDateTime.of(2023, 5, 1, 0, 0);
    private static final LocalDateTime VALID_CLOSING_DATE_TIME = LocalDateTime.of(2023, 6, 1, 0, 0);
    private static final Enrollment MAXIMUM_100_PEOPLE_ENROLLMENT = new Enrollment(100);
    private static final Enrollment MAXIMUM_0_PEOPLE_ENROLLMENT = new Enrollment(0);
    private static final User SAMPLE_USER = new User(1L, "dkswnkk", "pwd", "안주형", "dkswnkk.dev@gmail.com");


    @DisplayName("강의 시작일이 종료일보다 늦으면 예외를 던진다.")
    @Test
    void create_session_with_invalid_date_time_should_throw_exception() {
        LocalDateTime invalidOpeningDateTime = VALID_CLOSING_DATE_TIME.plusDays(1);

        assertThrows(InvalidSessionDateTimeException.class, () ->
                new SessionTime(invalidOpeningDateTime, VALID_CLOSING_DATE_TIME)
        );
    }

    @DisplayName("강의 최대 수강 인원을 넘어선 수강신청을 하면 예외를 던진다.")
    @Test
    void enroll_when_session_is_full_should_throw_exception() {
        SessionTime validSessionTime = new SessionTime(VALID_OPENING_DATE_TIME, VALID_CLOSING_DATE_TIME);
        Session session = Session.create("1st", new Image(), validSessionTime, SessionType.FREE, SessionStatus.RECRUITING, MAXIMUM_0_PEOPLE_ENROLLMENT);

        assertThrows(SessionEnrollmentException.class, () ->
                session.enroll(SAMPLE_USER));
    }

    @DisplayName("모든 조건이 충족되면 사용자를 세션에 등록할 수 있다.")
    @Test
    void enroll_when_all_conditions_are_met_should_succeed() {
        SessionTime validSessionTime = new SessionTime(VALID_OPENING_DATE_TIME, VALID_CLOSING_DATE_TIME);
        Session session = Session.create("1st", new Image(), validSessionTime, SessionType.FREE, SessionStatus.RECRUITING, MAXIMUM_100_PEOPLE_ENROLLMENT);

        session.enroll(SAMPLE_USER);
    }

    @DisplayName("수강신청 후 인원 수가 증가 한다")
    @Test
    void enrollment_increases_user_count() {
        SessionTime validSessionTime = new SessionTime(VALID_OPENING_DATE_TIME, VALID_CLOSING_DATE_TIME);
        Session session = Session.create("1st", new Image(), validSessionTime, SessionType.FREE, SessionStatus.RECRUITING, MAXIMUM_100_PEOPLE_ENROLLMENT);
        int beforeEnrollmentUserCount = 0;

        session.enroll(SAMPLE_USER);

        Assertions.assertThat(session.getEnrollmentUserCount()).isEqualTo(beforeEnrollmentUserCount + 1);
    }

}