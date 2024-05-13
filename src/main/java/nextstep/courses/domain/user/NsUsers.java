package nextstep.courses.domain.user;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class NsUsers {
    private final List<NsUser> users = new ArrayList<>();

    public NsUsers() {
    }

    public int numberOfUsers(){
        return users.size();
    }

    public void add(NsUser user){
        users.add(user);
    }
}
