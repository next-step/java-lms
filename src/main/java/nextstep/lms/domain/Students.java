package nextstep.lms.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<NsUser> students;

    public Students(List<NsUser> students) {
        this.students = new ArrayList<>(students);
    }

    public int size() {
        return students.size();
    }

    public void enroll(int capacity, NsUser nsUser) {
        if (this.students.contains(nsUser)) {
            throw new IllegalArgumentException("이미 수강중인 강의입니다.");
        }
        if (this.students.size() >= capacity) {
            throw new IllegalArgumentException("최대 수강 인원을 초과할 수 없습니다.");
        }
        this.students.add(nsUser);
    }
}
