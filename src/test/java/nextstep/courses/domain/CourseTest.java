package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static nextstep.Fixtures.aCourse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CourseTest {

    @Test
    @DisplayName("강의는 여러개의 강의를 가질 수 있다.")
    void test01() {
        Course course = aCourse().withSessions(new ArrayList<>()).build();

        course.addSession(new Session());
        course.addSession(new Session());

        assertThat(course.getSessions()).hasSize(2);
    }

    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다.")
    void test02() {
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endedAt = startedAt.plusDays(2);

        Course course = aCourse().withStartedAt(startedAt).withEndedAt(endedAt).build();

        assertThat(course.getStartedAt()).isEqualTo(startedAt);
        assertThat(course.getEndedAt()).isEqualTo(endedAt);
    }

    @Test
    @DisplayName("강의는 커버 이미지 정보를 가진다.")
    void test03() {
        String coverImageUrl = "http://edu.nextstep.camp";
        Course course = aCourse().withCoverImageUrl(coverImageUrl).build();

        assertThat(course.getCoverImageUrl()).isEqualTo(coverImageUrl);
    }

    @ParameterizedTest(name = "강의는 {0} 강의가 존재한다.")
    @EnumSource(value = CourseType.class, names = {"FREE", "PAID"})
    void test04(CourseType courseType) {
        Course course = aCourse().withCourseType(courseType).build();

        assertThat(course.getCourseType()).isEqualTo(courseType);
    }

    @ParameterizedTest(name = "강의 상태 {0} 존재한다.")
    @EnumSource(value = CourseStatus.class, names = {"READY", "OPEN", "CLOSED"})
    void test05(CourseStatus courseStatus) {
        Course course = aCourse().withCourseStatus(courseStatus).build();

        assertThat(course.getCourseStatus()).isEqualTo(courseStatus);
    }

    @Test
    @DisplayName("모집중일때만 강의 수강신청이 가능하다.")
    void test06() {
        Course course = aCourse().withCourseStatus(CourseStatus.OPEN).withMaxUserCount(10).build();

        course.register(NsUserTest.JAVAJIGI);

        assertThat(course.getUsers()).hasSize(1).containsExactly(NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("모집중이 아니면 수강신청이 불가능하다.")
    void test07() {
        Course course = aCourse().withCourseStatus(CourseStatus.READY).build();

        assertThatThrownBy(() -> course.register(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의 최대 수강인원이 초과하면 등록할 수 없다.")
    void test08() {
        Course course = aCourse().withCourseStatus(CourseStatus.OPEN)
                                 .withMaxUserCount(1)
                                 .withUsers(List.of(NsUserTest.SANJIGI)).build();

        assertThatThrownBy(() -> course.register(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }
}
