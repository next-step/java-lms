package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionStatus.RECRUITING;
import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {

    @Test
    void register_결과_확인() {
        //given
        Session session = new SessionBuilder()
                .withRegister(new RegisterBuilder()
                        .withStatus(RECRUITING)
                        .withMaxRegisterCount(2)
                        .withStudent(new NsUser())
                        .build()
                )
                .build();

        //when
        NsUsers nsUsers = session.register(new NsUser());

        //then
        assertThat(nsUsers.size()).isEqualTo(2);
    }
}
