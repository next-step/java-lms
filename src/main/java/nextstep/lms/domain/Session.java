package nextstep.lms.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Session {

    private LocalDate startedAt;
    private LocalDate endedAt;
    private String coverImage;
    private Type type;
    private Status status;
    private int numberOfRegisteredStudent = 0;
    private int maxNumberOfStudents;

    private Session(){

    }

    public Session(LocalDate startedAt, LocalDate endedAt) {
        this(startedAt, endedAt, "cover", Type.FREE, Status.READY, 30);
    }

    public Session(Status status) {
        this(LocalDate.now(), LocalDate.now().plusDays(30), "cover", Type.FREE, status, 30);
    }

    public Session(int maxNumberOfStudents) {
        this.startedAt = LocalDate.now();
        this.endedAt = LocalDate.now().plusDays(30);
        this.coverImage = "image";
        this.type = Type.FREE;
        this.status = Status.RECRUIT;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public Session(LocalDate startedAt, LocalDate endedAt, String coverImage, Type type, Status status, int maxNumberOfStudents) {
        validateDate(startedAt, endedAt);
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.coverImage = coverImage;
        this.type = type;
        this.status = status;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }


    public void register() {
        validateCapacityOfStudent();
        validateStatusOfSession();
        numberOfRegisteredStudent++;
    }

    private void validateDate(LocalDate startedAt, LocalDate endedAt) {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("강의 시작일은 강의 종료일 보다 빨라야 합니다.");
        }
    }

    private void validateStatusOfSession() {
        if (Status.isNotRecruit(status)) {
            throw new IllegalStateException("현재 강의 모집 중이 아닙니다.");
        }
    }

    private void validateCapacityOfStudent() {
        if (numberOfRegisteredStudent + 1 > maxNumberOfStudents) {
            throw new IllegalStateException("현재 최대 수강 인원이 초과 되었습니다.");
        }
    }

    public int getNumberOfRegisteredStudent() {
        return numberOfRegisteredStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return type == session.type && maxNumberOfStudents == session.maxNumberOfStudents && Objects.equals(startedAt, session.startedAt) && Objects.equals(endedAt, session.endedAt) && Objects.equals(coverImage, session.coverImage) && status == session.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startedAt, endedAt, coverImage, type, status, maxNumberOfStudents);
    }
}
