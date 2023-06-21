package nextstep.users.domain;

import java.util.List;

public interface NsUserNsUserGroupRepository {
  List<NsUserNsUserGroup> findByNsUserId(Long NsUserId);
}
