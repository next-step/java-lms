package nextstep.courses.domain;

public class FreeSession extends Session {

    public FreeSession(Image image, Period period) {
        super(image, period, SessionType.FREE);
    }

}
