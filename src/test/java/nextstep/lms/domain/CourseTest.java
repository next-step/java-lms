package nextstep.lms.domain;

import nextstep.lms.UnAuthorizedException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class CourseTest {

    protected static final Course COURSE_A = Course.create("과정A", LmsUserTest.ADMIN_1);


    @Test
    void 과정추가() {
        Course courseB = Course.create("과정B", LmsUserTest.ADMIN_1);
        assertAll(
                () -> assertThat(courseB.getTitle()).isEqualTo("과정B")
        );
    }

    @Test
    void 일반회원이_과정추가_불가능() {
        assertThatThrownBy(() -> Course.create("과정C", LmsUserTest.USER_1)).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    void 과정에_강의_추가() {
        COURSE_A.addSession(SessionTest.JAVASESSION);
        assertThat(COURSE_A.getSessions()).contains(SessionTest.JAVASESSION);
    }

    @Test
    void 과정에_강의추가시_다른생성자() {

    }
}