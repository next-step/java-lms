package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<NsUser> students = new ArrayList<>();

    public boolean hasStudent(NsUser student) {
        return students.contains(student);
    }

    public void add(NsUser loginUser) {
        students.add(loginUser);
    }
}
