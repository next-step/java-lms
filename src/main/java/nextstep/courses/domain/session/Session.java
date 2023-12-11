package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.domain.image.Image;
import nextstep.courses.exception.CannotEnrollException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {

    private Long sessionId;

    private Long price;

    private Image image;

    private SessionType sessionType;

    private Status status;

    private int maxCountOfStudents;

    private List<NsUser> students;

    private Date date;

    public Session(Long sessionId, SessionType sessionType, Status status, Long price, Image image,
        int maxCountOfStudents, Date date) {
        this.sessionId = sessionId;
        this.image = image;
        this.date = date;
        this.students = new ArrayList<>();
        this.sessionType = sessionType;
        this.status = status;
        this.price = price;
        this.maxCountOfStudents = maxCountOfStudents;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Session(Long sessionId, SessionType sessionType, Status status, Date date, Image image) {
        this(sessionId, sessionType, status, 0L, image, Integer.MAX_VALUE, date);
    }

    public void enroll(NsUser user, Payment payment) throws CannotEnrollException {
        if (!isOpen()) {
            throw new CannotEnrollException("강의 수강신청은 강의 상태가 모집중일 때만 가능하다");
        }
        if (isPaid() && isMaxStudents()) {
            throw new CannotEnrollException("유료 강의는 강의 최대 수강 인원을 초과할 수 없다");
        }
        if (duplicateUser(user)) {
            throw new CannotEnrollException("강의는 중복 수강신청할 수 없다");
        }
        if (isPaid() && !payment.isValid(this.price)) {
            throw new CannotEnrollException("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다");
        }
        addStudent(user);
    }


    public boolean isPaid() {
        return this.sessionType.equals(SessionType.PAID);
    }

    public void addStudent(NsUser user) {
        this.students.add(user);
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
