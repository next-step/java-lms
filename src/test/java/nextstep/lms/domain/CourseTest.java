package nextstep.lms.domain;

import nextstep.lms.UnAuthorizedException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class CourseTest {

    protected static Course courseA = Course.create("과정A", LmsUserTest.admin1);


    @Test
    void 과정추가() {
        Course courseB = Course.create("과정B", LmsUserTest.admin1);
        assertAll(
                () -> assertThat(courseB.getTitle()).isEqualTo("과정B"),
                () -> assertThat(courseB.getId()).isEqualTo(2L),
                () -> assertThat(courseA.getId()).isEqualTo(1L)
        );
    }

    @Test
    void 일반회원이_과정추가_불가능() {
        assertThatThrownBy(() -> Course.create("과정C", LmsUserTest.user1)).isInstanceOf(UnAuthorizedException.class);
    }
}