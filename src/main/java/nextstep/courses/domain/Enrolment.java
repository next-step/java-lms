package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.users.domain.NsUser;

public class Enrolment {
    private List<NsUser> enrolment;

    public Enrolment() {
        this.enrolment = new ArrayList<>();
    }

    public int count() {
        return enrolment.size();
    }

    public void enroll(NsUser user) {
        enrolment.add(user);
    }
}
