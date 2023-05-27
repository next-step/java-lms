package nextstep.courses.domain;

import nextstep.courses.exception.InvalidUrlPatternException;
import nextstep.courses.utility.UrlPattern;

public class SessionCoverImage {

    private final String imageUrl;

    private SessionCoverImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static SessionCoverImage create(String url) {
        if (UrlPattern.isValid(url)) {
            return new SessionCoverImage(url);
        }
        throw new InvalidUrlPatternException();
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
