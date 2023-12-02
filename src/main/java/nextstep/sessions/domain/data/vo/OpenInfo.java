package nextstep.sessions.domain.data.vo;

public class OpenInfo {

    private final CoverImage coverImage;
    private final Duration duration;

    public OpenInfo(CoverImage coverImage, Duration duration) {
        this.coverImage = coverImage;
        this.duration = duration;
    }
}
