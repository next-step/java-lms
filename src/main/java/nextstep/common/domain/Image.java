package nextstep.common.domain;

import javax.validation.constraints.NotBlank;

public class Image {
    private Long imageId;
    @NotBlank(message = "이미지링크는 공백이 허용되지 않습니다")
    private String imageLink;

    public Image(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageUrl() {
        return imageLink;
    }
}
