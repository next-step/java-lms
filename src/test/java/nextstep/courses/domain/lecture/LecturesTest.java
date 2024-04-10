package nextstep.courses.domain.lecture;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.cover.ImageHeight;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.cover.ImageType;
import nextstep.courses.domain.cover.ImageWidth;
import nextstep.courses.domain.lecture.impl.FreeLecture;
import org.junit.jupiter.api.Test;

class LecturesTest {

    @Test
    void Lectures는_강의를_추가할_수_있다() {
        Lectures lectures = new Lectures();
        Lecture lecture = new FreeLecture(new LectureName("강의이름1"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            new Image(new ImageSize(1),
                ImageType.JPG,
                new ImageWidth(300),
                new ImageHeight(200)),
            LectureStatus.PREPARING);
        lectures.addLecture(new LectureName("강의이름1"), lecture);

        assertThat(lectures.findLecture(new LectureName("강의이름1"))).isEqualTo(lecture);
    }

    @Test
    void Lectures는_강의를_삭제할_수_있다() {
        Lectures lectures = new Lectures();
        LectureName lectureName = new LectureName("강의이름2");
        Lecture lecture = new FreeLecture(new LectureName("강의이름2"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            new Image(new ImageSize(1),
                ImageType.JPG,
                new ImageWidth(300),
                new ImageHeight(200)),
            LectureStatus.PREPARING);

        lectures.addLecture(lectureName, lecture);
        Lecture deletedLecture= lectures.deleteLecture(lectureName);

        assertThat(deletedLecture).isEqualTo(lecture);
    }
}
