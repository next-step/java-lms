package nextstep.courses.domain;

import nextstep.courses.exception.SessionRegistrationException;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class ImageUrl {
    private String imageUrl;

    public ImageUrl(String imageUrl) {
        validateUrl(imageUrl);
        this.imageUrl = imageUrl;
    }

    private void validateUrl(String url) {
        try {
            new URL(url).toURI();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new IllegalArgumentException("잘못된 url 형식 입니다.");
        }
    }
}
