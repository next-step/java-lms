package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    private Session SESSION;

    @BeforeEach
    void setUp() {
        SESSION = makeSession();
    }

    @DisplayName("강의모집인원 마감시 true를 리턴한다.")
    @Test
    void 강의_모집마감_검증() {
        assertAll(
                () -> assertThat(SESSION.isClosed(10)).isTrue(),
                () -> assertThat(SESSION.isClosed(15)).isTrue(),
                () -> assertThat(SESSION.isClosed(0)).isFalse()
        );
    }

    @DisplayName("강의모집인원 마감시 true를 리턴한다.")
    @Test
    void 강의_모집인원_초과_에러() {
        SESSION.recruiting();
        assertThatThrownBy(() -> {
            SESSION.apply(0L, NsUserTest.JAVAJIGI);
            SESSION.apply(1L, NsUserTest.SANJIGI);
        }).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("신청시 생성되는 신청정보에 결과 검증")
    @Test
    void 신청정보_생성_결과검증() {
        SESSION.recruiting();
        assertThat(SESSION.apply(0L, NsUserTest.SANJIGI)).isEqualTo(
                new SessionApply(0L, SESSION, NsUserTest.SANJIGI)
        );
    }

    private static Session makeSession() {
        return Session.open(0L,
                makeCourse(),
                LocalDate.now(),
                LocalDate.now(),
                "imageUrl",
                true,
                1);
    }

    private static Course makeCourse() {
        return new Course("title", 1L);
    }

}