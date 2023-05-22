package nextstep.courses.domain;

import java.time.LocalDate;

public class Sessions {
    public static Session createSession(Long id) {
        return createSessionWithEnrollment(id, 50, 49, SessionStatus.ENROLLING);
    }

    public static Session createSessionWithEnrollment(Long id, int capacity, int sizeOfStudents, SessionStatus status) {
        return new Session(
                id, "TDD, 클린 코드 with Java 16기", 2L, "https://edu.nextstep.camp/images/covers/basic/008.jpg",
                ChargeStatus.CHARGE, 800_000,
                capacity, sizeOfStudents, status,
                LocalDate.now(), LocalDate.now().plusDays(30));
    }
}
