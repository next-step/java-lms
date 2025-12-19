package nextstep.courses.cohort.domain;

import static nextstep.courses.cohort.domain.fixture.CohortFixture.식별자를_전달받아_기수픽스처를_생성한다;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.courses.cohort.domain.enumeration.CohortStateType;
import nextstep.courses.cohort.domain.fixture.CohortFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CohortTest {

    @Test
    void 기수생성시_코스식별자가_비정상이면_예외처리_할_수_있다() {
        assertThatThrownBy(() -> new Cohort(0L, 5, 1, 0, LocalDateTime.now(), LocalDateTime.now(),
                        LocalDateTime.now(), LocalDateTime.now())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 기수생성시_수강신청기간이_수강기간보다_미래면_예외처리_할_수_있다() {
        // 조금 생각이 필요한 기능. 시작 시점을 기준으로 처리할지, 종료시점을 기준으로 할지, 각각의 기간을 기준으로 할지
        // 이건 요구사항 기준으로 달라질거 같긴한데... 시작시간을 기준으로하자.
        // 신청종료시점이 수강시작기간보다 뒤에있어도 된다 = ot기간까지 수강신청 받는경우도 있으니 ㅇㅇ ==> 이건 인터페이스로 빼도 되겠다.
    }

    @ParameterizedTest
    @CsvSource({"19, true", "20, false"})
    void 현재_수강인원을_더_받을수_있는지_확인할_수_있다(int presentStudentCount, boolean result) {
        // 최대 수강인원, 현재 수강인원 전부 필요할듯.
        Cohort cohort = new Cohort(1L, 5, 20, presentStudentCount, LocalDateTime.now(), LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());

        assertThat(cohort.isCanResist()).isEqualTo(result);
    }

    @Test
    void 지금이_수강_기간인지_확인할_수_있다() {
        // 기간 객체엔 특정 날짜 값이 시작-종료 중간값인지 확인하는 메서드 + 기수에선 수강기간 확인 메서드로 표현성 극대화
    }

    @Test
    void 기수의_상태를_수강신청_기간으로_변경할_수_있다() {
        // 특정 날짜값 받고 + 수강신청 기간인지 확인 + 신청상태로 상태변경
        Cohort cohort = new Cohort(1L, 5,  20, 0,
                LocalDateTime.of(2025, 1, 1, 0, 0, 0),
                LocalDateTime.of(2025, 1, 10, 23, 59, 59),
                LocalDateTime.of(2025, 1, 17, 0, 0, 0),
                LocalDateTime.of(2025, 2, 25, 23, 59, 59)
        );

        cohort.putOnRecruit(LocalDateTime.of(2025, 1, 1, 0, 0, 1));

        assertThat(cohort.cohortStateType()).isEqualTo(CohortStateType.RECRUIT);
    }

    @Test
    void 수강신청기간이_아닌데_기수의_상태를_수강신청_기간으로_변경하면_예외처리_할_수_있다() {
        // 특정 날짜값 받고 + 수강신청 기간인지 확인 + 신청상태로 상태변경
        Cohort cohort = new Cohort(1L, 5, 20, 0,
                LocalDateTime.of(2025, 1, 1, 0, 0, 0),
                LocalDateTime.of(2025, 1, 10, 23, 59, 59),
                LocalDateTime.of(2025, 1, 17, 0, 0, 0),
                LocalDateTime.of(2025, 2, 25, 23, 59, 59)
        );

        assertThatThrownBy(
                () -> cohort.putOnRecruit(LocalDateTime.of(2025, 1, 11, 0, 0, 1))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 기수의_상태를_수강_기간으로_변경할_수_있다() {
        // 특정 날짜값 받고 + 수강신청 기간인지 확인 + 신청상태로 상태변경
        Cohort cohort = new Cohort(1L, 5, 20, 0,
                LocalDateTime.of(2025, 1, 1, 0, 0, 0),
                LocalDateTime.of(2025, 1, 10, 23, 59, 59),
                LocalDateTime.of(2025, 1, 17, 0, 0, 0),
                LocalDateTime.of(2025, 2, 25, 23, 59, 59)
        );

        cohort.putOnActive(LocalDateTime.of(2025, 1, 17, 0, 0, 1));

        assertThat(cohort.cohortStateType()).isEqualTo(CohortStateType.ACTIVE);
    }

    @Test
    void 수강기간이_아닌데_기수의_상태를_수강중으로_변경하면_예외처리_할_수_있다() {
        // 특정 날짜값 받고 + 수강신청 기간인지 확인 + 신청상태로 상태변경
        Cohort cohort = new Cohort(1L, 5, 20, 0,
                LocalDateTime.of(2025, 1, 1, 0, 0, 0),
                LocalDateTime.of(2025, 1, 10, 23, 59, 59),
                LocalDateTime.of(2025, 1, 17, 0, 0, 0),
                LocalDateTime.of(2025, 2, 25, 23, 59, 59)
        );

        assertThatThrownBy(
                () -> cohort.putOnActive(LocalDateTime.of(2025, 2, 26, 0, 0, 0))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 기수의_식별자가_같은지_식별할_수_있다() {
        Cohort cohort = 식별자를_전달받아_기수픽스처를_생성한다(1L);

        assertThat(cohort.isSameCohortId(1L)).isTrue();
    }

    @Test
    void 기수의_현재수강인원을_1만큼_증가시킬_수_있다() {
        Cohort cohort = new Cohort(1L, 5, 20, 19, LocalDateTime.now(), LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
        assertThat(cohort.isCanResist()).isTrue();

        cohort.plusOnePresent();

        assertThat(cohort.isCanResist()).isFalse();
    }
}