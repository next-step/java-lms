package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SessionTest {
    private Session session;

    @BeforeEach
    public void sampleDataSetUp() {
        session = new Session();
    }

    @Test
    @DisplayName("[Session.registerUser()] 같은 사용자가 이미 등록한 강의에 또 등록하면 -> 예외 던짐")
    public void duplicateRegisterTest() {
        assertThatThrownBy(() -> {
            session.registerUser(NsUserTest.SANJIGI);
            session.registerUser(NsUserTest.SANJIGI);
        })
                .isInstanceOf(Exception.class);
    }
}