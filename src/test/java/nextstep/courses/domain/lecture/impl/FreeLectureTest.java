package nextstep.courses.domain.lecture.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.courses.domain.lecture.LectureName;
import nextstep.courses.domain.lecture.LectureStatus;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

class FreeLectureTest {

    @Test
    void 무료_강의_수강신청시_강의_상태가_모집중이라면_수강_신청이_가능하다() {
        FreeLecture freeLecture = new FreeLecture(
            new LectureName("강의이름"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LectureStatus.RECRUITING);

        assertThat(freeLecture.isRegistrationAvailable()).isTrue();
    }

    @Test
    void 무료_강의_수강신청시_강의_상태가_모집중이_아니라면_수강_신청이_불가능하다() {
        FreeLecture freeLecture = new FreeLecture(
            new LectureName("강의이름"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LectureStatus.END);

        assertThat(freeLecture.isRegistrationAvailable()).isFalse();
    }

    @Test
    void 무료_강의_수강신청시_강의_수강료가_0원일_경우_수강_신청이_가능하다() {
        FreeLecture freeLecture = new FreeLecture(
            new LectureName("강의이름"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LectureStatus.RECRUITING);

        assertThat(freeLecture.isPaymentAmountSameTuitionFee(
            new Payment("1L", 1L, 1L, new Money(0)))).isTrue();
    }

    @Test
    void 무료_강의_수강신청시_강의_수강료가_0원이_아닐_경우_수강_신청이_불가능하다() {
        FreeLecture freeLecture = new FreeLecture(
            new LectureName("강의이름"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LectureStatus.RECRUITING);

        assertThat(freeLecture.isPaymentAmountSameTuitionFee(
            new Payment("1L", 1L, 1L, new Money(9999)))).isFalse();
    }
}
