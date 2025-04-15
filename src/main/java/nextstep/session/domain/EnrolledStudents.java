package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class EnrolledStudents {

    List<NsUser> students = new ArrayList<>();

    public void add(NsUser user) {
        students.add(user);
    }

    public int count() {
        return students.size();
    }
}
