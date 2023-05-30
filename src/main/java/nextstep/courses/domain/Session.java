package nextstep.courses.domain;

import java.time.LocalDate;

import nextstep.users.domain.NsUser;

public class Session {
    private CardinalNumber cardinalNumber;
    private SessionType sessionType;
    private SessionStatus sessionStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private Enrolment enrolment;
    private MaximumClassSize maximumClassSize;

    private Image image;

    public Session(SessionStatus sessionStatus, MaximumClassSize maximumClassSize) {
        this.sessionStatus = sessionStatus;
        this.maximumClassSize = maximumClassSize;
        this.enrolment = new Enrolment();
    }

    public Session() {
        this.image = new Image();
    }

    public Session(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public Session(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public Session(int cardinalNumber) {
        this.cardinalNumber = new CardinalNumber(cardinalNumber);
    }
    public Session(CardinalNumber cardinalNumber, LocalDate startDate, LocalDate endDate) {
        this.cardinalNumber = cardinalNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    boolean isStartDateSame(LocalDate localDate) {
        return startDate == localDate;
    }

    boolean isEndDateSame(LocalDate localDate) {
        return endDate == localDate;
    }

    public void updateImageWithUrl(String imgUrl) {
        image.updateUrl(imgUrl);
    }

    public boolean isSameCoverImage(String imgUrl) {
        return image.isSameImage(imgUrl);
    }

    public boolean isFree() {
        return sessionType.isFree();
    }

    public boolean isReady() {
        return sessionStatus.isReady();
    }

    public boolean isOpen() {
        return sessionStatus.isOpen();
    }

    public boolean isClosed() {
        return sessionStatus.isClosed();
    }

    public boolean isEnrolmentPossible() {
        return isOpen() && !isMoreThanMaximumClassSize();
    }

    private boolean isMoreThanMaximumClassSize() {
        return enrolment.count() >= maximumClassSize.maxSize();
    }

    public void enroll(NsUser user) {
        if (!isEnrolmentPossible()) {
            throw new IllegalStateException("수강 인원이 초과되었습니다.");
        }
        enrolment.enroll(user);
    }

}
