package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.exception.InvalidSessionDateException;
import nextstep.users.domain.NsUser;

public class Session {

    private Long sessionId;

    private Long price;

    private Type type;

    private Status status;

    private int maxCountOfStudents;

    private List<NsUser> students;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    public Session(Long sessionId, Type type, Status status, Long price, int maxCountOfStudents,
        LocalDateTime startAt,
        LocalDateTime endAt) throws InvalidSessionDateException {
        validateStartTimeAndEndTime(startAt, endAt);
        this.sessionId = sessionId;
        this.students = new ArrayList<>();
        this.type = type;
        this.status = status;
        this.price = price;
        this.maxCountOfStudents = maxCountOfStudents;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Session(Long sessionId, Type type, Status status, LocalDateTime startAt,
        LocalDateTime endAt) throws InvalidSessionDateException {
        this(sessionId, type, status, 0L, Integer.MAX_VALUE, startAt, endAt);
    }


    private static void validateStartTimeAndEndTime(LocalDateTime startAt, LocalDateTime endAt)
        throws InvalidSessionDateException {
        if (startAt.isAfter(endAt)) {
            throw new InvalidSessionDateException("강의 시작일은 종료일보다 늦어질 수 없다");
        }
    }

    public boolean isPaid() {
        return this.type.equals(Type.PAID);
    }

    public void addStudent(NsUser user) {
        this.students.add(user);
    }

    public Long getPrice() {
        return price;
    }

    public boolean isOpen() {
        return this.status.equals(Status.OPEN);
    }

    public boolean duplicateUser(NsUser user) {
        return this.students.contains(user);
    }

    public boolean isMaxStudents() {
        return students.size() >= maxCountOfStudents;
    }
}
