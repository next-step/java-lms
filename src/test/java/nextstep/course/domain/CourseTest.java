package nextstep.course.domain;

import nextstep.courses.domain.Course;
import nextstep.session.domain.Session;
import nextstep.session.domain.image.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class CourseTest {

    @DisplayName("과정(Course)을 기수 단위로 생성한다.")
    @Test
    void createCourse() {
        //given, when
        Course course = new Course("TDD클린코드", 1L, 1L);

        //then
        assertThat(course)
                .extracting("id", "title", "term", "creatorId")
                .contains(1L, "TDD클린코드", 1L, 1L);
    }

    @DisplayName("Course를 여러개의 강의로 생성한다.")
    @Test
    void addLectureTest() {
        //given
        Course course = new Course(1L, "TDD클린코드", 1L, 1L);

        //when
        for (int i = 1; i < 6; i++) {
            course.addSession(createFreeSession(i));
        }

        //then
        assertThat(course.getLectures())
                .extracting("id", "title")
                .contains(
                        tuple(1L, "테스트강의"),
                        tuple(2L, "테스트강의"),
                        tuple(3L, "테스트강의"),
                        tuple(4L, "테스트강의"),
                        tuple(5L, "테스트강의")
                );
    }

    private Session createFreeSession(int id) {
        LocalDateTime startDate = LocalDateTime.parse("2023-04-05T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-05-05T00:00:00");

        Image image = new Image("테스트이미지.jpg", 300, 200, 1);
        return Session.createFree((long) id, "테스트강의", image, startDate, endDate);
    }
}
