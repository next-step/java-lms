package nextstep.courses.domain;

import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.Objects;

public class Session {
    private static final int INCREMENTAL_VALUE = 1;
    private static final int DEFAULT_NUMBER_OF_STUDENTS_REGISTERED = 0;
    private static final SessionState DEFAULT_SESSION_STATE = SessionState.PREPARING;

    private final long id;
    private final int fixedNumberOfStudent;
    private final NsUser lecturer;
    private final LocalDate registrationDate;

    private Image imageCover;
    private SessionState sessionState;
    private SessionState recruitmentState;
    private SessionType sessionType;
    private int numberOfStudentsRegistered;
    private LocalDate startDate;
    private LocalDate endDate;

    private Session(
            long id,
            int fixedNumberOfStudent,
            NsUser lecturer,
            LocalDate registrationDate,
            LocalDate startDate,
            LocalDate endDate,
            Image imageCover,
            SessionState sessionState,
            SessionState recruitmentState,
            SessionType sessionType,
            int numberOfStudentsRegistered
    ) {

        LocalDate now = LocalDate.now();
        validateId(id);
        validateFixedNumberOfStudent(fixedNumberOfStudent);
        validateRegisterLecturer(lecturer);
        validateSessionType(sessionType);
        validateSessionState(sessionState);
        validateRecruitmentState(recruitmentState);
        validateNumberOfStudentsRegistered(numberOfStudentsRegistered, fixedNumberOfStudent);
        validateDate(startDate, endDate, now);

        if (Objects.isNull(sessionState)) {
            sessionState = getCurrentSessionState(now, startDate, endDate);
        }

        this.id = id;
        this.fixedNumberOfStudent = fixedNumberOfStudent;
        this.lecturer = lecturer;
        this.registrationDate = registrationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageCover = imageCover;
        this.sessionState = sessionState;
        this.sessionType = sessionType;
        this.numberOfStudentsRegistered = numberOfStudentsRegistered;
        this.recruitmentState = recruitmentState;
    }

    public static Session createSession(
            int fixedNumberOfStudent,
            NsUser lecturer,
            LocalDate startDate,
            LocalDate endDate,
            Image imageCover,
            SessionState sessionState,
            SessionState recruitmentState,
            SessionType sessionType
    ) {
        long id = SimpleIdGenerator.getAndIncrement(Session.class);

        if (Objects.isNull(sessionState)) {
            sessionState = DEFAULT_SESSION_STATE;
        }

        return new Session(
                id,
                fixedNumberOfStudent,
                lecturer,
                LocalDate.now(),
                startDate,
                endDate,
                imageCover,
                sessionState,
                recruitmentState,
                sessionType,
                DEFAULT_NUMBER_OF_STUDENTS_REGISTERED
        );
    }

    public static Session of(
            int id,
            int fixedNumberOfStudent,
            NsUser lecturer,
            LocalDate registrationDate,
            LocalDate startDate,
            LocalDate endDate,
            Image imageCover,
            SessionState sessionState,
            SessionState recruitmentState,
            SessionType sessionType,
            int numberOfStudentsRegistered
    ) {
        return new Session(
                id,
                fixedNumberOfStudent,
                lecturer,
                registrationDate,
                startDate,
                endDate,
                imageCover,
                sessionState,
                recruitmentState,
                sessionType,
                numberOfStudentsRegistered
        );
    }

    public Session changeImage(Image imageCover, NsUser requestUser) {
        validateOwner(requestUser, "이미지 변경");
        this.imageCover = imageCover;
        return this;
    }

    public Session changeSessionType(SessionType sessionType, NsUser requestUser) {
        validateOwner(requestUser, "강의 타입 변경");
        if (!sessionState.isAvailableManualChangeSession()) {
            throw new IllegalStateException("강의 타입 변경은 강의 준비중일때만 가능해요 :(");
        }

        if (!recruitmentState.isAvailableManualChangeSession()) {
            throw new IllegalStateException("수강생 모집중에는 강의 타입을 변경할 수 없어요 :(");
        }

        this.sessionType = sessionType;
        return this;
    }

    public Session registerLecture() {
        int register = increaseRegister();
        validateNumberOfStudentsRegistered(register, fixedNumberOfStudent);
        validateRegisterRecruitmentState(recruitmentState);

        this.numberOfStudentsRegistered = register;

        return this;
    }

    public Session syncSession(LocalDate now) {
        this.sessionState = getCurrentSessionState(now, startDate, endDate);
        return this;
    }

