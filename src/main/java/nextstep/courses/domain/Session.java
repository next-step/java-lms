package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Session {

    private Long id;
    private SessionDate sessionDate;
    private SessionImage coverImage;
    private SessionProgress progress;
    private SessionType sessionType;
    private Students students;

    public Session(Long id, SessionDate sessionDate, SessionImage coverImage) {
        this(id, sessionDate, coverImage, new SessionProgress("준비중"));
    }

    public Session(Long id, LocalDate startAt, LocalDate endAt, SessionImage coverImage, SessionProgress progress) {
        this(id, new SessionDate(startAt, endAt), coverImage, progress);
    }

    public Session(Long id, SessionDate sessionDate, SessionImage coverImage, SessionProgress progress) {
        this(id, sessionDate, coverImage, progress, new SessionType(true));
    }

    public Session(Long id, LocalDate startedAt, LocalDate endedAt, SessionImage coverImage, SessionProgress progress, SessionType sessionType) {
        this(id, new SessionDate(startedAt, endedAt), coverImage, progress, sessionType);
    }

    public Session(Long id, SessionDate sessionDate, SessionImage coverImage, SessionProgress progress, SessionType sessionType) {
        this(id, sessionDate, coverImage, progress, sessionType, new Students(new ArrayList<>()));
    }

    public Session(Long id, SessionDate sessionDate, SessionImage coverImage, SessionProgress progress, SessionType sessionType, Students students) {
        this.id = id;
        this.sessionDate = sessionDate;
        this.coverImage = coverImage;
        this.progress = progress;
        this.sessionType = sessionType;
        this.students = students;
    }

    public boolean isRecruitState() {
        return this.progress.compareWithRecruit(progress);
    }

    public boolean isAlreadyFull() {
        return this.sessionType.compareStudents(this.students);
    }

    public void successApply(Student student) {
        this.students.addStudent(student);
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
                && Objects.equals(sessionType, session.sessionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionDate, coverImage, progress, sessionType);
    }
}
