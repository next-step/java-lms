package nextstep.common.domain;

import javax.validation.constraints.NotBlank;

public class Image {
    @NotBlank(message = "이미지링크는 공백이 허용되지 않습니다")
    private final String imageLink;
    private Long imageId;

    public Image(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageUrl() {
        return imageLink;
    }
}
