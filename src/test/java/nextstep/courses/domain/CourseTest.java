package nextstep.courses.domain;

import nextstep.payments.domain.Payments;
import nextstep.sessions.domain.*;
import nextstep.sessions.domain.Sessions;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CourseTest {
    public static Course COURSE1 = new Course("과정1", NsUserTest.JAVAJIGI.getId());

    @DisplayName("과정명과 유저아이디, 강의목록을 전달하면 Course 객체를 생성한다.")
    @Test
    void courseTest() {
        assertThat(new Course("과정2", 1L, new Sessions(List.of(SessionTest.JAVA, SessionTest.JAVA_TDD_17))))
                .isInstanceOf(Course.class);
    }

    @DisplayName("추가할 과정이 모집중이 아니면 IllegalStateException을 던진다.")
    @Test
    void addSessionTest() {
        assertThatThrownBy(() -> COURSE1.addSession(SessionTest.JAVA_TDD_16))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("과정을 결제하면 Payments 객체를 반환한다.")
    @Test
    void payTest() {
        COURSE1.addSession(SessionTest.JAVA_TDD_17);
        assertThat(COURSE1.pay()).isInstanceOf(Payments.class);
    }
}
