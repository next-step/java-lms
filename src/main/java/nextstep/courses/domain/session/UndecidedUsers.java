package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class UndecidedUsers {
    private List<SelectionUser> undecidedUsers;

    public UndecidedUsers(final List<SelectionUser> selectionUsers) {
        this.undecidedUsers = new ArrayList<>(selectionUsers);
    }

    public void add(final NsUser user) {
        this.undecidedUsers.add(new SelectionUser(SelectStatus.UNDECIDED, user));
    }

    public void add(final SelectionUser user) {
        this.undecidedUsers.add(user);
    }

    public List<SelectionUser> getUsers() {
        return this.undecidedUsers;
    }

    public int size() {
        return this.undecidedUsers.size();
    }

    public static UndecidedUsers of(final List<NsUser> nsUsers) {
        final List<SelectionUser> selectionUsers = new ArrayList<>();

        for (final NsUser nsUser : nsUsers) {
            selectionUsers.add(new SelectionUser(SelectStatus.UNDECIDED, nsUser));
        }

        return new UndecidedUsers(selectionUsers);
    }

    public void addAll(final List<SelectionUser> selectionUsers) {
        if (selectionUsers == null) {
            return;
        }

        this.undecidedUsers.addAll(selectionUsers);
    }
}
