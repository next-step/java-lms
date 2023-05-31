package nextstep.lms.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    public static final Session CLASS_ONE = new Session(
            1L, LocalDate.of(2023, 5, 22), LocalDate.of(2023, 5, 22),
            SessionState.PROGRESS, SessionRecruitingState.RECRUITING, SessionPaidType.FREE, 0, 5, 0L);

    private Session session;

    void setUp(int studentCapacity) {
        LocalDate startDate = LocalDate.of(2023, 5, 22);
        LocalDate endDate = LocalDate.of(2023, 5, 23);
        Long imageCover = 0L;

        session = new Session(startDate, endDate, imageCover, SessionPaidType.FREE, studentCapacity);
        session.changeRecruitingState();
    }

    @Test
    @DisplayName("강의 시작일과 종료일 유효성 체크")
    void validateSessionDateTest() {
        LocalDate startDate = LocalDate.of(2023, 5, 22);
        LocalDate endDate = LocalDate.of(2023, 5, 21);

        assertThatThrownBy(() -> new Session(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("종료 날짜가 시작 날짜보다 앞일 수 없습니다.");
    }

    @Test
    @DisplayName("강의 이미지 커버 수정 기능")
    void changeImageCoverTest() {
        setUp(0);
        Long changeCover = 1L;

        assertThat(session.changeImageCover(changeCover))
                .isEqualTo(changeCover);
    }

    @Test
    @DisplayName("강의 진행중으로 변경")
    void progressStateTest() {
        setUp(0);

        assertThat(session.changeProgressState())
                .isEqualTo(SessionState.PROGRESS);
    }

    @Test
    @DisplayName("강의 종료로 변경")
    void finishStateTest() {
        setUp(0);

        assertThat(session.changeFinishSessionState())
                .isEqualTo(SessionState.FINISH);
    }

    @Test
    @DisplayName("종료일이 지났을 때, 강의 상태 종료로 변경")
    void finishSessionTest() {
        setUp(0);

        LocalDate now = LocalDate.of(2023, 5, 24);

        assertThat(session.changeFinishSessionStateByDate(now))
                .isEqualTo(SessionState.FINISH);
    }

    @Test
    @DisplayName("종료일이 지나지 않았으면 강의 상태가 종료로 바뀌지 않음")
    void notFinishSessionTest() {
        setUp(0);

        LocalDate now = LocalDate.of(2023, 5, 23);

        assertThat(session.changeFinishSessionStateByDate(now))
                .isEqualTo(SessionState.READY);
    }

    @Test
    @DisplayName("모집 중이 아닐 때 수강 신청 에러")
    void notRecruitingErrorTest() {
        int studentCapacity = 5;
        setUp(studentCapacity);
        session.changeStoppedState();

        assertThatThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청 인원 초과 시 수강 신청 에러")
    void overStudentCapacityErrorTest() {
        int studentCapacity = 2;
        setUp(studentCapacity);

        session.changeProgressState();
        session.enroll(NsUserTest.JAVAJIGI);
        session.enroll(NsUserTest.SANJIGI);

        assertThatThrownBy(() -> session.enroll(NsUserTest.BADAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("학생 강의 수강 신청 기능")
    void registerTest() {
        int studentCapacity = 5;
        setUp(studentCapacity);
        session.changeProgressState();
        session.enroll(NsUserTest.JAVAJIGI);

        assertThat(session.getStudentCapacity().getRegisteredStudent())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("학생 수강 취소 기능")
    void cancelTest() {
        int studentCapacity = 5;
        setUp(studentCapacity);
        session.changeProgressState();
        Student javajigiStudent = session.enroll(NsUserTest.JAVAJIGI);
        session.enroll(NsUserTest.SANJIGI);

        session.cancel(javajigiStudent);

        assertThat(session.getStudentCapacity().getRegisteredStudent())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("강의 상태가 준비중이 아닐 때 강의 타입 변경 에러")
    void notReadySessionTypeErrorTest() {
        setUp(0);
        session.changeProgressState();

        assertThatThrownBy(() -> session.changeSessionType(SessionPaidType.PAID))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의 타입 변경")
    void changeSessionTypeTest() {
        setUp(0);

        assertThat(session.changeSessionType(SessionPaidType.PAID))
                .isEqualTo(SessionPaidType.PAID);
    }

    @Test
    @DisplayName("강의 개설 기능")
    void createSessionTest() {
        LocalDate startDate = LocalDate.of(2023, 5, 22);
        LocalDate endDate = LocalDate.of(2023, 5, 25);
        Long imageId = 0L;
        SessionPaidType sessionPaidType = SessionPaidType.FREE;
        int studentCapacity = 5;

        Session session = Session.createSession(startDate, endDate, imageId, sessionPaidType, studentCapacity);

        assertThat(session)
                .isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("강의가 진행 중인 상태에서도 수강신청 가능")
    void sessionProgressRecruitingSuccessTest() {
        Long id = 0L;
        LocalDate startDate = LocalDate.of(2023, 5, 22);
        LocalDate endDate = LocalDate.of(2024, 5, 25);
        SessionState sessionState = SessionState.PROGRESS;
        SessionPaidType sessionPaidType = SessionPaidType.FREE;
        SessionRecruitingState sessionRecruitingState = SessionRecruitingState.RECRUITING;
        int registeredStudent = 3;
        int studentCapacity = 10;
        Long imageId = 0L;

        Session session = new Session(id, startDate, endDate, sessionState, sessionRecruitingState,
                sessionPaidType, registeredStudent, studentCapacity, imageId);
        session.enroll(NsUserTest.JAVAJIGI);

        assertThat(session.getStudentCapacity().getRegisteredStudent())
                .isEqualTo(4);
    }

    @Test
    @DisplayName("강의 모집 상태 변경 - 모집중 -> 비모집중")
    void changeSessionRecruitingStateStoppedTest() {
        setUp(5);

        assertThat(session.changeStoppedState())
                .isEqualTo(SessionRecruitingState.STOPPED);
    }

    @Test
    @DisplayName("강의 모집 상태 변경 - 비모집중 -> 모집중")
    void changeSessionRecruitingStateRecruitingTest() {
        setUp(5);

        assertThat(session.changeRecruitingState())
                .isEqualTo(SessionRecruitingState.RECRUITING);
    }

    @Test
    @DisplayName("강의 종료일 때 모집중으로 변경할 경우 에러 테스트")
    void sessionFinishRecruitingErrorTest() {
        setUp(5);
        session.changeFinishSessionState();

        assertThatThrownBy(() -> session.changeRecruitingState())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("선발되지 않은 학생은 강사가 취소 테스트")
    void nonSelectedStudentDropTest() {
        setUp(5);
        Student javajigiStudent = session.enroll(NsUserTest.JAVAJIGI);
        javajigiStudent.changeStudentSelect();
        javajigiStudent.getStudentApprovedType();
        Student sanjigiStudent = session.enroll(NsUserTest.SANJIGI);
        Student badajigiStudent = session.enroll(NsUserTest.BADAJIGI);
        List<Student> students = List.of(javajigiStudent, sanjigiStudent, badajigiStudent);

        List<Student> canceledStudents = session.dropNonSelectedStudent(students);

        assertThat(session.getStudentCapacity().getRegisteredStudent())
                .isEqualTo(1);
        assertThat(canceledStudents)
                .hasSize(2);
    }

}
