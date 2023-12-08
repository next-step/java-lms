package nextstep.session.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class SessionParticipants {

    private List<NsUser> participants;

    private long maxNumberOfParticipants;

    public SessionParticipants(List<NsUser> participants, long maxNumberOfParticipants) {
        this.participants = participants;
        this.maxNumberOfParticipants = maxNumberOfParticipants;
    }

    public void enroll(NsUser user) {
        if (!isFull()) {
            this.participants = new ArrayList<>(participants);
            this.participants.add(user);
        }
    }

    public boolean isFull() {
        return maxNumberOfParticipants - participants.size() < 1;
    }

    public List<NsUser> getParticipants() {
        return participants;
    }
}
