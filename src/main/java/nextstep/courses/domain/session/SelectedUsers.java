package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SelectedUsers {
    private List<SelectionUser> selectedUsers;

    public SelectedUsers(final List<SelectionUser> tempSelectedUsers) {
        this.selectedUsers = tempSelectedUsers;
    }

    public static SelectedUsers of(final List<NsUser> nsUsers) {
        final List<SelectionUser> selectionUsers = new ArrayList<>();

        for (final NsUser nsUser : nsUsers) {
            selectionUsers.add(new SelectionUser(SelectStatus.SELECTED, nsUser));
        }

        return new SelectedUsers(selectionUsers);
    }

    public void add(final NsUser user) {
        this.selectedUsers.add(new SelectionUser(SelectStatus.SELECTED, user));
    }

    public void add(final SelectionUser user) {
        this.selectedUsers.add(user);
    }

    public List<SelectionUser> getUsers() {
        return this.selectedUsers;
    }

    public int size() {
        return this.selectedUsers.size();
    }

    public void addAll(final List<SelectionUser> selectionUsers) {
        if (selectionUsers == null) {
            return;
        }

        this.selectedUsers.addAll(selectionUsers);
    }
}
