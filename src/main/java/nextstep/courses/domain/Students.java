package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<Student> values = new ArrayList<>();


    public void add(Student student, int maxUser) {
        if (this.values.size() >= maxUser) {
            throw new RuntimeException("강의는 강의 최대 수강 인원을 초과할 수 없습니다.");
        }

        values.add(student);
    }
}
