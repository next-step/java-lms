package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public abstract class Session {
    protected Long id;
    protected String name;
    protected Period period;
    protected Image coverImage;
    protected SessionStatus status;
    protected Students registeredStudents;

    public Session(Long id, String name, Period period, Image coverImage, SessionStatus status) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.coverImage = coverImage;
        this.status = status;
        this.registeredStudents = new Students();
    }

    public Students getRegisteredStudent() {
        return registeredStudents;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public String getName() {
        return name;
    }

    public Period getPeriod() {
        return period;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public void register(Long studentId, Payment payment) {
        if (status != SessionStatus.RECRUITING) {
            throw new IllegalStateException("수강 신청은 모집중일 때만 가능합니다.");
        }

        validateRegistration(studentId, payment);
        registeredStudents.addStudent(new Student(studentId));
    }

    protected abstract void validateRegistration(Long studentId, Payment payment);

    public abstract SessionType getType();
}