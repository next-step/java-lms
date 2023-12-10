package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionTest {
    private static Session session;
    @BeforeEach
    void setup() {
        this.session = new Session(
                "TDD, 클린 코드 with Java 17기", new Charge(ChargeStatus.CHARGE, 800000),
                new Enrollment(SessionStatus.ENROLLING, 10),
                LocalDate.now(), LocalDate.now().plusDays(30),
                1L, "https://www.naver.com");
    }

    @Test
    @DisplayName("이미 수강신청을한 인원은 수강신청을 할 수 없다")
    void student_exception() {
        session.enroll(NsUserTest.JAVAJIGI);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            session.enroll(NsUserTest.JAVAJIGI);
        });
    }
}