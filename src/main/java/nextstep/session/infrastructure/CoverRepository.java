package nextstep.session.infrastructure;

import nextstep.common.domain.DeleteHistory;
import nextstep.session.dto.CoverDto;

public interface CoverRepository {

    long save(CoverDto coverDto);

    CoverDto findById(Long coverId);

    int updateDeleteStatus(Long coverId, boolean deleteStatus);
}
