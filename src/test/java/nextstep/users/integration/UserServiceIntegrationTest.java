package nextstep.users.integration;

import nextstep.users.domain.NsUser;
import nextstep.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Transactional
    @Test
    void testCanApprove() {
        userService.saveUser(new NsUser("400", "password", "test", "javajigi@slipp.net", "우아한테크코스"));
        userService.saveUser(new NsUser("500", "password", "test", "javajigi@slipp.net", "강사"));

        assertThat(userService.canApprove("500", "400")).isTrue();
    }

    @Transactional
    @Test
    void testCanNotApprove() {
        userService.saveUser(new NsUser("400", "password", "test", "javajigi@slipp.net", "비 선발 인원"));
        userService.saveUser(new NsUser("500", "password", "test", "javajigi@slipp.net", "강사"));

        assertThat(userService.canApprove("500", "400")).isFalse();
    }


    @Transactional
    @Test
    void testCanCancel() {
        userService.saveUser(new NsUser("400", "password", "test", "javajigi@slipp.net", "비 선발 인원"));
        userService.saveUser(new NsUser("500", "password", "test", "javajigi@slipp.net", "강사"));

        assertThat(userService.canCancel("500", "400")).isTrue();
    }

    @Transactional
    @Test
    void testCanNotCancel() {
        userService.saveUser(new NsUser("400", "password", "test", "javajigi@slipp.net", "우아한테크코스"));
        userService.saveUser(new NsUser("500", "password", "test", "javajigi@slipp.net", "강사"));

        assertThat(userService.canCancel("500", "400")).isFalse();
    }
}
