package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private SessionStatus status;
    private SessionType type;
    private int price;
    private int maxCapacity;
    private List<NsUser> students;
    private LocalDate startDate;
    private LocalDate endDate;
    private SessionCoverImage coverImage;

    private Session(SessionStatus status, SessionType type, int price, int maxCapacity, List<NsUser> students, LocalDate startDate, LocalDate endDate, SessionCoverImage coverImage) {
        this.status = status;
        this.type = type;
        this.price = price;
        this.maxCapacity = maxCapacity;
        this.students = students;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
    }

    public static Session createFreeSession() {
        return new Session(
                SessionStatus.READY,
                SessionType.FREE,
                0,
                0,
                new ArrayList<>(),
                LocalDate.now(),
                LocalDate.now(),
                new SessionCoverImage()
        );
    }

    public static Session createPaidSession(int price, int maxCapacity) {
        return new Session(
                SessionStatus.READY,
                SessionType.PAID,
                price,
                maxCapacity,
                new ArrayList<>(),
                LocalDate.now(),
                LocalDate.now(),
                new SessionCoverImage()
        );
    }

    public void register(NsUser student) {
        checkRecruiting();
        checkMaxCapacity();

        students.add(student);
    }

    public void ready() {
        status = SessionStatus.READY;
    }

    public void startRecruiting() {
        status = SessionStatus.RECRUITING;
    }

    public void close() {
        status = SessionStatus.CLOSED;
    }

    private void checkRecruiting() {
        if (status != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException("본 강의는 수강생을 모집하고 있지 않습니다.");
        }
    }

    private void checkMaxCapacity() {
        if (type == SessionType.PAID && students.size() >= maxCapacity) {
            throw new IllegalArgumentException("최대 수강 인원(" + maxCapacity + "명)에 도달하여 수강 신청이 불가능합니다.");
        }
    }
}
