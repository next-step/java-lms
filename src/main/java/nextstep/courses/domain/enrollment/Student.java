package nextstep.courses.domain.enrollment;

public class Student {

    private Long id;
    private Long nsUserId;

    public Student(Long id, Long nsUserId) {
        this.id = id;
        this.nsUserId = nsUserId;
    }

}