package nextstep.session.domain;

import nextstep.session.dto.CoverDto;

public interface CoverRepository {

    long save(CoverDto coverDto);

    CoverDto findById(long coverId);

    int updateDeleteStatus(long coverId, boolean deleteStatus);
}
