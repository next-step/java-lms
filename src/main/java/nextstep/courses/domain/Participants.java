package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final List<NsUser> participants = new ArrayList<>();

    public boolean enroll(NsUser nsUser) {
        return participants.add(nsUser);
    }

    public int size() {
        return participants.size();
    }

}
