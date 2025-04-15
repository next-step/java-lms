package nextstep.courses.domain;

import java.util.Objects;

public class SessionMeta {
    private final SessionType sessionType;
    private final SessionStatus sessionStatus;
    private final NsImage coverImage;

    public SessionMeta(SessionType sessionType, SessionStatus sessionStatus, NsImage coverImage) {
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
    }

    public boolean isRecruiting() {
        return sessionStatus.isRecruiting();
    }

    public boolean isFree() {
        return sessionType.isFree();
    }

    public boolean isPaid() {
        return sessionType.isPaid();
    }

    public SessionMeta startRecruiting() {
        return new SessionMeta(sessionType, SessionStatus.RECRUITING, coverImage);
    }

    public SessionMeta finishRecruiting() {
        return new SessionMeta(sessionType, SessionStatus.CLOSED, coverImage);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SessionMeta that = (SessionMeta) o;
        return sessionType == that.sessionType && sessionStatus == that.sessionStatus && Objects.equals(coverImage, that.coverImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionType, sessionStatus, coverImage);
    }
}
