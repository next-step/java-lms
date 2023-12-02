package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;

public class ParticipantCount {

    private int maxParticipants;
    private SessionParticipants sessionParticipants;

    public ParticipantCount(int maxParticipants) {
        this(maxParticipants, new SessionParticipants(new ArrayList<>()));
    }

    public ParticipantCount(int maxParticipants, SessionParticipants nowParticipants) {
        validateParticipant(maxParticipants, nowParticipants);
        this.maxParticipants = maxParticipants;
        this.sessionParticipants = nowParticipants;
    }

    public void add(NsUser user) {
        sessionParticipants.add(user);
        validateParticipant(maxParticipants, sessionParticipants);
    }

    private void validateParticipant(int maxParticipants, SessionParticipants nowParticipants) {
        if (maxParticipants < nowParticipants.count()) {
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
