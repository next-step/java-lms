package nextstep.courses.domain;

import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CourseTest {

    private final Image image = Image.from("이미지 입니다");
    private Session sessionOne;
    private Session sessionTwo;

    private Course compareCourse;
    private Course compareCourseTwoSession;

    @BeforeEach
    public void initialize() {
        SimpleIdGenerator.initialize();
        sessionOne = Session.createSession(
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                image,
                SessionState.PREPARING,
                SessionState.RECRUITING,
                SessionType.FREE
        );

        sessionTwo = Session.createSession(
                1,
                NsUserTest.JAVAJIGI,
                LocalDate.now(),
                LocalDate.now(),
                image,
                SessionState.PREPARING,
                SessionState.END_OF_RECRUITMENT,
                SessionType.FREE
        );
        compareCourse = Course.of(1, List.of(sessionOne));
        compareCourseTwoSession = Course.of(1, List.of(sessionOne, sessionTwo));
    }

    @Test
    @DisplayName("객체가 잘 생성되는지 확인")
    void 객체가_정상적으로_생성되는지_확인() {

        Course course = Course.createCourse(List.of(sessionOne));

        assertAll(
                () -> assertThat(course).isNotNull(),
                () -> assertThat(course).isInstanceOf(Course.class),
                () -> assertThat(course.getSessions()).hasSize(1)
        );
    }

    @Test
    @DisplayName("객체 동등성 테스트")
    void 객체가_동등성_테스트() {

        Course course = Course.createCourse(List.of(sessionOne));

        assertAll(
                () -> assertThat(course).isNotNull(),
                () -> assertThat(course).isEqualTo(compareCourse)
        );
    }

    @Test
    @DisplayName("강의 한건이 잘 추가 되는지 확인")
    void 강의_한건_추가_테스트() {

        Course course = Course.createCourse(List.of(sessionOne)).addSession(sessionTwo);

        assertAll(
                () -> assertThat(course).isNotNull(),
                () -> assertThat(course.getSessions()).hasSize(2),
                () -> assertThat(course).isEqualTo(compareCourseTwoSession)
        );
    }


    @Test
    @DisplayName("과정에 강의가 잘 합성되는지 확인")
    void 객체_생성_테스트() {
        Course course = Course.createCourse(List.of(sessionOne)).concat(List.of(sessionTwo));

        assertAll(
                () -> assertThat(course).isNotNull(),
                () -> assertThat(course.getSessions()).hasSize(2),
                () -> assertThat(course).isEqualTo(compareCourseTwoSession)
        );
    }

}