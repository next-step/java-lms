package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TotalSelectStatusUsers {
    private UndecidedUsers undecidedUsers = new UndecidedUsers(new ArrayList<>());
    private SelectedUsers selectedUsers = new SelectedUsers(new ArrayList<>());
    private UnselectedUsers unselectedUsers = new UnselectedUsers(new ArrayList<>());

    public TotalSelectStatusUsers(final UndecidedUsers users) {
        this(users.getUsers());
    }

    public TotalSelectStatusUsers(List<SelectionUser> selectionUsers) {
        final Map<SelectStatus, List<SelectionUser>> statusListMap = selectionUsers.stream()
                .collect(Collectors.groupingBy(SelectionUser::getStatus));

        undecidedUsers.addAll(statusListMap.get(SelectStatus.UNDECIDED));
        selectedUsers.addAll(statusListMap.get(SelectStatus.SELECTED));
        unselectedUsers.addAll(statusListMap.get(SelectStatus.UNSELECTED));
    }

    public void addUndecideUser(final NsUser user) {
        this.undecidedUsers.add(user);
    }

    public void addSelectedUser(final NsUser user) {
        this.selectedUsers.add(user);
    }

    public void addUnselectedUser(final NsUser user) {
        this.unselectedUsers.add(user);
    }

    public List<SelectionUser> getTotalUsers() {
        List<SelectionUser> totalUsers = new ArrayList<>();

        totalUsers.addAll(this.undecidedUsers.getUsers());
        totalUsers.addAll(this.selectedUsers.getUsers());
        totalUsers.addAll(this.unselectedUsers.getUsers());

        return totalUsers;
    }

    public int size() {
        return this.selectedUsers.size() + this.unselectedUsers.size() + this.undecidedUsers.size();
    }
}
