package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.image.Image;
import nextstep.users.domain.NsUser;
import nextstep.utils.BaseEntity;

import java.time.LocalDateTime;

public class Session extends BaseEntity {

    private String sessionName;

    private Image image;

    private SessionRegisterDetails sessionRegisterDetails;

    public Session(long id, LocalDateTime startedAt, LocalDateTime endedAt, String sessionName) {
        this(id, startedAt, endedAt, sessionName, null, null);
    }

    public Session(long id, String sessionName, SessionRegisterDetails sessionRegisterDetails) {
        this(id, LocalDateTime.now(), LocalDateTime.now(), sessionName, null, sessionRegisterDetails);
    }

    public Session(long id,
                   LocalDateTime startedAt,
                   LocalDateTime endedAt,
                   String sessionName,
                   Image image,
                   SessionRegisterDetails sessionRegisterDetails
    ) {
        super(id, startedAt, endedAt);
        this.sessionName = sessionName;
        this.image = image;
        this.sessionRegisterDetails = sessionRegisterDetails;
    }

    public void register(NsUser listener, Payment payment) {
        sessionRegisterDetails.register(listener, payment);
    }

    public boolean isContainListener(NsUser listener) {
        return sessionRegisterDetails.isContainsListener(listener);
    }

    public long getId() {
        return super.getId();
    }

    public LocalDateTime getStartedAt() {
        return super.getStartedAt();
    }

    public LocalDateTime getEndedAt() {
        return super.getEndedAt();
    }

    public String getSessionName() {
        return sessionName;
    }

    public long getImageId() {
        return image.getId();
    }

    public long getSessionRegisterDetailsId() {
        return sessionRegisterDetails.getId();
    }
}
