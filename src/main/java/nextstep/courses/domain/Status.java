package nextstep.courses.domain;

import nextstep.courses.exception.NotOpenSessionException;

import java.time.LocalDate;

public class Status {

    private SessionStatus sessionStatus;
    private RecruitmentStatus recruitmentStatus;

    public Status(LocalDate now, LocalDate startDate, LocalDate endDate) {
        this(SessionStatus.of(now, startDate, endDate), RecruitmentStatus.NOT_RECRUITMENT);
    }

    public Status(LocalDate now, LocalDate startDate, LocalDate endDate, RecruitmentStatus recruitmentStatus) {
        this(SessionStatus.of(now, startDate, endDate), recruitmentStatus);
    }

    public Status(SessionStatus sessionStatus, RecruitmentStatus recruitmentStatus) {
        this.sessionStatus = sessionStatus;
        this.recruitmentStatus = recruitmentStatus;
    }

    public void validate() {
        if (!sessionStatus.isInProgress() && !recruitmentStatus.isRecruiting()) {
            throw new NotOpenSessionException();
        }
    }

    public void startRecruiting() {
        this.recruitmentStatus = recruitmentStatus.ofRecruiting();
    }

    public String sessionStatus() {
        return this.sessionStatus.name();
    }

    public String recruitmentStatus() {
        return this.recruitmentStatus.name();
    }
}
