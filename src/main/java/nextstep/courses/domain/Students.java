package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<NsUser> students;

    public Students() {
        this.students = new ArrayList<>();
    }

    public boolean isGreaterEqualThan(Long capacity) {
        return this.students.size() >= capacity;
    }

    public void add(NsUser user) {
        if (this.contains(user)) {
            throw new IllegalStateException("이미 강의를 신청하였습니다.");
        }
        this.students.add(user);
    }

    private boolean contains(NsUser user) {
        return this.students.stream()
                .anyMatch(currentUser -> currentUser.matchUser(user));
    }
}
