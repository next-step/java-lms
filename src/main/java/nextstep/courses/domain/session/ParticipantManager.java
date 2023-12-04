package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;

public class ParticipantManager {

    private int maxParticipants;
    private SessionParticipants sessionParticipants;

    public ParticipantManager(int maxParticipants) {
        this(maxParticipants, new SessionParticipants(new ArrayList<>()));
    }

    public ParticipantManager(int maxParticipants, SessionParticipants nowParticipants) {
        this.maxParticipants = maxParticipants;
        this.sessionParticipants = nowParticipants;
    }

    public void add(NsUser user) {
        sessionParticipants.add(user);
    }

    public void validateParticipant() {
        if (maxParticipants == sessionParticipants.count()) {
            throw new IllegalArgumentException("최대 참가자 수를 초과하였습니다.");
        }
    }

    public int max() {
        return maxParticipants;
    }

    public int nowCount() {
        return sessionParticipants.count();
    }
}
