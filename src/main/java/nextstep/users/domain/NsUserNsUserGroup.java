package nextstep.users.domain;

public class NsUserNsUserGroup {
  private Long id;

  private Long nsUserId;

  private Long nsUserGroupId;

  public Long getId() {
    return id;
  }

  public Long getNsUserId() {
    return nsUserId;
  }

  public Long getNsUserGroupId() {
    return nsUserGroupId;
  }

  public NsUserNsUserGroup(Long id, Long nsUserId, Long nsUserGroupId) {
    this.id = id;
    this.nsUserId = nsUserId;
    this.nsUserGroupId = nsUserGroupId;
  }
}
