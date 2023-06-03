package nextstep.lms.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Session {

    private Long id;
    private SessionDate sessionDate;
    private Long imageId;
    private SessionState sessionState;
    private SessionRecruitingState sessionRecruitingState;
    private SessionPaidType sessionPaidType;
    private StudentCapacity studentCapacity;

    public Session(LocalDate startDate, LocalDate endDate) {
        this(null, startDate, endDate,
                SessionState.READY, SessionRecruitingState.STOPPED, SessionPaidType.FREE, 0,
                1, null);
    }

    public Session(LocalDate startDate, LocalDate endDate, Long imageId,
                   SessionPaidType sessionPaidType, int studentCapacity) {
        this(null, startDate, endDate,
                SessionState.READY, SessionRecruitingState.STOPPED, sessionPaidType, 0,
                studentCapacity, imageId);
    }

    public Session(Long id, LocalDate startDate, LocalDate endDate,
                   SessionState sessionState, SessionRecruitingState sessionRecruitingState, SessionPaidType sessionPaidType,
                   int registeredStudent, int studentCapacity, Long imageId) {
        this.id = id;
        this.sessionDate = new SessionDate(startDate, endDate);
        this.sessionState = sessionState;
        this.sessionRecruitingState = sessionRecruitingState;
        this.sessionPaidType = sessionPaidType;
        this.studentCapacity = new StudentCapacity(studentCapacity, registeredStudent);
        this.imageId = imageId;
    }

    public static Session createSession(LocalDate startDate, LocalDate endDate, Long imageId,
                                        SessionPaidType sessionPaidType, int studentCapacity) {
        return new Session(startDate, endDate, imageId, sessionPaidType, studentCapacity);
    }

    public Long changeImageCover(Long changeCover) {
        this.imageId = changeCover;
        return imageId;
    }

    public SessionState changeProgressState() {
        sessionState = SessionState.PROGRESS;
        return sessionState;
    }

    public SessionState changeFinishSessionState() {
        sessionState = SessionState.FINISH;
        return sessionState;
    }

    public SessionState changeFinishSessionStateByDate(LocalDate date) {
        if (sessionDate.isAfterEndDate(date)) {
            sessionState = SessionState.FINISH;
        }
        return sessionState;
    }

    public Student enroll(NsUser nsUser) {
        validateSessionRecruitingState();
        studentCapacity.enroll();

        return Student.init(nsUser, this);
    }

    private void validateSessionRecruitingState() {
        if (!sessionRecruitingState.equals(SessionRecruitingState.RECRUITING)) {
            throw new IllegalArgumentException("모집 중이 아닙니다.");
        }
    }

    public Student cancel(Student student) {
        student.sessionCancel();
        studentCapacity.cancel();

        return student;
    }

    public SessionPaidType changeSessionType(SessionPaidType sessionPaidType) {
        validateReadyState();
        this.sessionPaidType = sessionPaidType;
        return sessionPaidType;
    }

    private void validateReadyState() {
        if (!sessionState.equals(SessionState.READY)) {
            throw new IllegalArgumentException("수정 기간이 지났습니다.");
        }
    }

    public SessionRecruitingState changeStoppedState() {
        this.sessionRecruitingState = SessionRecruitingState.STOPPED;
        return sessionRecruitingState;
    }

    public SessionRecruitingState changeRecruitingState() {
        validateSessionStateIsNotFinished();
        this.sessionRecruitingState = SessionRecruitingState.RECRUITING;
        return sessionRecruitingState;
    }

    private void validateSessionStateIsNotFinished() {
        if (sessionState.equals(SessionState.FINISH)) {
            throw new IllegalArgumentException("강의가 종료되었습니다.");
        }
    }

    public List<Student> dropNonSelectedStudent(List<Student> students) {
        return students.stream()
                .filter(Student::isNonSelected)
                .peek(this::cancel)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public Long getImageId() {
        return imageId;
    }

    public String getSessionState() {
        return sessionState.toString();
    }

    public String getSessionRecruitingState() {
        return sessionRecruitingState.toString();
    }

    public String getSessionPaidType() {
        return sessionPaidType.toString();
    }

    public StudentCapacity getStudentCapacity() {
        return studentCapacity;
    }
}
