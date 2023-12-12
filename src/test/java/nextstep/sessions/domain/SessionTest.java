package nextstep.sessions.domain;

import nextstep.imgae.domain.Image;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SessionTest {
    Long id;
    String title;
    SessionPeriod sessionPeriod;
    LocalDateTime startedDate;
    LocalDateTime endedDate;
    Image image;
    boolean isFree;
    NsUser nsUser;

    @BeforeEach
    void setUp() {
        id = 1L;
        title = "강의 제목";
        image = new Image(id, 1000, "jpg", 300, 200);
        isFree = true;
        nsUser = new NsUser(1L, "id", "password", "name", "email");

        startedDate = LocalDateTime.of(2023, 12, 1, 0, 0, 0);
        endedDate = LocalDateTime.of(2023, 12, 30, 0, 0, 0);
        sessionPeriod = new SessionPeriod(startedDate, endedDate);
    }

    @DisplayName("강의가 모집중이 아니면 에러 발생")
    @Test
    void 강의_모집_불가() {
        EnrollmentCondition freeEnrollment = new FreeEnrollment();
        Enrollment enrollment = new Enrollment(freeEnrollment);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Session(id, startedDate, endedDate, SessionState.PREPARE, image, isFree,
                        nsUser, title, enrollment).enroll(nsUser, 0))
                .withMessage("강의가 모집중이 아닙니다.");
    }
}
