package nextstep.courses.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CoverImageTest {

  @Test
  @DisplayName("커버 이미지를 생성합니다.")
  public void 커버_이미지_객체_생성() {
      // given
      String fileName = "filetest.png";
      long size = 10;
    double width = 300;
    double height = 200;

      // when
      CoverImage coverImage = CoverImage.defaultOf(fileName, size, width, height);

      // then
      Assertions.assertThat(coverImage).isNotNull();
  }

  @Test
  @DisplayName("커버 이미지 사이즈 제한")
  public void 커버_이미지_사이즈_제한_생성() {
    // given
    String fileName = "filetest.png";
    long size = 1024L*1024L;
    double width = 300;
    double height = 200;

    // then
    assertThrows(IllegalArgumentException.class
        , () ->CoverImage.defaultOf(fileName, size, width, height));
  }

  @Test
  @DisplayName("커버 이미지 확장자 제한")
  public void 커버_이미지_확장자_제한_생성() {
    // given
    String fileName = "filetest.txt";
    long size = 1024L;
    double width = 300;
    double height = 200;

    // then
    assertThrows(IllegalArgumentException.class
        , () ->CoverImage.defaultOf(fileName, size, width, height));
  }

  @Test
  @DisplayName("커버 이미지 너비 제한")
  public void 커버_이미지_너비_제한_생성() {
    // given
    String fileName = "filetest.txt";
    long size = 1024L;
    double width = 299;
    double height = 200;

    // then
    assertThrows(IllegalArgumentException.class
        , () ->CoverImage.defaultOf(fileName, size, width, height));
  }

  @Test
  @DisplayName("커버 이미지 높이 제한")
  public void 커버_이미지_높이_제한_생성() {
    // given
    String fileName = "filetest.txt";
    long size = 1024L;
    double width = 300;
    double height = 199;

    // then
    assertThrows(IllegalArgumentException.class
        , () ->CoverImage.defaultOf(fileName, size, width, height));
  }

  @Test
  @DisplayName("커버 이미지 높이너비 비율 제한")
  public void 커버_이미지_높이너비_비율_제한_생성() {
    // given
    String fileName = "filetest.png";
    long size = 1024L;
    double width = 300;
    double height = 201;

    // then
    assertThrows(IllegalArgumentException.class
        , () ->CoverImage.defaultOf(fileName, size, width, height));
  }
}
