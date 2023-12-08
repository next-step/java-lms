package nextstep.session.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import nextstep.users.domain.NsUser;

public class SessionParticipants {

    private List<NsUser> participants = new ArrayList<>();

    private long maxNumberOfParticipants;

    public SessionParticipants() {
    }

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
        return maxNumberOfParticipants - participants.size() <= 0;
    }

    public List<NsUser> getParticipants() {
        return participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SessionParticipants that = (SessionParticipants) o;
        return maxNumberOfParticipants == that.maxNumberOfParticipants && Objects.equals(participants,
                that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participants, maxNumberOfParticipants);
    }

    @Override
    public String toString() {
        return "SessionParticipants{" +
                "participants=" + participants +
                ", maxNumberOfParticipants=" + maxNumberOfParticipants +
                '}';
    }
}
