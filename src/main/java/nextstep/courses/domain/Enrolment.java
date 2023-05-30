package nextstep.courses.domain;

import java.util.List;

import nextstep.users.domain.NsUser;

public class Enrolment {
    private List<NsUser> enrolment;

    public int count() {
        return enrolment.size();
    }
}
