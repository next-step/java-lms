package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class Session {
    private Long id;
    private List<NsUser> users;
    private SessionPayment sessionPayment;
    private Enrollment enrollment;
    private Duration duration;
    private SessionStatus sessionStatus;
    private CoverImage coverImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session() {
        this.id = 0L;
        this.duration = new Duration(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        this.sessionPayment = new SessionPayment(SessionPaymentType.FREE, 0L);
        this.enrollment = new Enrollment(new NsUsers(new ArrayList<>()),0);
        this.sessionStatus = SessionStatus.READY;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Session(Long id, SessionPayment sessionPayment, Enrollment enrollment, Duration duration, CoverImage coverImage) {
        this.id = id;
        this.duration = duration;
        validateCountOfEnrollments(sessionPayment, enrollment);
        this.sessionPayment = sessionPayment;
        this.enrollment = enrollment;
        this.sessionStatus = SessionStatus.READY;
        this.coverImage = coverImage;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private void validateCountOfEnrollments(SessionPayment sessionPayment, Enrollment enrollment) {
        if (!sessionPayment.isPaid() && enrollment.isNotEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public void enroll(NsUser user, Long amountOfPay){
        if(sessionStatus!=SessionStatus.ENROLLING){
            throw new IllegalArgumentException();
        }
        if(!sessionPayment.isSameAmountOfPay(amountOfPay)){
            throw new IllegalArgumentException();
        }
        enrollment.enroll(user);
        if(enrollment.isFull()){
            sessionStatus = SessionStatus.DONE;
        }
    }

    public boolean isSameId(Long id){
        return this.id.equals(id);
    }
}
