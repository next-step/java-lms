package nextstep.courses.domain.participant;

import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Participant {
    private final NsUser nsUser;
    private final ParticipantState participantState;


    public Participant(NsUser nsUser, ParticipantState participantState) {
        this.nsUser = nsUser;
        this.participantState = participantState;
    }

    public boolean equalUser(NsUser nsUser) {
        return this.nsUser.equals(nsUser);
    }

    public Long getUserId() {
        return nsUser.getId();
    }

    public String getState() {
        return participantState.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(nsUser, that.nsUser) && participantState == that.participantState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUser, participantState);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "nsUser=" + nsUser +
                ", participantState=" + participantState +
                '}';
    }
}
