package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Students {
    private final List<NsUser> students;

    public Students() {
        this.students = new ArrayList<>();
    }

    public void add(NsUser user) {
        validateDuplicate(user);
        students.add(user);
    }

    private void validateDuplicate(NsUser user) {
        if (students.contains(user)) {
            throw new IllegalStateException();
        }
    }

    public int size() {
        return students.size();
    }

    public boolean isFull(int capacity) {
        return students.size() >= capacity;
    }
}
