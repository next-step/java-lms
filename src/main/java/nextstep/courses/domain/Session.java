package nextstep.courses.domain;

import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {

    private static final int DEFAULT_NUMBER_OF_STUDENTS_REGISTERED = 0;
    private static final SessionState DEFAULT_SESSION_STATE = SessionState.PREPARING;


    private final long id;
    private final int fixedNumberOfStudent;
    private final NsUser lecturer;
    private final LocalDateTime registrationDate;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    private Image imageCover;
    private SessionState sessionState;
    private SessionType sessionType;
    private int numberOfStudentsRegistered;

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
