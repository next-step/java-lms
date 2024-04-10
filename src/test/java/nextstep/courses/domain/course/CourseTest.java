package nextstep.courses.domain.course;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.cover.ImageHeight;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.cover.ImageType;
import nextstep.courses.domain.cover.ImageWidth;
import nextstep.courses.domain.lecture.Lecture;
import nextstep.courses.domain.lecture.LectureName;
import nextstep.courses.domain.lecture.LectureStatus;
import nextstep.courses.domain.lecture.MaxRegistrationCount;
import nextstep.courses.domain.lecture.RegistrationCount;
import nextstep.courses.domain.lecture.impl.PaidCourse;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    void 강의등록이_되어야_합니다(){
        Payment payment = new Payment("1", 1L, 1L, new Money(5000));
        Course course = new Course("18기-자바TDD", 1L);

        Lecture paidCourse = new PaidCourse(new LectureName("유료강의1"),
            new RegistrationCount(0),
            new MaxRegistrationCount(new RegistrationCount(999)),
            new Money(5000),
            new Image(new ImageSize(1),
                ImageType.JPG,
                new ImageWidth(300),
                new ImageHeight(200)),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LectureStatus.RECRUITING);

        course.addLecture(new LectureName("유료강의1"), paidCourse);

        assertThat(course.registerLecture(new LectureName("유료강의1"), payment)).isTrue();
    }
}
