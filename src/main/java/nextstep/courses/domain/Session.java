package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final Long id;
    private final String title;
    private final LocalDate startAt;
    private final LocalDate endAt;
    private final Image coverImage;
    private final ChargeType chargeType;
    private final long price;
    private final int studentCapacity;
    private SessionStatus sessionStatus;
    private final List<NsUser> students = new ArrayList<>();

    public Session(Long id, String title, LocalDate startAt, LocalDate endAt, Image coverImage,
                   ChargeType chargeType, long price, int studentCapacity, SessionStatus sessionStatus) {
        this.id = id;
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.coverImage = coverImage;
        this.chargeType = chargeType;
        this.price = price;
        this.studentCapacity = studentCapacity;
        this.sessionStatus = sessionStatus;
    }

    public void enroll(NsUser student, int payment) throws CannotEnrollException {
        if (isFull()) {
            throw new CannotEnrollException("최대수강인원을 초과할 수 없습니다.");
        }

        if (price != payment) {
            throw new CannotEnrollException("결제한 금액이 수강료와 다릅니다.");
        }

        if (!sessionStatus.isEnrolling()) {
            throw new CannotEnrollException("모집 중인 강의가 아닙니다.");
        }

        students.add(student);
    }

    private boolean isFull() {
        return students.size() == studentCapacity;
    }

    public int enrolledStudents() {
        return students.size();
    }

    public void closeSession() {
        this.sessionStatus = SessionStatus.CLOSED;
    }

}
