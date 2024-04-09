package nextstep.courses.domain.lecture.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.courses.domain.lecture.Lecture;
import nextstep.courses.domain.lecture.LectureStatus;
import org.junit.jupiter.api.Test;

class PaidCourseTest {

    @Test
    void 강의_최대_수강_인원은_0일_수_없다() {
    }

    @Test
    void 강의_최대_수강_인원을_넘을_경우_강의_신청이_불가능해야한다() {
    }

    @Test
    void 수강생이_결제한_금액과_수강료가_일치하지_않은_경우_강의_신청이_불가능하다() {
    }

    @Test
    void 유료_강의_수강신청시_강의_상태가_모집중이_아니라면_수강_신청이_불가능해야한다(){
        LectureStatus preparing = LectureStatus.PREPARING;

        Lecture paidLecture = new PaidCourse(1, LocalDateTime.now(), LocalDateTime.now(), LectureStatus.PREPARING);

        assertThat(paidLecture.isRegistrationAvailable()).isTrue();
    }
}
