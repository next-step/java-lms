package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class Session {
    private String title;
    private Charge charge;
    private Enrollment enrollment;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long createId;
    private String image;

    public Session(String title, Charge charge, Enrollment enrollment, LocalDate startDate, LocalDate endDate, Long createId, String image) {
        this.title = title;
        this.charge = charge;
        this.enrollment = enrollment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createId = createId;
        this.image = image;
    }

    public void enroll(NsUser nsUser) {
        enrollment.enroll(nsUser);
    }
}
