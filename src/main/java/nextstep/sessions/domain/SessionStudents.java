package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SessionStudents {

    private List<NsUser> students;
    private Map<Long, NsUser> studentMap;

    public SessionStudents(List<NsUser> students) {
        this.students = students;
        this.studentMap = students.stream()
                .collect(Collectors.toMap(NsUser::getId, Function.identity()));
    }

    public boolean contains(Long nsUserId) {
        NsUser nsUser = studentMap.get(nsUserId);
        return nsUser != null;
    }

    public void enrollStudent(NsUser nsUser) {
        if (contains(nsUser.getId())) {
            throw new IllegalArgumentException("이미 등록된 학생입니다");
        }

        students.add(nsUser);
    }

    public int getCurrentStudentCount() {
        return students.size();
    }
}
