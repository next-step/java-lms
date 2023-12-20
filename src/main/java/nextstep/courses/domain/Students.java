package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<NsUser> students;

    public Students() {
        this(new ArrayList<>());
    }
    public Students(List<NsUser> students) {
        this.students = students;
    }

    public void add(NsUser loginUser, int capacity) {
        if (this.students.size() == capacity) {
            throw new IllegalArgumentException("강의 최대 수강 인원을 초과할 수 없습니다");
        }
        if (this.students.contains(loginUser)) {
            throw new IllegalArgumentException("이미 수강신청한 강의를 수강신청 할 수 없습니다");
        }
        this.students.add(loginUser);
    }
}
