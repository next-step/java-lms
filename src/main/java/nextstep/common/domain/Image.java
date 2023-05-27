package nextstep.common.domain;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Image {
    private Long imageId;
    @NotBlank(message = "이미지링크는 공백이 허용되지 않습니다")
    private String imageLink;

    public Image(Long imageId,String imageLink) {
        this.imageId = imageId;
        this.imageLink = imageLink;
    }

    public static Image of(String imageLink) {
        return new Image(null, imageLink);
    }

    public Long getImageId() {
        return imageId;
    }

    public String getImageUrl() {
        return imageLink;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image other = (Image) o;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, imageLink);
    }
}