    public Session changeRecruitmentState(SessionState recruitmentState, NsUser requestUser) {
        validateOwner(requestUser, "모집 상태 변경");
        validateRecruitmentState(recruitmentState);

        this.recruitmentState = recruitmentState;

        return this;
    }

    private void validateRecruitmentState(SessionState recruitmentState) {

        if (Objects.isNull(recruitmentState)) {
            throw new IllegalArgumentException("모집 상태에 값이 입력 되지 않았어요 :(");
        }

        if (!recruitmentState.isAllowRecruitmentState()) {
            throw new IllegalArgumentException("모집 상태는 모집중/모집 종료만 가능해요 :( 제공된 모집 상태 [" + recruitmentState + "]");
        }
    }

    private void validateRegisterRecruitmentState(SessionState recruitmentState) {

        if (!recruitmentState.isAvailableRecruitment()) {
            throw new IllegalStateException("모집중인 강의가 아니에요 :(");
        }
    }

    private SessionState getCurrentSessionState(LocalDate now, LocalDate startDate, LocalDate endDate) {

        if (now.isAfter(endDate)) {
            return SessionState.FINISH;
        }

        if (now.isBefore(startDate)) {
            return SessionState.PREPARING;
        }

        return SessionState.PROGRESSING;
    }

    private int increaseRegister() {
        return Math.addExact(numberOfStudentsRegistered, INCREMENTAL_VALUE);
    }

    private void validateOwner(NsUser requestUser, String comment) {

        if (Objects.isNull(requestUser)) {
            throw new IllegalArgumentException("요청자가 입력되질 않았어요 :(");
        }

        if (lecturer != requestUser) {
            throw new IllegalArgumentException("강의자만 " + comment + " 가능해요 :( 강의자 [" + lecturer.getUserId() + "]");
        }
    }

    private void validateId(long id) {
        if (id == 0L) {
            throw new IllegalArgumentException("유효하지 않는 아이디에요 :( [입력 값 : " + id + "]");
        }
    }

    private void validateFixedNumberOfStudent(int fixedNumberOfStudent) {
        if (fixedNumberOfStudent == 0) {
            throw new IllegalArgumentException("강의의 정원은 0명 이상이여야 해요 :(");
        }
    }

    private void validateRegisterLecturer(NsUser lecturer) {
        if (Objects.isNull(lecturer)) {
            throw new IllegalArgumentException("강의자가 입력되질 않았어요 :(");
        }
    }

    private void validateSessionType(SessionType sessionType) {
        if (Objects.isNull(sessionType)) {
            throw new IllegalArgumentException("강의 타입이 입력되질 않았어요 :(");
        }
    }

    private void validateSessionState(SessionState sessionState) {
        if (Objects.isNull(sessionState)) {
            throw new IllegalArgumentException("강의 상태가 입력되질 않았어요 :(");
        }

        if (!sessionState.isAllowSessionState()) {
            throw new IllegalArgumentException("강의 상태는 모집 중/모집 종료를 입력하실수 없어요 :( 제공 된 강의 상태 [" + sessionState + "]");
        }
    }

    private void validateNumberOfStudentsRegistered(int numberOfStudentsRegistered, int fixedNumberOfStudent) {
        if (numberOfStudentsRegistered > fixedNumberOfStudent) {
            throw new IllegalStateException("등록인원이 정원을 초과 할 수 없어요 :( [정원 : " + fixedNumberOfStudent + "명 ]");
        }
    }

    private void validateDate(LocalDate startDate, LocalDate endDate, LocalDate now) {

        if (Objects.isNull(startDate)) {
            throw new IllegalArgumentException("강의 시작일이 등록되질 않았어요 :(");
        }

        if (Objects.isNull(endDate)) {
            throw new IllegalArgumentException("강의 종료일이 등록되질 않았어요 :(");
        }

        if (startDate.isBefore(now)) {
            throw new IllegalArgumentException("강의 시작 날짜가 현재 날짜보다 앞일 수 없습니다 :(");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("강의 종료 날짜가 강의 시작 날짜보다 앞일 수 없습니다 :(");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id && fixedNumberOfStudent == session.fixedNumberOfStudent && Objects.equals(lecturer, session.lecturer) && sessionState == session.sessionState && recruitmentState == session.recruitmentState && sessionType == session.sessionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fixedNumberOfStudent, lecturer, sessionState, recruitmentState, sessionType);
    }

    public int getNumberOfStudentsRegistered() {
        return numberOfStudentsRegistered;
    }
}
