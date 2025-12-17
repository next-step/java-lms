package nextstep.courses.cohort.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class CohortTest {

    @Test
    void 기수생성시_코스식별자가_비정상이면_예외처리_할_수_있다() {
        assertThatThrownBy(() -> new Cohort(0L, LocalDateTime.now(), LocalDateTime.now(),
                        LocalDateTime.now(), LocalDateTime.now())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 기수생성시_수강신청기간이_수강기간보다_미래면_예외처리_할_수_있다() {
        // 조금 생각이 필요한 기능. 시작 시점을 기준으로 처리할지, 종료시점을 기준으로 할지, 각각의 기간을 기준으로 할지
    }

    @Test
    void 현재_수강인원을_더_받을수_있는지_확인할_수_있다() {
        // 최대 수강인원, 현재 수강인원 전부 필요할듯.
    }

    @Test
    void 지금이_수강신청_기간인지_확인할_수_있다() {
        // 기간 객체엔 특정 날짜 값이 시작-종료 중간값인지 확인하는 메서드 + 기수에선 수강신청기간 확인 메서드로 표현성 극대화
    }

    @Test
    void 지금이_수강_기간인지_확인할_수_있다() {
        // 기간 객체엔 특정 날짜 값이 시작-종료 중간값인지 확인하는 메서드 + 기수에선 수강기간 확인 메서드로 표현성 극대화
    }

    @Test
    void 기수의_상태를_수강신청_기간으로_변경할_수_있다() {
        // 특정 날짜값 받고 + 수강신청 기간인지 확인 + 신청상태로 상태변경
    }

    @Test
    void 기수의_상태를_수강_기간으로_변경할_수_있다() {
        // 특정 날짜값 받고 + 수강기간인지 확인 + 수강기간으로 상태변경
    }

}