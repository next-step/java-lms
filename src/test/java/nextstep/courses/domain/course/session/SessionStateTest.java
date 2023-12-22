package nextstep.courses.domain.course.session;

import nextstep.courses.domain.course.session.apply.Applies;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionStateTest {
    @Test
    @DisplayName("SessionState 는 무료 강의가 0원이 아니면 예외를 던진다")
    void newObject_freeType_overZeroAmount_throwsException() {
        assertThatThrownBy(
                () -> new SessionState(SessionType.FREE, 1000L, Integer.MAX_VALUE, new Applies())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("SessionState 는 무료 강의가 정원이 최대가 아니면 예외를 던진다.")
    void newObject_freeType_lessThanMaxQuota_throwsException() {
        assertThatThrownBy(
                () -> new SessionState(SessionType.FREE, 0L, 100, new Applies())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("SessionState 는 유료 강의가 0원이면 예외를 던진다.")
    void newObject_chargedType_zeroAmount_throwsException() {
        assertThatThrownBy(
                () -> new SessionState(SessionType.CHARGE, 0L, 100, new Applies())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("SessionState 는 유료 강의가 정원 수가 0명이면 예외를 던진다.")
    void newObject_chargedType_zeroQuota_throwsException() {
        assertThatThrownBy(
                () -> new SessionState(SessionType.CHARGE, 100L, 0, new Applies())
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
