package nextstep.courses.dto;

import nextstep.payments.domain.Payment;

public class RequestSessionDto {

    String courseTitle;

    String sessionName;

    Payment payment;

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getSessionName() {
        return sessionName;
    }

    public Payment getPayment() {
        return payment;
    }

    public RequestSessionDto(String courseTitle, String sessionName, Payment payment) {
        this.courseTitle = courseTitle;
        this.sessionName = sessionName;
        this.payment = payment;
    }
}
