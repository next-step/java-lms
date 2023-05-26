package nextstep.courses.domain;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class SessionImageUrl {
    private static final String INVALID_URL_MESSAGE = "url 형식이 아닙니다.";

    private String imageUrl;

    public SessionImageUrl(String imageUrl) {
        validateUrl(imageUrl);
        this.imageUrl = imageUrl;
    }

    private void validateUrl(String imageUrl) {
        if (!isValidUrl(imageUrl)) {
            throw new IllegalArgumentException(INVALID_URL_MESSAGE);
        }
    }

    private boolean isValidUrl(String imageUrl) {
        try {
            URI uri = new URI(imageUrl);
            return uri.getScheme() != null && uri.getHost() != null;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionImageUrl that = (SessionImageUrl) o;

        return Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return imageUrl != null ? imageUrl.hashCode() : 0;
    }

    public String value() {
        return this.imageUrl;
    }
}
