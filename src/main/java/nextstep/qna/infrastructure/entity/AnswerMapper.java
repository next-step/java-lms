package nextstep.qna.infrastructure.entity;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerInformation;
import nextstep.qna.domain.Times;

public class AnswerMapper {

    private AnswerMapper() {}

    public static Answer toDomain(AnswerEntity entity) {
        Times times = new Times(entity.getCreatedDate(), entity.getUpdatedDate());
        AnswerInformation information = new AnswerInformation(entity.getContents(),
                                                              entity.getWriter(),
                                                              entity.isDeleted(),
                                                              times);
        return new Answer(entity.getId(), information);
    }
}
