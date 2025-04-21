package nextstep.payments.domain;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.payments.domain.PaymentStatus.PENDING;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PaymentTest {

    @DisplayName("Payment 인스턴스 생성")
    @Test
    public void testConstructor() {
        assertDoesNotThrow(() -> new Payment(
            "1",
            false,
            LocalDateTime.now(),
            LocalDateTime.now(),
            new Session(),
            NsUserTest.JAVAJIGI,
            300_000L,
            PENDING
        ));
    }

    @DisplayName("강의와 유저 정보가 동일")
    @Test
    public void testEqualsSessionUser_Ture() {
        Session session = new Session();
        assertThat(new Payment("1", session, NsUserTest.JAVAJIGI, 200_000L)
            .equalsSessionUser(new Payment("2", session, NsUserTest.JAVAJIGI, 200_000L))
        ).isTrue();
    }

    @DisplayName("강의와 유저 정보가 동일하지 않음")
    @Test
    public void testEqualsSessionUser_False() {
        Session session = new Session();
        assertThat(new Payment("1", session, NsUserTest.JAVAJIGI, 200_000L)
            .equalsSessionUser(new Payment("3", session, NsUserTest.SANJIGI, 200_000L))
        ).isFalse();
    }
}
