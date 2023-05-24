package nextstep.qna.domain.validate;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;

public class QuestionRemoveValidator {

    public static void validate(Question question, NsUser requestUser) {
        validateAuthorization(question, requestUser);
        validateAboutAnswer(question);
    }

    private static void validateAuthorization(Question question, NsUser requestUser) throws CannotDeleteException {
        if (!question.isOwner(requestUser)) {
            throw new CannotDeleteException("글 작성자만 삭제 가능해요 :(");
        }
    }

    private static void validateAboutAnswer(Question question) {

        if (!question.hasAnswer()) {
            return;
        }

        if (question.hasAnotherOwner()) {
            throw new CannotDeleteException("다른분이 작성한 답변글이 존재해서 삭제 불가능 해요 :(");
        }
    }

}
