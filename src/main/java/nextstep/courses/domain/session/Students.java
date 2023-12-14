package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private List<Long> studentsId = new ArrayList<>();

    public Students() {

    }

    public void add(Long userId) {
        this.studentsId.add(userId);
    }

    public final boolean isGreaterThanOrEqualTo(Long maxStudentsNumber) {
        return this.studentsId.size() >= maxStudentsNumber;
    }
}
