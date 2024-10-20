package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers createAnswers(List<Answer> answers) {
        return new Answers(answers);
    }

    public int size() {
        return answers.size();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void validateAnswerOwners(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkIfAnotherOwnerExists(loginUser);
        }
    }

    public List<DeleteHistory> generateAnswerDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            answer.setDeleted();
            deleteHistories.add(answer.createDeleteHistory());
        }
        return deleteHistories;
    }
}
