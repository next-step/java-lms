package nextstep.courses.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoverImage {
    private static final Pattern URL_PATTERN = Pattern.compile("^(?:https?:\\/\\/)?(?:www\\.)?[a-zA-Z0-9./]+$");
    private static final String URL_ERROR_MESSAGE = "이미지 경로가 올바르지 않습니다. URL : ";

    private final String imageUrl;

    public CoverImage(String imageUrl) {
        validationImageUrl(imageUrl);
        this.imageUrl = imageUrl;
    }

    private void validationImageUrl(String imageUrl) {
        Matcher matcher = URL_PATTERN.matcher(imageUrl);
        if(!matcher.matches()) {
            throw new IllegalArgumentException(URL_ERROR_MESSAGE + imageUrl);
        }
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
