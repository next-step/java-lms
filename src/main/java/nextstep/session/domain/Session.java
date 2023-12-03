package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private static final SessionStatus DEFAULT_SESSION_STATUS = SessionStatus.PREPARING;

    private Long id;

    private int generation;

    private Long creatorId;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    private LocalDate startDate;

    private LocalDate endDate;

    private SessionImage sessionImage;

    private SessionStatus sessionStatus = DEFAULT_SESSION_STATUS;

    private SessionType sessionType;

    private Integer limitNumberOfStudents;
    private List<NsUser> students = new ArrayList<>();
    private Long price;

    public Session(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionType sessionType, Integer limitNumberOfStudents) {
        validateSessionType(sessionType, limitNumberOfStudents);
        this.generation = generation;
        this.creatorId = creatorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionImage = sessionImage;
        this.sessionType = sessionType;
        this.limitNumberOfStudents = limitNumberOfStudents;
    }

    public Session(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionType sessionType, Integer limitNumberOfStudents, Long price) {
        validateSessionType(sessionType, limitNumberOfStudents);
        this.generation = generation;
        this.creatorId = creatorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionImage = sessionImage;
        this.sessionType = sessionType;
        this.limitNumberOfStudents = limitNumberOfStudents;
        this.price = price;
    }

    public static Session create(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        return new Session(generation, creatorId, startDate, endDate, sessionImage, SessionType.FREE, null);
    }

    public static Session create(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionType sessionType, Integer limitNumberOfStudents, Long price) {
        return new Session(generation, creatorId, startDate, endDate, sessionImage, sessionType, limitNumberOfStudents, price);
    }

    private static void validateSessionType(SessionType sessionType, Integer limitNumberOfStudents) {
        if (sessionType == SessionType.FREE && limitNumberOfStudents != null) {
            throw new IllegalArgumentException("무료강의는 최대 수강 인원 제한이 없습니다.");
        }
        if (sessionType == SessionType.PAID && limitNumberOfStudents == null) {
            throw new IllegalArgumentException("유료강의는 최대 수강 인원 제한이 있습니다.");
        }
    }

    public void enroll(NsUser user) {
        validateEnroll();
        validatePayment(user);
        students.add(user);
    }

    private void validateEnroll() {
        if (this.sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 신청 가능합니다.");
        }

        if (isPaid() && this.limitNumberOfStudents == students.size()) {
            throw new IllegalStateException("수강신청 정원이 가득찼습니다.");
        }
    }

    private void validatePayment(NsUser user) {
        if (isFree()) {
            return;
        }
        if (!user.getSessionPayment(this).validateSameAmount(price)) {
            throw new IllegalArgumentException("강의의 가격과 결제한 가격이 다릅니다.");
        }
    }

    private boolean isPaid() {
        return this.sessionType == SessionType.PAID;
    }

    private boolean isFree() {
        return this.sessionType == SessionType.FREE;
    }

    public List<NsUser> getStudents() {
        return students;
    }

    public void changeStatus(SessionStatus status) {
        sessionStatus = status;
    }
}
