package nextstep.qna.domain.validate;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.Answers;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;

public class QuestionRemoveValidator {

    public static void validate(Question question, NsUser requestUser) {
        validateAuthorization(question.getWriter(), requestUser);
        validateHasAnswer(question.getAnswers(), requestUser);
    }

    private static void validateAuthorization(NsUser writer, NsUser requestUser) throws CannotDeleteException {
        if (!writer.matchUser(requestUser)) {
            throw new CannotDeleteException("글 작성자만 삭제 가능해요 :(");
        }
    }

    private static void validateHasAnswer(Answers answers, NsUser requestUser) {

        if (!answers.hasAnswers()) {
            return;
        }

        if (answers.hasAnotherOwner(requestUser)) {
            throw new CannotDeleteException("답변글이 존재해서 삭제 불가능 해요 :(");
        }
    }

}
