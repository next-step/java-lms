package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

class ImageTest {

  public static final Image TEST_IMAGE = new Image(
      1L,
      "originalFileName",
      ImageType.GIF,
      new Url("coverImgUrl")
  );

}