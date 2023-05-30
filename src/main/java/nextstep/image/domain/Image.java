package nextstep.image.domain;

import nextstep.image.exception.EmptyImageUrlException;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Image {
    private Long imageId;
    @NotBlank(message = "이미지링크는 공백이 허용되지 않습니다")
    private String imageUrl;

    public Image(Long imageId, String imageUrl) {
        validateImageUrl(imageUrl);
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public static Image of(String imageLink) {
        return new Image(null, imageLink);
    }

    private void validateImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new EmptyImageUrlException();
        }
    }

    public Long getImageId() {
        return imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", imageUrl='" + imageUrl + '\'' +
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
        return Objects.hash(imageId);
    }
}
