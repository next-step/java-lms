package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.image.Image;
import nextstep.utils.BaseEntity;

import java.time.LocalDateTime;

public class Session extends BaseEntity {

    private String sessionName;

    private Image image;

    private SessionDetails sessionDetails;

    private Payment payment;

    public Session(long id,
                   LocalDateTime startedAt,
                   LocalDateTime endedAt,
                   String sessionName,
                   Image image,
                   SessionDetails sessionDetails
    ) {
        super(id, startedAt, endedAt);
        this.sessionName = sessionName;
        this.image = image;
        this.sessionDetails = sessionDetails;
    }

}
