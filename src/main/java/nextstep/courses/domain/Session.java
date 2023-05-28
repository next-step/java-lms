package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String coverUrl;

    private Progress progress;

    private BillType billType;

    private long maxEnrollment;

    private Course course;

    public Session() {
    }

    public Session(LocalDate startDate, LocalDate endDate, String coverUrl, Progress progress, BillType billType, long maxEnrollment, Course course) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverUrl = coverUrl;
        this.progress = progress;
        this.billType = billType;
        this.maxEnrollment = maxEnrollment;
        this.course = course;
    }

    public enum Progress {
        NOT_STARTED, IN_PROGRESS, FINISHED
    }

    public enum BillType {
        FREE, PAID
    }
}
