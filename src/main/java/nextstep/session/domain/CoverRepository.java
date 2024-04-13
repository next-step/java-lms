package nextstep.session.domain;

import nextstep.session.dto.CoverVO;

public interface CoverRepository {

    long save(Cover cover);

    Cover findById(long coverId);

    int updateDeleteStatus(long coverId, boolean deleteStatus);
}
