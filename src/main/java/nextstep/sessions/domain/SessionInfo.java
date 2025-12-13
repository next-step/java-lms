package nextstep.sessions.domain;

public class SessionInfo {

    static final String ERROR_COVER_IMAGE_REQUIRED = "강의 커버 이미지는 필수입니다";

    private final Period period;

    private final SessionPricing pricing;

    private SessionImage image;

    public SessionInfo(Period period, SessionPricing pricing, SessionImage image) {
        validateImage(image);
        this.period = period;
        this.pricing = pricing;
        this.image = image;
    }

    public SessionPricing pricing() {
        return pricing;
    }

    private static void validateImage(SessionImage image) {
        if (image == null) {
            throw new IllegalArgumentException(ERROR_COVER_IMAGE_REQUIRED);
        }
    }

}
