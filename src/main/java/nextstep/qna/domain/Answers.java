package nextstep.qna.domain;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void postHasAnswersWrittenByMe (NsUser nsUser) throws CannotDeleteException {
        for(Answer answer : answers) {
            answer.validEachAnswerWrittenByMe(nsUser);
        }
    }

    public List<DeleteHistory> delete(NsUser nsUser, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(nsUser));
        }
        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
