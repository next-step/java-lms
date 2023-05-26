package nextstep.lms.domain;

import nextstep.lms.UnAuthorizedException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class SessionTest {

    protected static final Session JAVASESSION = Session.create("자바 기초", CourseTest.COURSE_A, LmsUserTest.ADMIN_1, 0, 100, null, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15));

    @Test
    void 무료강의추가() {
        Session newSession = Session.create("자바 중급", CourseTest.COURSE_A, LmsUserTest.ADMIN_1, 0, 100, null, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15));
        assertAll(
                () -> assertThat(newSession.getPrice()).isEqualTo(0),
                () -> assertThat(newSession.getMaxApplicantCount()).isEqualTo(100),
                () -> assertThat(newSession.getCourse()).isEqualTo(CourseTest.COURSE_A),
                () -> assertThat(newSession.getCreator()).isEqualTo(LmsUserTest.ADMIN_1)
        );
    }

    @Test
    void 일반회원이_강의추가_불가능() {
        assertThatThrownBy(
                () -> Session.create("파이썬 기초1", CourseTest.COURSE_A, LmsUserTest.USER_1, 0, 100, null, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15))
        ).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    void 과정생성자와_불일치시_생성불가능() {
        assertThatThrownBy(
                () -> Session.create("파이썬 기초2", CourseTest.COURSE_A, LmsUserTest.ADMIN_2, 0, 100, null, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15))
        ).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    void 모집중으로상태변경() {
        JAVASESSION.open(LmsUserTest.ADMIN_1);
        assertAll(
                () -> assertThat(JAVASESSION.getStatus()).isEqualTo(SessionStatus.OPEN),
                () -> assertThat(JAVASESSION.getUpdatedAt()).isNotNull()
        );
    }

    @Test
    void 생성자가_아닌_유저가_상태변경시() {
        assertAll(
                () -> assertThatThrownBy(() -> JAVASESSION.open(LmsUserTest.USER_1)).isInstanceOf(UnAuthorizedException.class),
                () -> assertThatThrownBy(() -> JAVASESSION.open(LmsUserTest.ADMIN_2)).isInstanceOf(UnAuthorizedException.class)
        );
    }
}