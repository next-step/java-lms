package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public boolean isAllOwnerAnswer(NsUser nsUser) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(nsUser));
    }

    public List<DeleteHistory> deleteAllAnswer(NsUser nsUser) {
        validateExistOtherAnswer(nsUser);
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            DeleteHistory deleteHistory = answer.deleteAnswer(nsUser);
            deleteHistories.add(deleteHistory);
        }
        return deleteHistories;
    }

    private void validateExistOtherAnswer(NsUser nsUser) {
        if (!isAllOwnerAnswer(nsUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public int countAnswer() {
        return answers.size();
    }
}
