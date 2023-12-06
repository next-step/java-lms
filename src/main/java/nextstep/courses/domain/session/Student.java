package nextstep.courses.domain.session;

public class Student {

    private final Long userId;

    public Student(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return userId;
    }
}
