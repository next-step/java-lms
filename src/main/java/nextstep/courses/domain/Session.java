package nextstep.courses.domain;

public class Session {

    private Long id;

    private Image coverImage;

    private SessionEnrollment enrollment;

    private SessionSchedule schedule;

    public Session() {
    }

    public Session(Image coverImage, SessionEnrollment enrollment, SessionSchedule schedule) {
        this(0L, coverImage, enrollment, schedule);
    }

    public Session(Long id, Image coverImage, SessionEnrollment enrollment, SessionSchedule schedule) {
        this.id = id;
        this.coverImage = coverImage;
        this.enrollment = enrollment;
        this.schedule = schedule;
    }


}
