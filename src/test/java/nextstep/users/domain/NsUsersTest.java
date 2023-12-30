package nextstep.users.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.domain.NsUserLimit;
import nextstep.courses.domain.SessionPaymentType;
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
        assertThat(users.isFull(new NsUserLimit(1,SessionPaymentType.PAID))).isTrue();
    }

    @Test
    @DisplayName("유저 수가 한계량을 넘어섰는지 확인")
    void isGreater(){
        List<NsUser> actualData = new ArrayList<>(List.of(NsUserTest.JAVAJIGI));
        NsUsers users = new NsUsers(actualData);
        assertThat(users.isGreater(new NsUserLimit(0, SessionPaymentType.PAID))).isTrue();
    }

    @Test
    @DisplayName("내부에 유저가 없지 않은지 확인")
    void isNotEmpty(){
        List<NsUser> actualData = new ArrayList<>(List.of(NsUserTest.JAVAJIGI));
        NsUsers users = new NsUsers(actualData);
        assertThat(users.isNotEmpty()).isTrue();
    }

    @Test
    @DisplayName("다른 NsUsers와 비교 후 다른 NsUser에만 있는 NsUser들을 반환")
    void diffWith(){
        List<NsUser> actualData = new ArrayList<>(List.of(NsUserTest.JAVAJIGI));
        List<NsUser> otherNsUserData = new ArrayList<>(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI));
        NsUsers users = new NsUsers(actualData);
        NsUsers otherUsers = new NsUsers(otherNsUserData);
        assertThat(users.diffWith(otherUsers)).contains(NsUserTest.SANJIGI);
    }

    @Test
    @DisplayName("NsUsers 내부 전체를 타 NsUsers의 멤버들로 교체")
    void replaceAll(){
        List<NsUser> actualData = new ArrayList<>(List.of(NsUserTest.JAVAJIGI));
        List<NsUser> otherNsUserData = new ArrayList<>(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI));
        NsUsers users = new NsUsers(actualData);
        NsUsers otherUsers = new NsUsers(otherNsUserData);
        users.replaceAll(otherUsers);
        assertThat(actualData).contains(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI);
    }
}
