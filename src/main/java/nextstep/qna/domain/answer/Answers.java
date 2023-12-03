package nextstep.qna.domain.answer;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {

    private List<Answer> answerList;

    public Answers() {
        answerList = new ArrayList<>();
    }

    public Answers(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void add(Answer answer) {
        answerList.add(answer);
    }

    public void deletedBy(NsUser writer) throws CannotDeleteException {
        for (Answer answer : answerList) {
            answer.deletedBy(writer);
        }
    }

    public final List<Answer> getAnswerList() {
        return Collections.unmodifiableList(answerList);
    }
}
