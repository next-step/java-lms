package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Image;

import java.time.LocalDate;
import java.util.List;

public class Session {
    private final SessionDuration sessionDuration;
    private final Image coverImage;
    private final int fee;
    private final int maxStudents;
    private SessionStatus sessionStatus = SessionStatus.READY;

    public Session(LocalDate startDate, LocalDate endDate, Image coverImage) {
        this.sessionDuration = new SessionDuration(startDate, endDate);
        this.coverImage = coverImage;
        this.fee = 0;
        this.maxStudents = Integer.MAX_VALUE;
    }

    public Session(LocalDate startDate, LocalDate endDate, Image coverImage, int fee, int maxStudents) {
        this.sessionDuration = new SessionDuration(startDate, endDate);
        this.coverImage = coverImage;
        this.fee = fee;
        this.maxStudents = maxStudents;
    }

    public Enrollment enrollment() {
        if (!sessionStatus.isApplying()) {
            throw new RuntimeException("수강 신청은 강의 상태가 모집중이 아니면 불가능합니다. 강의 상태 : " + sessionStatus);
        }

        return new Enrollment(fee, maxStudents);
    }

    public void changeStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}
