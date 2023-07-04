package nextstep.courses.domain;

public class Student {
    private long id;
    private final long session_id;
    private final long user_id;

    public Student(long session_id, long user_id) {
        this.session_id = session_id;
        this.user_id = user_id;
    }

    public long getId() {
        return id;
    }

    public long getSession_id() {
        return session_id;
    }

    public long getUser_id() {
        return user_id;
    }
}
