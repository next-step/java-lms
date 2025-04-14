package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionTest {

    @Test
    public void 수강신청_시_강의상태가_모집중_상태가_아닌_경우_예외_발생() {
        Session session = Session.createFreeSession(LocalDate.now(), LocalDate.now());

        session.ready();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.enroll(new NsUser(), new Payment()));

        session.close();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.enroll(new NsUser(), new Payment()));
    }

    @Test
    public void 유료강의신청_시_최대수강인원을_초과하는_경우_예외_발생() {
        long price = 10000;
        int maxCapacity = 3;
        Session paidSession = Session.createPaidSession(new Money(price), new Capacity(maxCapacity), LocalDate.now(), LocalDate.now());
        paidSession.startRecruiting();

        Payment payment = new Payment("paymentId", 0L, 0L, price);

        IntStream.range(0, maxCapacity)
                .forEach(i -> paidSession.enroll(new NsUser(), payment));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> paidSession.enroll(new NsUser(), payment));
    }
}
