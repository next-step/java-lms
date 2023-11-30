package nextstep.lms.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FreeSessionTest {
    FreeSession recruitingFreeSession;
    FreeSession completedFreeSession;

    @BeforeEach
    void setting() {
        recruitingFreeSession = new FreeSession(
                "무료강의(모집중)",
                LocalDateTime.of(2023, 10, 30, 0, 0),
                LocalDateTime.of(2023, 12, 14, 23, 59),
                new CoverImage("next.png", 0.8, 300, 200),
                SessionStatus.RECRUITING,
                new ArrayList<>());
        completedFreeSession = new FreeSession(
                "무료강의(모집마감)",
                LocalDateTime.of(2023, 10, 30, 0, 0),
                LocalDateTime.of(2023, 12, 14, 23, 59),
                new CoverImage("next.png", 0.8, 300, 200),
                SessionStatus.COMPLETED,
                new ArrayList<>());
    }

    @DisplayName("수강신청은 강의 상태가 모집중일 때만 가능")
    @Test
    void 중복_신청_불가능() throws IllegalArgumentException {
        recruitingFreeSession.enroll(NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> recruitingFreeSession.enroll(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 수강중인 강의입니다.");
    }

    @DisplayName("수강신청은 강의 상태가 모집중일 때만 가능")
    @Test
    void 강의_마감() throws IllegalArgumentException {
        assertThatThrownBy(() -> completedFreeSession.enroll(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강신청 기간이 아닙니다.");
    }

    @DisplayName("최대 수강 인원 제한이 없다")
    @Test
    void 자유로운_수강신청() {
        assertThat(recruitingFreeSession.enroll(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(recruitingFreeSession.enroll(NsUserTest.SANJIGI)).isTrue();
    }
}