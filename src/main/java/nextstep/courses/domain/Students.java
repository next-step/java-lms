package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {

    private final List<NsUser> students;

    public Students() {
        students = new ArrayList<>();
    }

    public Students(List<NsUser> students) {
        if (students == null) {
            throw new IllegalArgumentException("student is null");
        }

        this.students = students;
    }

    public boolean isPossibleAdd(int maxNumberOfStudent) {
        return students.size() < maxNumberOfStudent;
    }
}
