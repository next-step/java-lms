package nextstep.courses.domain;

import java.time.LocalDate;

public class Sessions {
    public static Session createSession(Long id, SessionType type) {
        return createSessionWithEnrollment(id, 50, SessionStatus.ENROLLING, type);
    }

    public static Session createSessionWithEnrollment(Long id, int capacity, SessionStatus status, SessionType type) {
        return new Session(
                id, "강의제목",
                type, 1000,
                new Enrollment(status, capacity),
                LocalDate.now(), LocalDate.now().plusDays(30),
                2L, "https://naver.com/res/images/cover.jpg");
    }
}