package nextstep.courses.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EnrollmentDTOTest {
    @Test
    @DisplayName("NsUserLimitDTO 생성")
    void create() {
        List<NsUser> userList = new ArrayList<>();
        NsUsersDTO nsUsersDTO = new NsUsersDTO(userList);
        NsUserLimitDTO userLimitDTO = new NsUserLimitDTO(10);
        assertThat(new EnrollmentDTO(nsUsersDTO, userLimitDTO)).isInstanceOf(EnrollmentDTO.class);
    }

    @Test
    @DisplayName("NsUserLimitDTO 반환")
    void getLimits() {
        List<NsUser> userList = new ArrayList<>(List.of(NsUserTest.SANJIGI, NsUserTest.JAVAJIGI));
        NsUsersDTO nsUsersDTO = new NsUsersDTO(userList);
        NsUserLimitDTO userLimitDTO = new NsUserLimitDTO(10);
        assertThat(new EnrollmentDTO(nsUsersDTO, userLimitDTO).getLimits()).isInstanceOf(NsUserLimitDTO.class);
    }
}
