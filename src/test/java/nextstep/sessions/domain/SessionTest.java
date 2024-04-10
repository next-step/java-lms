package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static nextstep.sessions.domain.SessionStatus.RECRUITING;
import static nextstep.sessions.domain.SessionType.PAID;

public class SessionTest {

    @DisplayName("강의의 가격과 결제 금액이 같을 때, 수강신청이 된다")
    @Test
    void register() {
        SessionDetails details = new SessionDetails(40, 0, 30000, PAID, RECRUITING);
        List<NsUser> users = new ArrayList<>();
        Session tddCleanCodeJava = new Session(1L, "tdd, 클린코드 java", details, users);
        Payment payment = new Payment("tdd, 클린코드 java", 1L, 1L, 30000L);

        Assertions.assertThat(users.size()).isEqualTo(0);
        tddCleanCodeJava.register(NsUserTest.JAVAJIGI, payment.getAmount());
        Assertions.assertThat(users.size()).isEqualTo(1);
    }

}
