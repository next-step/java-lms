package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import nextstep.courses.CannotRegisterException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {

    private long id;
    private String title;
    private SessionType sessionType;
    private SessionState state;
    private String image;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Course course;

    private List<NsUser> users = new ArrayList<>();

    private int studentCapacity;

    private long fee;

    public Session(long id, String title, SessionType sessionType, SessionState state, String image,
            LocalDateTime startDate, LocalDateTime endDate, int studentCapacity, long fee) {
        this.id = id;
        this.title = title;
        this.sessionType = sessionType;
        this.state = state;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.studentCapacity = studentCapacity;
        this.fee = fee;
    }

    public Session(long id, String title, SessionType sessionType, SessionState state, String image,
            LocalDateTime startDate, LocalDateTime endDate) {
        this(id, title, sessionType, state, image, startDate, endDate, 0, 0);
    }

    public void toCourse(Course course) {
        this.course = course;
    }

    public void register(NsUser user) throws CannotRegisterException {
        isRegisteredAllowed();
        users.add(user);
    }

    public void register(NsUser user, Payment payment) throws CannotRegisterException {
        validateFee(payment);
        register(user);
    }

    private void isRegisteredAllowed() throws CannotRegisterException {
        if (!state.isAllowed()) {
            throw new CannotRegisterException(String.format("해당 강의 상태 %s에서는 수강 신청을 할 수 없습니다.", state.getState()));
        }

        if (sessionType.isPaid() && !isExceedStudentCapacity()) {
            throw new CannotRegisterException("신청 가능한 수강인원이 가득 찼습니다.");
        }
    }

    private boolean isExceedStudentCapacity() {
        return users.size() + 1 < studentCapacity;
    }

    private void validateFee(Payment payment) throws CannotRegisterException {
        if (!payment.isEqualAmount(fee)) {
            throw new CannotRegisterException("수강생이 결제한 금액과 수강료가 일치하지 않습니다.");
        }
    }
}
