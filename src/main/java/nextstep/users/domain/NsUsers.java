package nextstep.users.domain;

import java.util.List;

public class NsUsers {
    private final List<NsUser> userList;

    public NsUsers(List<NsUser> userList) {
        this.userList = userList;
    }
    public void add(NsUser user){
        userList.add(user);
    }

    public boolean isFull(int limit){
        return userList.size() >= limit;
    }

    public boolean isGreater(int limit){
        return userList.size() > limit;
    }

    public boolean isNotEmpty(){
        return !userList.isEmpty();
    }
}
