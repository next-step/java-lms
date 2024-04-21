package nextstep.courses.dto;

import nextstep.payments.domain.Payment;

public class RequestSessionDto {

    private Long courseId;

    private Long sessionId;

    private Payment payment;

    private String sessionType;

    public RequestSessionDto(Long courseId, Long sessionId, Payment payment, String sessionType) {
        this.courseId = courseId;
        this.sessionId = sessionId;
        this.payment = payment;
        this.sessionType = sessionType;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Payment getPayment() {
        return payment;
    }

    public String getSessionType() {
        return sessionType;
    }
}
