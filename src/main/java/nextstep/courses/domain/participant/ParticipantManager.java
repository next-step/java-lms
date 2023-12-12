package nextstep.courses.domain.participant;

import nextstep.courses.exception.MaxParticipantsException;

import java.util.ArrayList;

public class ParticipantManager {

    private int maxParticipants;
    private SessionParticipants sessionParticipants;

    public ParticipantManager() {
        this(0, new SessionParticipants(new ArrayList<>()));
    }

    public ParticipantManager(int maxParticipants) {
        this(maxParticipants, new SessionParticipants(new ArrayList<>()));
    }

    public ParticipantManager(int maxParticipants, SessionParticipants nowParticipants) {
        this.maxParticipants = maxParticipants;
        this.sessionParticipants = nowParticipants;
    }

    public void add(SessionUserEnrolment user) {
        sessionParticipants.add(user);
    }

    public void validateParticipant() {
        if (maxParticipants == sessionParticipants.count()) {
            throw new MaxParticipantsException();
        }
    }

    public int max() {
        return maxParticipants;
    }

    public int nowCount() {
        return sessionParticipants.count();
    }

    public static ParticipantManager of(int maxParticipants) {
        return new ParticipantManager(maxParticipants);
    }

    public void mapppadBySessionParticipants(SessionParticipants sessionParticipants) {
        this.sessionParticipants = sessionParticipants;
    }
}
