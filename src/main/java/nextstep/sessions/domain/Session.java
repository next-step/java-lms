package nextstep.sessions.domain;

import nextstep.sessions.domain.image.Image;
import nextstep.users.domain.NsUser;
import nextstep.utils.BaseEntity;

import java.time.LocalDateTime;

public class Session extends BaseEntity {

    private String sessionName;

    private Image image;

    private SessionDetails sessionDetails;

    //private List<NsUser> listeners;

    public Session(long id, String sessionName, SessionDetails sessionDetails) {
        this(id, LocalDateTime.now(), LocalDateTime.now(), sessionName, null, sessionDetails);
    }

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

    public void register(NsUser listener, Long amount) {
        sessionDetails.register(listener, amount);
    }

    public boolean isContainListener(NsUser listener) {
        return sessionDetails.isContainsListener(listener);
    }

}
