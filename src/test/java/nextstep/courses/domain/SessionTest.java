package nextstep.courses.domain;

import nextstep.courses.domain.tuition.Money;
import nextstep.exception.EnrollNotAllowException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    public static final Session sessionPaid = new Session(false, 20, 20000);
    public static final Session sessionPaidAndFull = Session.createFull();

    @Test
    void 유료_강의는_수강_인원이_최대_수강_인원_이내이면_들을_수_있다() {
        assertThat(sessionPaid.isLeft()).isTrue();
    }

    @Test
    void 유료_강의는_최대_수강_인원을_초과할_수_없다() {
        assertThatThrownBy(() -> sessionPaidAndFull.register(NsUser.GUEST_USER, new Money(1000)))
                .isInstanceOf(EnrollNotAllowException.class);
    }

    @Test
    void 유료_강의는_수강생이_결제한_금액과_수강료가_일치할때_수강신청이_가능하다() {
        assertThat(sessionPaid.canPay(new Money(20000))).isTrue();
    }

    @Test
    void 유료_강의는_수강생이_결제한_금액과_수강료가_일치하지_않으면_수강_신청_불가하다() {
        assertThat(sessionPaid.canPay(new Money(19000))).isFalse();
    }

}
