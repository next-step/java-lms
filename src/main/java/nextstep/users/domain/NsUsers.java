package nextstep.users.domain;

import java.util.ArrayList;
import java.util.List;

public class NsUsers {

    private List<NsUser> nsUsers = new ArrayList<>();

    public static NsUsers autoGenerate() {
        NsUsers nsUsers1 = new NsUsers();
        for (long i = 1; i <= 10L; i++) {
            nsUsers1.nsUsers.add(new NsUser(i, "javajigi" + i, "password" + i, "name" + i, "javajigi" + i + "@slipp.net"));
        }

        return nsUsers1;
    }

    public List<NsUser> getNsUsers() {
        return nsUsers;
    }
}
