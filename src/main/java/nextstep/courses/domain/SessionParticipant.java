package nextstep.courses.domain;

public class SessionParticipant {

    private static final int MAXIMUM_STUDENT = 30;
    private final int period;

    private int students;

    public SessionParticipant(int period) {
        this.period = period;
    }

    public SessionParticipant(int period, int students) {
        this.period = period;
        this.students = students;
    }

    public boolean isFullSession() {
        return students > MAXIMUM_STUDENT;
    }

    public void participateStudent() {
        students++;
    }

    public int getStudents() {
        return students;
    }
}
