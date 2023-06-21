package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<NsUser> students;

    public Students() {
        this.students = new ArrayList<>();
    }

    public Students(List<NsUser> students) {
        this.students = students;
    }

    public boolean isGreaterEqualThan(Long capacity) {
        return this.students.size() >= capacity;
    }

    public Long add(NsUser user) {
        if (this.students.contains(user)) {
            throw new IllegalStateException("이미 처리되었습니다.");
        }
        this.students.add(user);
        return user.getId();
    }

    public Long remove(NsUser user) {
        if (!this.students.contains(user)) {
            throw new IllegalStateException("승인 대기 상태가 아닙니다.");
        }
        this.students.remove(user);
        return user.getId();
    }

    public boolean contains(NsUser user) {
        return this.students.stream()
                .anyMatch(currentUser -> currentUser.matchUser(user));
    }
}
