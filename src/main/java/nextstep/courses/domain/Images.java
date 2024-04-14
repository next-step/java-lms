package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Images {
  List<Image> images;

  public Images(List<Image> images) {
    this.images = images;
  }

  public Images(Image image){
    this.images = new ArrayList<>();
    this.images.add(image);
  }

  public List<Image> getImages() {
    return images;
  }
}
