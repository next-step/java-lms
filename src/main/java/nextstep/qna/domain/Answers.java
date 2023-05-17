package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.User;

import java.util.ArrayList;
import java.util.List;


public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAll(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        List<Answer> deletedAnswers = new ArrayList<>();

        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
            deletedAnswers.add(answer);
        }

        answers.removeAll(deletedAnswers);

        return deleteHistories;
    }

    public int getCount() {
        return answers.size();
    }
}
