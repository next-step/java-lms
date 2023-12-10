package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.exception.CannotEnrollException;
import nextstep.courses.exception.InvalidSessionDateException;
import nextstep.users.domain.NsUser;

public class Session {

    private Type type;

    private Status status;

    private int maxCountOfStudents = Integer.MAX_VALUE;

    private List<NsUser> students;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    public Session(Type type, Status status, int maxCountOfStudents, LocalDateTime startAt,
        LocalDateTime endAt) throws InvalidSessionDateException {
        validateStartTimeAndEndTime(startAt, endAt);
        this.students = new ArrayList<>();
        this.type = type;
        this.status = status;
        this.maxCountOfStudents = maxCountOfStudents;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private static void validateStartTimeAndEndTime(LocalDateTime startAt, LocalDateTime endAt)
        throws InvalidSessionDateException {
        if (startAt.isAfter(endAt)) {
            throw new InvalidSessionDateException("강의 시작일은 종료일보다 늦어질 수 없다");
        }
    }

    public void enrollStudent(NsUser user) throws CannotEnrollException {
        if (!this.status.equals(Status.OPEN)) {
            throw new CannotEnrollException("강의 수강신청은 강의 상태가 모집중일 때만 가능하다");
        }
        if (this.type.equals(Type.PAID) && students.size() >= maxCountOfStudents) {
            throw new CannotEnrollException("유료 강의는 강의 최대 수강 인원을 초과할 수 없다");
        }
        if (this.students.contains(user)) {
            throw new CannotEnrollException("강의는 중복 수강신청할 수 없다");
        }
        this.students.add(user);
    }
}
