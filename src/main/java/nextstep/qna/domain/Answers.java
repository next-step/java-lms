package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final Question question;
    private final List<Answer> answerList;

    public Answers(Question question) {
        this.question = question;
        this.answerList = new ArrayList<>();
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answerList) {
            deleteHistories.add(answer.delete(loginUser));
        }

        return deleteHistories;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(question);
        answerList.add(answer);
    }

}
