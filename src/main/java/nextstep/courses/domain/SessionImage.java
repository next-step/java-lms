package nextstep.courses.domain;

public class SessionImage {
    private final String imageUrl;

    public SessionImage(String imageUrl) {
        if (!isValidUrl(imageUrl)) {
            throw new IllegalArgumentException("유효하지 않은 URL 입니다.");
        }
        this.imageUrl = imageUrl;
    }

    private boolean isValidUrl(String coverImageUrl) {
        try {
            new java.net.URL(coverImageUrl).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "SessionImage{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
