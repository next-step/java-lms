package nextstep.courses.domain.student;

public class Student {

    private final int period;

    private final String name;

    public Student(int period, String name) {
        this.period = period;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int period() {
        return period;
    }
}
