package nextstep.courses.domain;

public class FreeSession extends Session {

    private int studentCount;

    public FreeSession(String title, Course course) {
        super(title, course);
        this.studentCount = 0;
    }

    @Override
    public void signUp() {
        this.studentCount += 1;
    }
}
