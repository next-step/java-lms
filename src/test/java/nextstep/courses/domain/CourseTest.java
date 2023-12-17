package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CourseTest {
    public static final Course C1 = new Course(1L, "JAVA, TDD with Clean Code", NsUserTest.JAVAJIGI.getId(), LocalDateTime.now(), LocalDateTime.now());
    public static final Course C2 = new Course(2L, "Kotlin, TDD with Clean Code", NsUserTest.SANJIGI.getId(), LocalDateTime.now(), LocalDateTime.now());

    @Test
    @DisplayName("과정 생성")
    void create() {
        assertThat(new Course()).isInstanceOf(Course.class);
    }
}
