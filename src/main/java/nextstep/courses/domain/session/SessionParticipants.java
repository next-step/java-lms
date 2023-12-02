package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionParticipants {

    private final List<NsUser> participants;

    public SessionParticipants(List<NsUser> participants) {
        this.participants = participants;
    }

    public void add(NsUser user) {
        validate(user);
        participants.add(user);
    }

    private void validate(NsUser user) {
        if (participants.contains(user)) {
            throw new IllegalArgumentException("이미 등록된 사용자입니다.");
        }
    }

    public int count() {
        return participants.size();
    }
}
