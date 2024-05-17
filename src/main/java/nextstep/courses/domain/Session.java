package nextstep.courses.domain;

import nextstep.image.domain.Image;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final String title;
    private final Long sessionId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Image image;
    private final SessionCost cost;
    private final SessionStatus status;
    private final List<Student> students = new ArrayList<>();
    private final int limit;

    public Session(String title, Long sessionId, LocalDateTime startDate, LocalDateTime endDate, Image image, SessionCost cost, SessionStatus status, int limit) {
        this.title = title;
        this.sessionId = sessionId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.cost = cost;
        this.status = status;
        this.limit = limit;
    }

    public void apply(Payment payment) {
        sessionIdCheck(payment);
        if (status.check() && cost.paymentCheck(payment)) {
            limitStudents();
            students.add(new Student(payment.getNsUserId()));
        }
    }

    private void sessionIdCheck(Payment payment) {
        if (this.sessionId == payment.getSessionId())
            throw new IllegalArgumentException("다른 강의를 신청했습니다");
    }

    private void limitStudents() {
        if (students.size() > limit)
            throw new IllegalArgumentException("최대 수강 인원을 초과할 수 없습니다");
    }
}
