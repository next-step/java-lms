package nextstep.courses.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NsUsersDTOTest {
    @Test
    @DisplayName("NsUsersDTO 생성")
    void create() {
        List<NsUser> userList = new ArrayList<>(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI));
        assertThat(new NsUsersDTO(userList)).isInstanceOf(NsUsersDTO.class);
    }

    @Test
    @DisplayName("NsUsersDTO 생성")
    void getUserList() {
        List<NsUser> userList = new ArrayList<>(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI));
        assertIterableEquals(new NsUsersDTO(userList).getUserList(), userList);
    }
}
