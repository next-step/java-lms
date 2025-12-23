package nextstep.courses.session.domain;

public class Session {

    private Long id;
    private String title;
    private String url;
    private SessionImage image;


    public Session(String title, String url, SessionImage sessionImage) {
        this(0L, title, url, sessionImage);
    }

    public Session(
            String title,
            String url,
            String imageUrl, String imageName,
            long size, int width, int height
    ) {
        this(0L, title, url, new SessionImage(imageUrl, size, imageName, width, height));
    }

    public Session(Long id, String title, String url, SessionImage image) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.image = image;
    }
}
