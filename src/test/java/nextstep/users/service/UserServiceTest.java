package nextstep.users.service;

import nextstep.stub.domain.TestNsUser;
import nextstep.stub.repository.TestUserRepository;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserServiceTest {

    @DisplayName("유저 조회")
    @Test
    void testGetUser() {
        NsUser nsUser = new NsUser("test-user", "password", "name", "test@naver.com");

        TestUserRepository testUserRepository = new TestUserRepository(Map.of("test-user", nsUser));
        UserService userService = new UserService(testUserRepository);

        assertThat(userService.getUser("test-user")).isEqualTo(nsUser);
    }

    @DisplayName("복수 유저 조회")
    @Test
    void testGetUsers() {
        NsUser nsUser1 = new NsUser("test-user1", "password", "name", "test@naver.com");
        NsUser nsUser2 = new NsUser("test-user2", "password", "name", "test@naver.com");

        TestUserRepository testUserRepository = new TestUserRepository(Map.of(
            "test-user1", nsUser1,
            "test-user2", nsUser2
        ));
        UserService userService = new UserService(testUserRepository);

        assertThat(userService.getUsers(List.of("test-user1", "test-user2")))
            .containsAll(List.of(nsUser1, nsUser2));
    }

    @DisplayName("유저 저장")
    @Test
    void testSaveUser() {
        NsUser nsUser = new NsUser("test-user", "password", "name", "test@naver.com");
        TestUserRepository testUserRepository = new TestUserRepository(1L);
        UserService userService = new UserService(testUserRepository);

        userService.saveUser(nsUser);

        assertThat(testUserRepository.getSaveCalled()).isEqualTo(1);
    }

    @DisplayName("결재 승인 가능 체크")
    @Test
    void testCanApprove() {
        TestNsUser approver = new TestNsUser("approver", "password", "name", "test@naver.com");
        TestNsUser applicant = new TestNsUser("applicant", "password", "name", "test@naver.com");

        TestUserRepository testUserRepository = new TestUserRepository(Map.of(
            "approver", approver,
            "applicant", applicant
        ));

        UserService userService = new UserService(testUserRepository);
        userService.canApprove("approver", "applicant");

        assertAll(
            () -> assertThat(approver.getCanApproveCalled()).isEqualTo(1),
            () -> assertThat(applicant.getCanApproveCalled()).isEqualTo(0)
        );
    }

    @DisplayName("결재 취소 가능 체크")
    @Test
    void testCanCancel() {
        TestNsUser approver = new TestNsUser("approver", "password", "name", "test@naver.com");
        TestNsUser applicant = new TestNsUser("applicant", "password", "name", "test@naver.com");

        TestUserRepository testUserRepository = new TestUserRepository(Map.of(
            "approver", approver,
            "applicant", applicant
        ));

        UserService userService = new UserService(testUserRepository);
        userService.canCancel("approver", "applicant");

        assertAll(
            () -> assertThat(approver.getCanCancelledCalled()).isEqualTo(1),
            () -> assertThat(applicant.getCanCancelledCalled()).isEqualTo(0)
        );
    }
}
