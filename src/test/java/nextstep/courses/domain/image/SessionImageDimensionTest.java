package nextstep.courses.domain.image;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SessionImageDimensionTest {

  @ParameterizedTest
  @CsvSource({"300,200"})
  void 너비는_300이상_높이는_200이상(int width, int height){
    assertDoesNotThrow(() -> new SessionImageDimension(width, height));
  }

  @ParameterizedTest
  @CsvSource({"299,299", "299,300", "300,199"})
  void 그_외_너비_예외(int width, int height){
    assertThatThrownBy(() -> new SessionImageDimension(width, height))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("이미지는 가로 300이상, 세로 200 이상이어야 합니다.");
  }

  @ParameterizedTest
  @CsvSource({"300,200","1200,800"})
  void 너비와_높이_비율은_3_대_2(int width, int height){
    assertDoesNotThrow(() -> new SessionImageDimension(width, height));

  }

  @ParameterizedTest
  @CsvSource({"400,200", "600,300", "900,400"})
  void 그_외_비율_예외(int width, int height){
    assertThatThrownBy(() -> new SessionImageDimension(width, height))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("이미지는 가로 x 세로 3대 2이어야 합니다.");
  }

}