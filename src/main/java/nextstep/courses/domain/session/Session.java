package nextstep.courses.domain.session;

import nextstep.common.BaseEntity;
import nextstep.courses.domain.ragistration.Registration;

import java.time.LocalDateTime;

public class Session extends BaseEntity {
    private SessionInfo sessionInfo;
    private CoverImage coverImage;
    private SessionDate sessionDate;
    private Price price;

    public Session(Long id, SessionInfo sessionInfo, CoverImage coverImage, SessionDate sessionDate, Price price) {
        this(id, LocalDateTime.now(), null, sessionInfo, coverImage, sessionDate, price);
    }

    public Session(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, SessionInfo sessionInfo, CoverImage coverImage, SessionDate sessionDate, Price price) {
        super(id, createdAt, updatedAt);

        this.sessionInfo = sessionInfo;
        this.coverImage = coverImage;
        this.sessionDate = sessionDate;
        this.price = price;
    }

    public void checkIsRegister(int studentsCount) {
        this.sessionInfo.checkIsRegister(studentsCount);
    }

    public void registerSession(Registration registration) {
        if (registration.isApprove()) {
            this.registerSession(1);
        }
    }

    public void registerSession(int studentsCount) {
        this.sessionInfo.registerSession(studentsCount);
        super.setUpdatedAt();
    }

    public boolean isSession(Long sessionId) {
        return super.getId().equals(sessionId);
    }

    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public Price getPrice() {
        return price;
    }
}
