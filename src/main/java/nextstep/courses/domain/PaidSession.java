package nextstep.courses.domain;

public class PaidSession extends Session {
    private final int AVAILABLE_STUDENTS_COUNT = 100;

    private int studentCount;

    public PaidSession(String title, Course course) {
        super(title, course);
    }

    public PaidSession(String title, Course course, int studentCount) {
        super(title, course);
        this.studentCount = studentCount;
    }

    // 수강생 증가
}
