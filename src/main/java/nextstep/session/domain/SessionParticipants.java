package nextstep.session.domain;

import java.util.List;
import nextstep.users.domain.NsUser;

public class SessionParticipants {

    private List<NsUser> participants;

    private long maxNumberOfParticipants;

    public SessionParticipants(List<NsUser> participants, long maxNumberOfParticipants) {
        this.participants = participants;
        this.maxNumberOfParticipants = maxNumberOfParticipants;
    }

    public boolean isFull() {
        return maxNumberOfParticipants - participants.size() < 1;
    }
}
