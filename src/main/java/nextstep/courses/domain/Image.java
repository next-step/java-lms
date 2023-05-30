package nextstep.courses.domain;

public class Image {
    private long id;
    private String url;

    public void updateUrl(String imgUrl) {
        url = imgUrl;
    }

    public boolean isSameImage(String imgUrl) {
        return url == imgUrl;
    }
}
