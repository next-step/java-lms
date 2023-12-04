package nextstep.sessions.domain.data.vo;

public class OpenInfo {

    private CoverImage coverImage;
    private final Duration duration;

    public OpenInfo(Duration duration) {
        this.duration = duration;
    }

    public Duration duration() {
        return duration;
    }
}
