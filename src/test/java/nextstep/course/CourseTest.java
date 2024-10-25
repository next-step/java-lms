package nextstep.course;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Image;
import nextstep.lecture.Lecture;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

/*
- 과정(Course)을 기수 단위로 생성한다.
- Course를 여러개의 강의로 생성한다.
*/
public class CourseTest {

    @DisplayName("과정(Course)을 기수 단위로 생성한다.")
    @Test
    void createCourse() {
        Course course = new Course(1L, "TDD클린코드", 1L, 1L);
        assertThat(course)
                .extracting("id", "title", "term", "creatorId")
                .contains(1L, "TDD클린코드", 1L, 1L);
    }

    @DisplayName("Course를 여러개의 강의로 생성한다.")
    @Test
    void addLectureTest() throws ParseException {
        Course course = new Course(1L, "TDD클린코드", 1L, 1L);

        for (int i = 1; i < 6; i++) {
            course.addLecture(createFreeLecture(i));
        }

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

    private Lecture createFreeLecture(int id) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-04-05");
        Date endDate = simpleDateFormat.parse("2023-05-05");

        Image image = new Image(1L, "테스트이미지", 300, 200, 1);
        return Lecture.createFree((long) id, "테스트강의", image, startDate, endDate);
    }
}
