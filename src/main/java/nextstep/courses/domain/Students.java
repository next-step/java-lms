package nextstep.courses.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Students {
    private static final String ALREADY_STUDENT = "이미 등록된 학생 입니다.";
    private static final String NOT_INFO = "등록된 학생 정보가 없습니다.";
    private static final String NULL_EXCEPTION = "학생 데이터가 잘못 생성되었습니다.";
    private Map<Long, Student> studentsMap = new HashMap<>();
    public Students() {

    }

    public void putEntity(Student student) {
        if (Objects.isNull(student)) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }

        if (studentsMap.containsKey(student.getId())) {
            throw new IllegalArgumentException(ALREADY_STUDENT);
        }
        addStudent(student);
    }

    public void removeEntity(Student student) {
        if (Objects.isNull(student)) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }

        if (!studentsMap.containsKey(student.getId())) {
            throw new IllegalArgumentException(NOT_INFO);
        }
        removeSession(student);
    }

    private void addStudent(Student student) {
        studentsMap.put(student.getId(), student);
    }

    private void removeSession(Student student) {
        studentsMap.remove(student.getId());
    }

    public int getSize() {
        return studentsMap.size();
    }
}
