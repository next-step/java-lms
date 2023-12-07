package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.Objects;

import static nextstep.courses.domain.PaidType.FREE;
import static nextstep.courses.domain.PaidType.PAID;
import static nextstep.courses.domain.SessionStatus.APPLYING;


public class Session {
    private Long id;
    private Long courseId;
    private Period period;
    private Image image;
    private PaidType paidType;
    private Integer maxStudentNumber;
    private Integer appliedNumber;
    private Long sessionFee;
    private SessionStatus sessionStatus;

    public Session(Long courseId, LocalDateTime startDate, LocalDateTime endDate, Long imageId, String paidType, Integer maxStudentNumber, Integer appliedNumber, Long sessionFee, String sessionStatus) {
        this(0L, courseId, startDate, endDate, imageId, paidType, maxStudentNumber, appliedNumber, sessionFee, sessionStatus);
    }

    public Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, Long imageId, String paidType, Integer maxStudentNumber, Integer appliedNumber, Long sessionFee, String sessionStatus) {
        this(id, courseId, startDate, endDate, Image.newImage(imageId), PaidType.valueOf(paidType.toUpperCase()), maxStudentNumber, appliedNumber, sessionFee, SessionStatus.valueOf(sessionStatus.toUpperCase()));
    }

    private Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, Image image, PaidType paidType, Integer maxStudentNumber, Integer appliedNumber, Long sessionFee, SessionStatus sessionStatus) {
        this.id = id;
        this.courseId = courseId;
        this.period = new Period(startDate, endDate);
        this.image = image;
        this.paidType = paidType;
        this.maxStudentNumber = maxStudentNumber;
        this.appliedNumber = appliedNumber;
        this.sessionFee = sessionFee;
        this.sessionStatus = sessionStatus;
    }

    public static Session freeSession(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, Image image, Integer appliedNumber, SessionStatus sessionStatus) {
        return new Session(id, courseId, startDate, endDate, image, FREE, null, appliedNumber, null, sessionStatus);
    }

    public static Session paidSession(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, Image image, Integer maxStudentNumber, Integer appliedNumber, Long sessionFee, SessionStatus sessionStatus) {
        return new Session(id, courseId, startDate, endDate, image, PAID, maxStudentNumber, appliedNumber, sessionFee, sessionStatus);
    }

    public void enroll(Payment payment) {
        if (isFullOfStudents()) {
            throw new IllegalArgumentException("유료 강의는 강의 최대 수강 인원을 초과할 수 없습니다.");
        }
        if (isValidPayAmount(payment)) {
            throw new IllegalArgumentException("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능합니다.");
        }
        if (isNotStatusToSignUp()) {
            throw new IllegalArgumentException("강의 수강신청은 강의 상태가 모집 중일 때만 가능합니다.");
        }

        increaseAppNumber();
    }

    public boolean isFullOfStudents() {
        return paidType.equals(PAID) && maxStudentNumber <= appliedNumber;
    }

    public boolean isValidPayAmount(Payment payment) {
        return paidType.equals(PAID) && !Objects.equals(payment.getAmount(), sessionFee);
    }

    public boolean isNotStatusToSignUp() {
        return sessionStatus != APPLYING;
    }

    public void increaseAppNumber() {
        this.appliedNumber = appliedNumber + 1;
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Period getPeriod() {
        return period;
    }

    public Image getImage() {
        return image;
    }

    public PaidType getPaidType() {
        return paidType;
    }

    public Integer getMaxStudentNumber() {
        return maxStudentNumber;
    }

    public Integer getAppliedNumber() {
        return appliedNumber;
    }

    public Long getSessionFee() {
        return sessionFee;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }
}
