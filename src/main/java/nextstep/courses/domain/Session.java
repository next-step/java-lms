package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Session {

    private Long id;
    private SessionDate sessionDate;

    private SessionImage coverImage;
    private SessionProgress progress;
    private boolean isFree;
    private Students students = new Students(new ArrayList<>());
    private int maxStudents = Integer.MAX_VALUE;

    public Session(Long id, SessionDate sessionDate, SessionImage coverImage) {
        this(id, sessionDate, coverImage, new SessionProgress("준비중"));
    }

    public Session(Long id, LocalDate startAt, LocalDate endAt, SessionImage coverImage, SessionProgress progress) {
        this(id, new SessionDate(startAt, endAt), coverImage, progress);
    }

    public Session(Long id, SessionDate sessionDate, SessionImage coverImage, SessionProgress progress) {
        this(id, sessionDate, coverImage, progress, true);
    }

    public Session(Long id, LocalDate startedAt, LocalDate endedAt, SessionImage coverImage, SessionProgress progress, boolean isFree) {
        this(id, new SessionDate(startedAt, endedAt), coverImage, progress, isFree);
    }

    public Session(Long id, SessionDate sessionDate, SessionImage coverImage, SessionProgress progress, boolean isFree) {
        this.id = id;
        this.sessionDate = sessionDate;
        this.coverImage = coverImage;
        this.progress = progress;
        this.isFree = isFree;
    }

    public boolean isRecruitState() {
        return this.progress.equals(new SessionProgress(SessionProgress.RECRUIT));
    }

    public void setMaxStudents(int maxStudents) {
        if (isFree) {
            return;
        }
        this.maxStudents = maxStudents;
    }

    public void checkAddStudent(Student student) {
        this.students.addStudent(student, this.maxStudents);
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
