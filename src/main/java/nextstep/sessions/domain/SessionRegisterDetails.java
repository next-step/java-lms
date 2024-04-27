package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionRegisterDetails {

    private final long id;

    private CountOfStudent countOfStudent;

    private final Price price;

    private final SessionStatus sessionStatus;

    private List<NsUser> students;

    private Session session;

    private int maxOfStudents;

    public SessionRegisterDetails(long id, Price price, SessionStatus sessionStatus, int maxOfStudents) {
        this.id = id;
        this.price = price;
        this.sessionStatus = sessionStatus;
        this.maxOfStudents = maxOfStudents;
    }

    public SessionRegisterDetails(
            int currentCountOfStudents,
            int maxOfStudents,
            long price,
            SessionType sessionType,
            SessionStatus sessionStatus
    ) {
        this(currentCountOfStudents, maxOfStudents, price, sessionType, sessionStatus, new ArrayList<>());
    }

    public SessionRegisterDetails(
            int currentCountOfStudents,
            int maxOfStudents,
            long price,
            SessionType sessionType,
            SessionStatus sessionStatus,
            List<NsUser> students
    ) {
        this(new CountOfStudent(currentCountOfStudents, maxOfStudents, sessionType), new Price(price), sessionStatus, students);
    }

    public SessionRegisterDetails(
            CountOfStudent countOfStudent,
            Price price,
            SessionStatus sessionStatus,
            List<NsUser> students
    ) {
        this(0L, countOfStudent, price, sessionStatus, students);
    }

    public SessionRegisterDetails(
            long id,
            CountOfStudent countOfStudent,
            Price price,
            SessionStatus sessionStatus
    ) {
        this(id, countOfStudent, price, sessionStatus, new ArrayList<>());
    }

    public SessionRegisterDetails(
            long id,
            CountOfStudent countOfStudent,
            Price price,
            SessionStatus sessionStatus,
            List<NsUser> students
    ) {
        this(id, countOfStudent, price, sessionStatus, students, null);
    }

    public SessionRegisterDetails(long id, CountOfStudent countOfStudent, Price price, SessionStatus recruiting, List<NsUser> students, Session session) {
        this.id = id;
        this.countOfStudent = countOfStudent;
        this.price = price;
        this.sessionStatus = recruiting;
        this.students = students;
        this.session = session;
    }

    public void register(NsUser student, Payment payment) {
        if (this.sessionStatus.isNotRecruiting()) {
            throw new IllegalArgumentException(String.format("현재 강의는 (%s)인 상태입니다.", this.sessionStatus));
        }
        if (this.price.isNotSamePrice(payment)) {
            throw new IllegalArgumentException("결제한 금액이 강의의 가격과 일치하지 않습니다.");
        }
        this.countOfStudent.increaseCountOfStudents();
        students.add(student);
    }

    public void enroll(Student student, List<Student> enrolledStudents, Payment payment) {
        if (this.sessionStatus.isNotRecruiting()) {
            throw new IllegalArgumentException(String.format("현재 강의는 (%s)인 상태입니다.", this.sessionStatus));
        }
        if (this.price.isNotSamePrice(payment)) {
            throw new IllegalArgumentException("결제한 금액이 강의의 가격과 일치하지 않습니다.");
        }
        Students students = new Students(this.maxOfStudents, SessionType.PAID, enrolledStudents);
        students.enroll(student);
    }

    public boolean isNotSamePrice(long amount) {
        return price.isNotSamePrice(amount);
    }

    public boolean isContainsListener(NsUser listener) {
        return students.contains(listener);
    }

    public int getMaxCountOfStudents() {
        return countOfStudent.getMaxOfStudents();
    }

    public String getSessionType() {
        return countOfStudent.getSessionType();
    }

    public long getPrice() {
        return price.getPrice();
    }

    public String getSessionStatus() {
        return sessionStatus.name();
    }

    public long getId() {
        return id;
    }


    public int getCurrentCountOfStudents() {
        return countOfStudent.getCurrentCountOfStudents();
    }

    public long getSessionId() {
        return session.getId();
    }

}
