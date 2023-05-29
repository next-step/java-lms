package nextstep.courses.domain;

import nextstep.courses.exception.InvalidUrlPatternException;
import nextstep.courses.utility.UrlPattern;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionCoverImage that = (SessionCoverImage) o;
        return Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUrl);
    }

    @Override
    public String toString() {
        return "SessionCoverImage{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }

}
