package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
            answer.validEachPostHasAnswerWrittenByMe(nsUser);
        }
    }

    public List<DeleteHistory> delete(List<DeleteHistory> deleteHistories) {
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete());
        }
        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
