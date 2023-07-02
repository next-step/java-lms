package nextstep.courses.domain;

import nextstep.courses.domain.enums.ProgressState;
import nextstep.courses.domain.enums.RecruitmentState;
import nextstep.courses.exception.DuplicateStudentException;
import nextstep.courses.exception.EnrollFullException;
import nextstep.courses.exception.SessionExpiredException;
import nextstep.courses.exception.SessionNotOpenException;
import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SessionStatusTest {
    private static final NsUser jerry = new NsUser(1L, "jerry", "password", "name", "jerry@nextstep.com");
    private static final NsUser joy = new NsUser(2L, "joy", "password", "조이", "joy@nextstep.com");
    private static final NsUser david = new NsUser(3L, "david","password","데이빗","david@gamil.com");

    @Test
    void 생성자테스트() {
        Assertions.assertThat(new SessionStatus(30)).isInstanceOf(SessionStatus.class);
    }

    @Test
    void 중복등록불가테스트() {
        SessionStatus sessionStatus = new SessionStatus(2, ProgressState.PROCEEDING, RecruitmentState.RECRUITING);

        sessionStatus.enroll(jerry, 1L);

        assertThatThrownBy(() -> {
            sessionStatus.enroll(jerry, 1L);
        }).isInstanceOf(DuplicateStudentException.class).hasMessageContaining("이미 강의에 등록된 유저입니다.");
    }

    @Test
    void 최대인원초과테스트() {
        SessionStatus sessionStatus = new SessionStatus(1, ProgressState.PROCEEDING, RecruitmentState.RECRUITING);

        sessionStatus.enroll(jerry, 1L);

        assertThatThrownBy(() -> {
            sessionStatus.enroll(david, 1L);
        }).isInstanceOf(EnrollFullException.class).hasMessageContaining("최대 수강 인원을 초과하여 신청이 불가합니다.");
    }

    @Test
    void 강의신청불가_강의종료() {
        SessionStatus sessionStatus = new SessionStatus(1, ProgressState.END, RecruitmentState.RECRUITING);

        assertThatThrownBy(() -> {
            sessionStatus.enroll(david, 1L);
        }).isInstanceOf(SessionNotOpenException.class).hasMessageContaining("강의가 종료되어 신청이 불가합니다.");
    }

    @Test
    void 강의신청불가_비모집중() {
        SessionStatus sessionStatus = new SessionStatus(1, ProgressState.PROCEEDING, RecruitmentState.NOT_RECRUITING);

        assertThatThrownBy(() -> {
            sessionStatus.enroll(david, 1L);
        }).isInstanceOf(SessionNotOpenException.class).hasMessageContaining("강의가 비모집중으로 신청이 불가합니다.");
    }
}
