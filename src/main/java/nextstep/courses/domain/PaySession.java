package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class PaySession extends Session {

    private final int maxCountOfStudents;

    public PaySession(Image image, Period period, int maxCountOfStudents) {
        super(image, period, SessionType.PAY);
        this.maxCountOfStudents = maxCountOfStudents;
    }

    public void enroll(NsUser students) {
        if (this.maxCountOfStudents <= this.getStudents().size()) {
            throw new IllegalArgumentException("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.");
        }
        this.getStudents().add(students);
    }

    public int getMaxCountOfStudents() {
        return this.maxCountOfStudents;
    }
}
