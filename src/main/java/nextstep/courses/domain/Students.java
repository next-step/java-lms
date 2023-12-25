package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<NsUser> students;
    private final int capacity;

    public Students(int capacity) {
        this(new ArrayList<>(), capacity);
    }
    public Students(List<NsUser> students, int capacity) {
        this.students = students;
        this.capacity = capacity;
    }

    public void add(NsUser loginUser) {
        if (this.students.size() == this.capacity) {
            throw new IllegalArgumentException("강의 최대 수강 인원을 초과할 수 없습니다");
        }
        if (this.students.contains(loginUser)) {
            throw new IllegalArgumentException("이미 수강신청한 강의를 수강신청 할 수 없습니다");
        }
        this.students.add(loginUser);
    }

    public List<NsUser> getStudents() {
        return students;
    }

    public int getCapacity() {
        return capacity;
    }
}
