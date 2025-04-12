package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionTest {

    @Test
    public void 수강신청_시_강의상태가_모집중_상태가_아닌_경우_예외_발생() {
        Session session = Session.createFreeSession();

        session.ready();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.register(new NsUser()));

        session.close();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.register(new NsUser()));
    }

    @Test
    public void 유료강의신청_시_최대수강인원을_초과하는_경우_예외_발생() {
        int maxCapacity = 3;
        Session paidSession = Session.createPaidSession(maxCapacity);
        paidSession.startRecruiting();

        IntStream.range(0, maxCapacity)
                .forEach(i -> paidSession.register(new NsUser()));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> paidSession.register(new NsUser()));
    }
}
