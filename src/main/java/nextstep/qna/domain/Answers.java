package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public void checkDeletePermission(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            checkAnswerOwner(answer, loginUser);
        }
    }

    private void checkAnswerOwner(Answer answer, NsUser loginUser) throws CannotDeleteException {
        if (!answer.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void add(Answer answer){
        this.answers.add(answer);
    }

    public List<DeleteHistory> deleteAnswers() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete());
        }
        return deleteHistories;
    }
}
