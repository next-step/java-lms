package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CourseTest {
    @Test
    void 생성자테스트() {
        Assertions.assertThat(new Course("TDD, 클린 코드 with Java", 1L)).isInstanceOf(Course.class);
    }

    @Test
    void getter테스트() {
        LocalDateTime now = LocalDateTime.now();
        Course course = new Course(1004L, "TDD, 클린 코드 with Java",1004L, now, now);

        course.addSession(new Session("20230701", "20230731", 30, 1004L));

        Assertions.assertThat(course.getTitle()).isEqualTo("TDD, 클린 코드 with Java");
        Assertions.assertThat(course.getCreatorId()).isEqualTo(1004L);
        Assertions.assertThat(course.getCreatedAt()).isEqualTo(now);
        Assertions.assertThat(course.getSessionCount()).isEqualTo(1);
    }


}
