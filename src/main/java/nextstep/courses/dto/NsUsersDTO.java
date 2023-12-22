package nextstep.courses.dto;

import java.util.List;
import nextstep.users.domain.NsUser;

public class NsUsersDTO {
    private final List<NsUser> userList;

    public NsUsersDTO(List<NsUser> userList) {
        this.userList = userList;
    }

    public List<NsUser> getUserList() {
        return userList;
    }
}
