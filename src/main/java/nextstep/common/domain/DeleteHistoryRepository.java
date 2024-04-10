package nextstep.common.domain;

import nextstep.common.dto.DeleteHistoryDto;

import java.util.List;

public interface DeleteHistoryRepository {

    void saveAll(List<DeleteHistoryDto> deleteHistories);
}
