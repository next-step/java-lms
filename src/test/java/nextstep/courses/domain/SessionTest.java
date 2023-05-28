package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    private static final Session SESSION = makeSession();

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
            SESSION.apply(NsUserTest.JAVAJIGI);
            SESSION.apply(NsUserTest.SANJIGI);
        }).isInstanceOf(IllegalStateException.class);
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