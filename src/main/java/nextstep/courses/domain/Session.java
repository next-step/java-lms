package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Session {

    private SessionDate sessionDate;

    private SessionImage coverImage;
    private SessionProgress progress;
    private boolean isFree;

    public Session(LocalDate startAt, LocalDate endAt, SessionImage coverImage) {
        this(new SessionDate(startAt, endAt), coverImage);
    }

    public Session(SessionDate sessionDate, SessionImage coverImage) {
        this(sessionDate, coverImage, new SessionProgress("준비중"));
    }

    public Session(LocalDate startAt, LocalDate endAt, SessionImage coverImage, SessionProgress progress) {
        this(new SessionDate(startAt, endAt), coverImage, progress);
    }

    public Session(SessionDate sessionDate, SessionImage coverImage, SessionProgress progress) {
        this(sessionDate, coverImage, progress, true);
    }

    public Session(LocalDate startedAt, LocalDate endedAt, SessionImage coverImage, SessionProgress progress, boolean isFree) {
        this(new SessionDate(startedAt, endedAt), coverImage, progress, isFree);
    }

    public Session(SessionDate sessionDate, SessionImage coverImage, SessionProgress progress, boolean isFree) {
        this.sessionDate = sessionDate;
        this.coverImage = coverImage;
        this.progress = progress;
        this.isFree = isFree;
    }

    public boolean isRecruitState() {
        return this.progress.equals(new SessionProgress(SessionProgress.RECRUIT));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof Session))
            return false;
        Session session = (Session) object;
        return Objects.equals(sessionDate, session.sessionDate)
                && Objects.equals(coverImage, session.coverImage)
                && Objects.equals(progress, session.progress)
                && Objects.equals(isFree, session.isFree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionDate, coverImage, progress, isFree);
    }

}
