package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionTest {

    @Test
    @DisplayName("이미 수강신청을한 인원은 수강신청을 할 수 없다")
    void student_exception() {
        Session session = Sessions.createSession(1L, SessionType.CHARGE);
        session.enroll(NsUserTest.JAVAJIGI);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            session.enroll(NsUserTest.JAVAJIGI);
        });
    }

    @Test
    @DisplayName("무료 강의에 가격이 있을 때 예외가 발생한다.")
    void create_exception() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            Sessions.createSession(1L, SessionType.FREE);
        });
    }
}