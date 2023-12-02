package nextstep.qna.infrastructure.entity;

import nextstep.qna.domain.DeletedHistory;

public class DeletedHistoryMapper {

    private DeletedHistoryMapper() {}

    public static DeletedHistoryEntity toEntity(DeletedHistory history) {
        return new DeletedHistoryEntity(history.getContentType(),
                                        history.getContentId(),
                                        history.getDeletedBy(),
                                        history.getCreatedDate());
    }
}
