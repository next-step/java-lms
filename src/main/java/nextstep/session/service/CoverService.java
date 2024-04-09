package nextstep.session.service;

import nextstep.common.domain.DeleteHistory;
import nextstep.session.domain.Cover;

public interface CoverService {

    Cover findById(Long coverId);

    DeleteHistory delete(Long coverId);

    Long save(Cover cover);
}
