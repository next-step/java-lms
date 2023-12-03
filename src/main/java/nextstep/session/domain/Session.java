package nextstep.session.domain;

import java.time.LocalDateTime;
import nextstep.image.domain.Image;

public class Session {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Image image;

    private SessionType type;

    private SessionStatus status;
}
