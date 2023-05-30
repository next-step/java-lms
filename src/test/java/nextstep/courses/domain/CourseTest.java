package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {

    public static final Course TEST_COURSE = new Course(1L, "코스", 1L, LocalDateTime.parse("2023-05-01T00:00:00"), null);

    @Test
    @DisplayName("같은 상태를 갖는 두 과정은 동일한 과정이다.")
    void 과정_생성() {
        assertThat(new Course(1L, "코스", 1L, LocalDateTime.parse("2023-05-01T00:00:00"), null)).isEqualTo(TEST_COURSE);
    }

}