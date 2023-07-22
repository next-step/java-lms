package nextstep.courses.domain;

import nextstep.courses.CannotRegisterSessionException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {

    private int maxCapacity;
    private final List<NsUser> students;

    public Students() {
        maxCapacity = 30;
        students = new ArrayList<>(maxCapacity);
    }

    public Students(int countOfPerson) {
        this.maxCapacity = countOfPerson;
        students = new ArrayList<>(maxCapacity);
    }

    public void register(NsUser students) throws CannotRegisterSessionException {
        if (isExceedCapacity(1)) {
            throw new CannotRegisterSessionException("정원 초과입니다.");
        }
        this.students.add(students);
    }

    public void register(List<NsUser> students) throws CannotRegisterSessionException {
        if (isExceedCapacity(students.size())) {
            throw new CannotRegisterSessionException("정원 초과입니다.");
        }
        this.students.addAll(students);
    }

    public int size() {
        return students.size();
    }

    private boolean isExceedCapacity(int count) {
        return maxCapacity <= students.size() + count;
    }
}
