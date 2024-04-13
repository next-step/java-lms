package nextstep.session.domain;

import nextstep.common.domain.DeleteHistory;
import nextstep.users.domain.NsUser;

public interface CoverService {

    Cover findById(Long coverId);

    DeleteHistory delete(Cover cover, NsUser requestUser);

    Long save(Cover cover);
}
