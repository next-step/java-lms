package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.domain.session.SessionTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {

    public static final Course TEST_COURSE = Course.of(1L, Cohort.of(1L), new ArrayList<>(List.of(
        SessionTest.TEST_SESSION_CHARGED)));

    @Test
    @DisplayName("같은 상태를 갖는 두 과정은 동일한 과정이다.")
    void 과정_생성() {
        assertThat(Course.of(1L, Cohort.of( 1L), List.of(SessionTest.TEST_SESSION_CHARGED))).isEqualTo(TEST_COURSE);
    }

}