package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> deleteAll(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            DeleteHistory answerDeleteHistory = deleteAnswer(answer, loginUser);
            deleteHistories.add(answerDeleteHistory);
        }
        return deleteHistories;
    }

    private DeleteHistory deleteAnswer(Answer answer, NsUser loginUser) throws CannotDeleteException {
        try {
            return answer.delete(loginUser);
        } catch (CannotDeleteException e) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<Answer> answers() {
        return this.answers;
    }
}
