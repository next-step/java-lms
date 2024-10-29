package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Subscribers {

    private List<Subscriber> subscribeUsers = new ArrayList<>();

    public Subscribers() {
    }

    public Subscribers(List<Subscriber> subscribeUsers) {
        this.subscribeUsers = subscribeUsers;
    }

    public int subscribeUsersSize() {
        return this.subscribeUsers.size();
    }

    public Subscriber addUser(Session session, NsUser nsUser) {
        Subscriber subscriber = new Subscriber(session, nsUser);
        this.subscribeUsers.add(subscriber);
        return subscriber;
    }

    public List<Subscriber> getSubscribeUsers() {
        return subscribeUsers;
    }
}
