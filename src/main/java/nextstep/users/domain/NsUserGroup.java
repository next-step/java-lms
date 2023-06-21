package nextstep.users.domain;

import java.util.List;

public class NsUserGroup {
  private Long id;
  private String name;

  public NsUserGroup(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public boolean isPermitted(List<NsUserNsUserGroup> nsUserNsUserGroups) {
    return nsUserNsUserGroups.stream()
        .filter(group -> this.id.equals(group.getNsUserGroupId()))
        .findAny()
        .isPresent();
  }
}
