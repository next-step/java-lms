package nextstep.users.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NsUsersTest {

    private static final NsUsers USERS = new NsUsers();

    static {
        USERS.add(NsUserTest.JAVAJIGI);
    }

    @Test
    void NsUsers의_인원_수가_입력된_값보다_크거나_같으면_true를_반환한다() {
        assertThat(USERS.hasExceededMaxCapacity(1)).isTrue();
    }

    @Test
    void NsUsers의_인원_수가_입력된_값보다_작으면_false를_반환한다() {
        assertThat(USERS.hasExceededMaxCapacity(2)).isFalse();
    }

    @Test
    void 유저가_포함되어_있다면_true를_반환한다() {
        assertThat(USERS.contains(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    void 유저가_포함되어_있지_않다면_false를_반환한다() {
        assertThat(USERS.contains(NsUserTest.SANJIGI)).isFalse();
    }
}
