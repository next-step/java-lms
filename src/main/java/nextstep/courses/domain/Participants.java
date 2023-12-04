package nextstep.courses.domain;

import nextstep.courses.exception.ParticipantsException;
import nextstep.users.domain.NsUser;

import java.util.Set;

public class Participants {
    private final Set<NsUser> values;

    public Participants(Set<NsUser> values) {
        this.values = values;
    }

    public int size() {
        return values.size();
    }

    public Participants add(NsUser user) {
        isDuplication(user);
        values.add(user);
        return new Participants(values);
    }

    private void isDuplication(NsUser user) {
        if (values.contains(user)) {
            throw new ParticipantsException("이미 수강 신청한 참여자 입니다.");
        }
    }


}
