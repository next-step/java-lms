package nextstep.lms.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaidSessionTest {
    PaidSession paidSession;

    @BeforeEach
    void setting() {
        paidSession = new PaidSession("유료강의", LocalDateTime.of(2023, 10, 30, 0, 0),
                LocalDateTime.of(2023, 12, 14, 23, 59),
                new CoverImage("next.png", 0.8, 300, 200), SessionStatus.RECRUITING,
                new ArrayList<>(), 1, 800000);
    }

    @DisplayName("강의 최대 수강 인원을 초과할 수 없다.")
    @Test
    void 최대_수강인원_초과() throws Exception {
        paidSession.enrollment(NsUserTest.JAVAJIGI);
        Assertions.assertThatThrownBy(() -> paidSession.enrollment(NsUserTest.SANJIGI))
                .isInstanceOf(Exception.class)
                .hasMessage("강의 최대 수강 인원을 초과할 수 없습니다.");
    }
}