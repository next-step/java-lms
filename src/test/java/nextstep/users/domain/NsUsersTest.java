package nextstep.users.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NsUsersTest {
    @Test
    @DisplayName("NsUsers 생성")
    void create() {
        assertThat(new NsUsers(new ArrayList<>())).isInstanceOf(NsUsers.class);
    }

    @Test
    @DisplayName("NsUser 추가")
    void add() {
        List<NsUser> actualData = new ArrayList<>();
        NsUsers users = new NsUsers(actualData);
        users.add(NsUserTest.JAVAJIGI);
        assertThat(actualData).contains(NsUserTest.JAVAJIGI);
    }


    @Test
    @DisplayName("유저 수가 한계량에 도달했는지 확인")
    void isFull(){
        List<NsUser> actualData = new ArrayList<>(List.of(NsUserTest.JAVAJIGI));
        NsUsers users = new NsUsers(actualData);
        assertThat(users.isFull(1)).isTrue();
    }

    @Test
    @DisplayName("유저 수가 한계량을 넘어섰는지 확인")
    void isGreater(){
        List<NsUser> actualData = new ArrayList<>(List.of(NsUserTest.JAVAJIGI));
        NsUsers users = new NsUsers(actualData);
        assertThat(users.isGreater(0)).isTrue();
    }

    @Test
    @DisplayName("내부에 유저가 없지 않은지 확인")
    void isNotEmpty(){
        List<NsUser> actualData = new ArrayList<>(List.of(NsUserTest.JAVAJIGI));
        NsUsers users = new NsUsers(actualData);
        assertThat(users.isNotEmpty()).isTrue();
    }
}
