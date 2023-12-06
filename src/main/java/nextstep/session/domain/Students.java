package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Students {
    private List<NsUser> students;

    public Students(List<NsUser> students) {
        this.students = students;
    }

    public NsUser add(NsUser nsUser) {
        students.stream()
                .filter(s -> s.equals(nsUser))
                .findAny()
                .ifPresent(s -> {
                    throw new IllegalArgumentException("이미 존재하는 학생입니다.");
                });
        students.add(nsUser);
        return nsUser;
    }

    public int size() {
        return students.size();
    }
}
