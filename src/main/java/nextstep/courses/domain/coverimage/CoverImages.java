package nextstep.courses.domain.coverimage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoverImages {

  private Set<CoverImage> coverImages = new HashSet<>();

  public CoverImages() {
  }

  public CoverImages(Set<CoverImage> coverImages) {
    this.coverImages = coverImages;
  }
  public CoverImages(CoverImage coverImages) {
    this.coverImages.add(coverImages);
  }

  public void add(CoverImage coverImage) {
    this.coverImages.add(coverImage);
  }
  public void addAll(CoverImages coverImages) {
    this.coverImages.addAll(coverImages.coverImages);
  }

  public boolean has(CoverImage coverImage) {
    return this.coverImages.contains(coverImage);
  }
  public List<CoverImage> coverImages() {
    return new ArrayList<>(coverImages);
  }
}
