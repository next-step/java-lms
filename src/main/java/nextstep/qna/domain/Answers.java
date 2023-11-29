package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.ANSWER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nextstep.qna.error.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> deleteAnswers(NsUser loginUser) throws CannotDeleteException {
        if (!canDelete(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        return addHistory();
    }

    private boolean canDelete(NsUser loginUser) {
        try {
            for (Answer answer : answers) {
                answer.checkOwner(loginUser);
            }
        } catch (CannotDeleteException exception) {
            return false;
        }

        return true;
    }

    private List<DeleteHistory> addHistory() {
        answers.forEach(Answer::deleted);
        return answers.stream()
                      .map(answer -> new DeleteHistory(ANSWER, answer))
                      .collect(Collectors.toList());
    }
}
