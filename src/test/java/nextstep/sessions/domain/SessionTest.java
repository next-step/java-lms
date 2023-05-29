package nextstep.sessions.domain;

import nextstep.courses.domain.CourseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {
    public static final Session S1 = Session.of(1L, CourseTest.C);
    public static final Session S2 = Session.of(2L, CourseTest.C);

    @DisplayName("Session 객체가 잘 생성되는지 확인")
    @Test
    void Session_객체가_정상적으로_생성되는지_확인() {
        assertThat(Session.of(1L, CourseTest.C)).isInstanceOf(Session.class);
    }
}
