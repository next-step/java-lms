package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SelectionUsers {
    List<SelectionUser> selectionUsers;

    public SelectionUsers(List<SelectionUser> selectionUsers) {
        this.selectionUsers = selectionUsers;
    }

    public List<SelectionUser> getSelectionUsers() {
        return this.selectionUsers;
    }


    public static SelectionUsers convert(final List<NsUser> nsUsers) {
        final List<SelectionUser> selectionUsers = new ArrayList<>();

        for (final NsUser nsUser : nsUsers) {
            selectionUsers.add(new SelectionUser(SelectStatus.UNDECIDED, nsUser));
        }

        return new SelectionUsers(selectionUsers);
    }

    public int size() {
        return this.selectionUsers.size();
    }

    public void add(final NsUser user) {
        this.selectionUsers.add(new SelectionUser(SelectStatus.UNDECIDED, user));
    }
}
