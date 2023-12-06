package nextstep.session;

import nextstep.session.domain.Session;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static nextstep.session.TestFixtures.endSession;
import static nextstep.session.TestFixtures.preparingSession;
import static nextstep.session.TestFixtures.registableRecrutingFreeSession;
import static nextstep.session.TestFixtures.registableRecrutingPaidSession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class SessionTest {

    @ParameterizedTest
    @MethodSource(value = "nonRegistrableSessions")
    void 모집중인_상태일_때만_강의_수강신청_할_수_있다(Session session) {
        Throwable throwable = catchThrowable(() -> session.register(NsUserTest.JAVAJIGI, BigDecimal.valueOf(999)));

        assertThat(throwable).isInstanceOf(IllegalStateException.class)
                .hasMessage("수강 신청 불가능한 상태입니다.");
    }

    static Stream<Session> nonRegistrableSessions() {
        return Stream.of(endSession(), preparingSession());
    }

    @Test
    void 유료_강의는_수강생이_결제한_금액과_수강료가_일치할_때_수강신청이_가능하다() {
        Session paidSession = registableRecrutingPaidSession();

        Throwable throwable = catchThrowable(() -> paidSession.register(NsUserTest.JAVAJIGI, BigDecimal.valueOf(999)));

        assertThat(throwable).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능합니다.");
    }

    @Test
    void 무료_강의는_결제_금액_없이_수강신청이_가능하다() {
        Session freeSession = registableRecrutingFreeSession();
        int expectedMemberSize = freeSession.memberSize() + 1;

        freeSession.register(NsUserTest.SANJIGI, BigDecimal.ZERO);

        assertThat(freeSession.memberSize()).isEqualTo(expectedMemberSize);
    }
}
