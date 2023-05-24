package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MaxEnrollmentTest {

  @Test
  void 수강_신청자_수가_정원을_초과하지_않으면_false_반환_테스트() {
    MaxEnrollment maxEnrollment = new MaxEnrollment(10);

    int beforeFull = 9;

    assertThat(maxEnrollment.isFull(beforeFull)).isFalse();
  }

  @Test
  void 수강_신청자_수가_정원을_초과하면_true_반환_테스트() {
    MaxEnrollment maxEnrollment = new MaxEnrollment(10);

    int afterFull = 10;

    assertThat(maxEnrollment.isFull(afterFull)).isTrue();
  }


}