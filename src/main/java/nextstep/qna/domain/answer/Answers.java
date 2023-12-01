package nextstep.qna.domain.answer;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.Collections;
import java.util.List;

public class Answers {

    private List<Answer> answerList;

    public Answers(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void isDeleteBy(NsUser writer) throws CannotDeleteException {
        canDeleteBy(writer);
        answerList.stream()
                .forEach(answer -> answer.isDeleted());
    }

    public void canDeleteBy(NsUser writer) throws CannotDeleteException {
        for (Answer answer : answerList) {
            try {
                answer.canDeleteBy(writer);
            } catch (CannotDeleteException e) {
                throw e;
            }
        }
    }

    public final List<Answer> getAnswerList() {
        return Collections.unmodifiableList(answerList);
    }
}
