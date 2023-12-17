package nextstep.courses.domain.coverimage;

import java.util.HashSet;
import java.util.Set;

public class CoverImages {

  private Set<CoverImage> coverImages = new HashSet<>();

  public CoverImages(Set<CoverImage> coverImages) {
    this.coverImages = coverImages;
  }

  public void add(CoverImage coverImage) {
    this.coverImages.add(coverImage);
  }

  public boolean has(CoverImage coverImage) {
    return this.coverImages.contains(coverImage);
  }
}
