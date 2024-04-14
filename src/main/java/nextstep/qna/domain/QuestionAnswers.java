package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswers {

    private List<Answer> answers;

    public QuestionAnswers() {
        answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    // 제거 대상
    public List<Answer> getAnswers() {
        return answers;
    }

    public void checkAnswerOwner(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkOwner(loginUser);
        }
    }


}
