package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Students {
    private static final int MAX_STUDENTS = 20;
    private static final String MAX_STUDENT_EXCEPTION = "최대 인원을 초과하였습니다.";
    private static final String ALREADY_STUDENT = "이미 등록된 학생 입니다.";
    private static final String NOT_INFO = "등록된 학생 정보가 없습니다.";
    private static final String NULL_EXCEPTION = "학생 데이터가 잘못 생성되었습니다.";
    private Map<Long, NsUser> studentsMap = new HashMap<>();
    private List<NsUser> users = new ArrayList<>();

    public Students() {
    }

    public Students(List<NsUser> users) {
        this.users = users;
    }

    public void convertStudentListToMap() {
        studentsMap = users.stream()
                .collect(Collectors.toMap(NsUser::getId, Function.identity(), (pre, post) -> pre));
    }

    public void enroll(NsUser nsUser) {
        if (studentsMap.size() > MAX_STUDENTS) {
            throw new IllegalArgumentException(MAX_STUDENT_EXCEPTION);
        }

        if (Objects.isNull(nsUser)) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }

        if (studentsMap.containsKey(nsUser.getId())) {
            throw new IllegalArgumentException(ALREADY_STUDENT);
        }
        addStudent(nsUser);
    }

    public void removeEntity(NsUser nsUser) {
        if (Objects.isNull(nsUser)) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }

        if (!studentsMap.containsKey(nsUser.getId())) {
            throw new IllegalArgumentException(NOT_INFO);
        }
        removeSession(nsUser);
    }

    public static Students createStudents(List<NsUser> nsUsers) {
        Students students = new Students();
        nsUsers.stream().forEach(
                nsUser -> students.enroll(nsUser)
        );
        return students;
    }

    private void addStudent(NsUser nsUser) {
        studentsMap.put(nsUser.getId(), nsUser);
    }

    private void removeSession(NsUser nsUser) {
        studentsMap.remove(nsUser.getId());
    }

    public int getSize() {
        return studentsMap.size();
    }
}
