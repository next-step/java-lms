package nextstep.session.domain;

import nextstep.session.dto.CoverVO;

public interface CoverRepository {

    long save(CoverVO coverVO);

    CoverVO findById(long coverId);

    int updateDeleteStatus(long coverId, boolean deleteStatus);
}
