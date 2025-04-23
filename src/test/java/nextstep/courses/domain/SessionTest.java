package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class SessionTest {

    private static final long PRICE = 10000;
    private static final int MAX_CAPACITY = 3;

    private static final Student student = new Student(new NsUser());
    private Session freeSession;
    private Session paidSession;

    @BeforeEach
    public void setUp() {
        freeSession = Session.createFreeSession(LocalDate.now(), LocalDate.now());
        paidSession = Session.createPaidSession(new Money(PRICE), new Capacity(MAX_CAPACITY), LocalDate.now(), LocalDate.now());
    }


    @Test
    public void 수강신청_시_강의상태가_모집중_상태가_아닌_경우_예외_발생() {
        freeSession.readySession();
        assertThatIllegalStateException()
                .isThrownBy(() -> freeSession.enroll(0, student, new Payment()));

        freeSession.endSession();
        assertThatIllegalStateException()
                .isThrownBy(() -> freeSession.enroll(0, student, new Payment()));
    }

    @Test
    public void 유료강의신청_시_최대수강인원을_초과하는_경우_예외_발생() {
        Payment payment = new Payment("paymentId", 0L, 0L, PRICE);

        paidSession.startRecruitment();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> paidSession.enroll(MAX_CAPACITY, student, payment));
    }

    @Test
    public void 종료된_강의에_대해서_모집중_상태로_변경시_예외_발생() {
        freeSession.endSession();
        assertThatIllegalStateException()
                .isThrownBy(() -> freeSession.startRecruitment());
    }
}
