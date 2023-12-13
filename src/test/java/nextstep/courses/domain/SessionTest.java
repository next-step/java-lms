package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {
    @Test
    @DisplayName("강의 생성")
    void create() {
        assertThat(new Session())
                .isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("강의 생성시 가격 타입 & 가격 매칭 실패 에러 발생")
    void create_throw_exception_sessionPaymentType_price() {
        assertThatThrownBy(() ->
                new Session(0L,
                        new SessionPayment(SessionPaymentType.PAID, 0L),
                        new Enrollment(new NsUsers(List.of(NsUserTest.JAVAJIGI)), 1),
                        new Duration(
                                LocalDateTime.now(),
                                LocalDateTime.now().plusMonths(1L)
                        ),
                        new CoverImage("pobi.png",500L,300D, 200D)
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
