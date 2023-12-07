package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class UnselectedUsers {
    private List<SelectionUser> unselectedUsers;

    public UnselectedUsers(final List<SelectionUser> tempUnselectedUsers) {
        this.unselectedUsers = tempUnselectedUsers;
    }

    public static UnselectedUsers of(final List<NsUser> nsUsers) {
        final List<SelectionUser> selectionUsers = new ArrayList<>();

        for (final NsUser nsUser : nsUsers) {
            selectionUsers.add(new SelectionUser(SelectStatus.UNSELECTED, nsUser));
        }

        return new UnselectedUsers(selectionUsers);
    }

    public void add(final NsUser user) {
        this.unselectedUsers.add(new SelectionUser(SelectStatus.UNSELECTED, user));
    }

    public void add(final SelectionUser user) {
        this.unselectedUsers.add(user);
    }

    public List<SelectionUser> getUsers() {
        return this.unselectedUsers;
    }

    public int size() {
        return this.unselectedUsers.size();
    }

    public void addAll(final List<SelectionUser> selectionUsers) {
        if (selectionUsers == null) {
            return;
        }

        this.unselectedUsers.addAll(selectionUsers);
    }
}
