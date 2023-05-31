package nextstep.courses.domain;

public class CoverImage {
    private Long id;
    private String title;
    private String imageUrl;

    public CoverImage(Long id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }
}
