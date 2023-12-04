package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Students {

    private List<NsUser> userList;

    public Students(NsUser... users) {
        this.userList = new ArrayList<>(List.of(users));
    }

    public Students(List<NsUser> userList) {
        this.userList = userList;
    }

    public Students() {
        this.userList = new ArrayList<>();
    }

    public Students registerSessionStudent(NsUser nsUser, SessionType sessionType) {
        this.userList.add(nsUser);
        if (sessionType.isMaxCapacity(this)) {
            throw new MaxStudentsExceedException("수강인원을 초과하여 신청할 수 없습니다.");
        }
        return this;
    }

    public int size(){
        return this.userList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return Objects.equals(userList, students.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userList);
    }
}
