package nextstep.courses.domain.session;

public class CoverImage {
    private String url;

    public CoverImage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "CoverImage{" +
                "url='" + url + '\'' +
                '}';
    }
}
