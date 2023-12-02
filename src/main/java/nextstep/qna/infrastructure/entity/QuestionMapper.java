package nextstep.qna.infrastructure.entity;

import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionInformation;
import nextstep.qna.domain.QuestionTexts;
import nextstep.qna.domain.Times;

public class QuestionMapper {

    private QuestionMapper() {}

    public static Question toDomain(QuestionEntity entity) {
        QuestionTexts questionTexts = new QuestionTexts(entity.getTitle(), entity.getContents());
        Times times = new Times(entity.getCreatedDate(), entity.getUpdatedDate());
        QuestionInformation information = new QuestionInformation(questionTexts, entity.getWriter(), times, entity.isDeleted());
        return new Question(entity.getId(), information);
    }
}
