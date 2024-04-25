package nextstep.courses.domain.session;

import java.time.LocalDate;

public class SessionEntity {
    private Long id;
    private String name;
    private String coverImage;
    private int fee;
    private SessionProgressStatus sessionProgressStatus;
    private SessionApplyStatus sessionApplyStatus;
    private int maxStudents;
    private LocalDate startDate;
    private LocalDate endDate;

    public SessionEntity() {

    }

    public SessionEntity(Long id, String name, String coverImage, int fee, SessionProgressStatus sessionProgressStatus, SessionApplyStatus sessionApplyStatus, int maxStudents, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.coverImage = coverImage;
        this.fee = fee;
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionApplyStatus = sessionApplyStatus;
        this.maxStudents = maxStudents;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
