package nextstep.courses.domain;

import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private static final int INCREMENTAL_VALUE = 1;
    private static final int DEFAULT_NUMBER_OF_STUDENTS_REGISTERED = 0;
    private static final SessionState DEFAULT_SESSION_STATE = SessionState.PREPARING;

    private final long id;
    private final int fixedNumberOfStudent;
    private final NsUser lecturer;
    private final LocalDateTime registrationDate;

    private Image imageCover;
    private SessionState sessionState;
    private SessionType sessionType;
    private int numberOfStudentsRegistered;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Session(
            long id,
            int fixedNumberOfStudent,
            NsUser lecturer,
            LocalDateTime registrationDate,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Image imageCover,
            SessionState sessionState,
            SessionType sessionType,
            int numberOfStudentsRegistered
    ) {


        validateId(id);
        validateFixedNumberOfStudent(fixedNumberOfStudent);
        validateRegisterLecturer(lecturer);
        validateSessionType(sessionType);
        validateSessionState(sessionState);
        validateNumberOfStudentsRegistered(numberOfStudentsRegistered, fixedNumberOfStudent);
        validateDate(startDate, endDate);

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
    }

    public static Session createSession(int fixedNumberOfStudent, NsUser lecturer, LocalDateTime startDate, LocalDateTime endDate, Image imageCover, SessionState sessionState, SessionType sessionType) {
        long id = SimpleIdGenerator.getAndIncrement(Session.class);

        if (Objects.isNull(sessionState)) {
            sessionState = DEFAULT_SESSION_STATE;
        }

        return new Session(id, fixedNumberOfStudent, lecturer, LocalDateTime.now(), startDate, endDate, imageCover, sessionState, sessionType, DEFAULT_NUMBER_OF_STUDENTS_REGISTERED);
    }

    public Session changeImage(Image imageCover, NsUser requestUser) {
        validateOwner(requestUser);
        this.imageCover = imageCover;
        return this;
    }

    public Session changeSessionType(SessionType sessionType, NsUser requestUser) {
        validateOwner(requestUser);
        if (sessionState.isAvailableManualChangeSession()) {
            throw new IllegalStateException("강의 타입 변경은 강의 준비중일때만 가능해요 :(");
        }

        this.sessionType = sessionType;
        return this;
    }

    public Session registerForALecture() {
        int register = increaseRegister();
        validateNumberOfStudentsRegistered(register, fixedNumberOfStudent);

        if (sessionState.isAvailableRecruitment()) {
            throw new IllegalStateException("강의 준비중일때만 수강 신청이 가능해요 :(");
        }

        this.numberOfStudentsRegistered = register;

        return this;
    }

    private int increaseRegister() {
        return Math.addExact(numberOfStudentsRegistered, INCREMENTAL_VALUE);
    }

    private void validateOwner(NsUser requestUser) {

        if (Objects.isNull(requestUser)) {
            throw new IllegalArgumentException("요청가 입력되질 않았어요 :( ");
        }

        if (lecturer != requestUser) {
            throw new IllegalArgumentException("강의를 등록하신분이 아니에요 :(");
        }
    }

    private void validateId(long id) {
        if (id == 0L) {
            throw new IllegalArgumentException("유효하지 않는 아이디에요 :( [입력 값 : " + id + "]");
        }
    }

    private void validateFixedNumberOfStudent(int fixedNumberOfStudent) {
        if (fixedNumberOfStudent == 0L) {
            throw new IllegalArgumentException("강의의 정원은 0명 이상이여야 해요 :(");
        }
    }

    private void validateRegisterLecturer(NsUser lecturer) {
        if (Objects.isNull(lecturer)) {
            throw new IllegalArgumentException("강의자가 입력되질 않았어요 :( ");
        }
    }

    private void validateSessionType(SessionType sessionType) {
        if (Objects.isNull(sessionType)) {
            throw new IllegalArgumentException("강의 타입이 입력되질 않았어요 :( ");
        }
    }

    private void validateSessionState(SessionState sessionState) {
        if (Objects.isNull(sessionState)) {
            throw new IllegalArgumentException("강의 상태가 입력되질 않았어요 :( ");
        }
    }

    private void validateNumberOfStudentsRegistered(int numberOfStudentsRegistered, int fixedNumberOfStudent) {
        if (numberOfStudentsRegistered > fixedNumberOfStudent) {
            throw new IllegalArgumentException("등록인원이 정원을 초과 할 수 없어요 :( [정원 : " + fixedNumberOfStudent + "명 ]");
        }
    }

    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime now = LocalDateTime.now();

        if (Objects.isNull(startDate)) {
            throw new IllegalArgumentException("강의 시작일이 등록되질 않았어요 :( ");
        }

        if (Objects.isNull(endDate)) {
            throw new IllegalArgumentException("강의 종료일이 등록되질 않았어요 :( ");
        }

        if (now.toLocalDate().isAfter(startDate.toLocalDate())) {
            throw new IllegalArgumentException("강의 시작 날짜가 현재 날짜보다 앞일 수 없습니다.");
        }

        if (endDate.toLocalDate().isBefore(startDate.toLocalDate())) {
            throw new IllegalArgumentException("강의 종료 날짜가 강의 시작 날짜보다 앞일 수 없습니다.");
        }
    }

}
