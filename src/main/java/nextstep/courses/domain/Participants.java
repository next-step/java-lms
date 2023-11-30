package nextstep.courses.domain;

import nextstep.courses.exception.ParticipantsException;
import nextstep.users.domain.NsUser;

import java.util.Set;

public class Participants {
    private final Set<NsUser> values;

    public Participants(Set<NsUser> values) {
        this.values = values;
    }

    public void add(NsUser user) {
        if (values.contains(user)) {
            throw new ParticipantsException("이미 수강 신청한 참여자 입니다.");
        }
        values.add(user);
    }
}
