package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {

    private int maxCountOfPerson;
    private final List<NsUser> students;

    public Students() {
        maxCountOfPerson = 30;
        students = new ArrayList<>(maxCountOfPerson);
    }

    public Students(int countOfPerson) {
        this.maxCountOfPerson = countOfPerson;
        students = new ArrayList<>(maxCountOfPerson);
    }

    public void register(NsUser students) {
        if (isExceedCapacity(1)) {
            throw new IllegalArgumentException("정원 초과입니다.");
        }
        this.students.add(students);
    }

    public void register(List<NsUser> students) {
        if (isExceedCapacity(students.size())) {
            throw new IllegalArgumentException("정원 초과입니다.");
        }
        this.students.addAll(students);
    }

    public int size() {
        return students.size();
    }

    private boolean isExceedCapacity(int count) {
        return maxCountOfPerson <= students.size() + count;
    }
}
