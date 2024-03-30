package nextstep.qna.domain.answer;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers = new ArrayList();

    public Answers() {
    }

    public void add(Answer answer, Question question) {
        answer.toQuestion(question);
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public boolean isEmpty() {
        return answers.isEmpty();
    }

    public boolean isOwner(NsUser nsUser) {
        return this.answers.stream()
                .allMatch(answer -> answer.isOwner(nsUser));
    }

    public void delete(NsUser nsUser) throws CannotDeleteException {
        if (isEmpty()) {
            return;
        }

        if (!isOwner(nsUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        this.answers.forEach(answer -> answer.delete());
    }

    public List<DeleteHistory> getDeleteHistories() {
        return this.answers.stream()
                .map(DeleteHistory::new)
                .collect(Collectors.toList());
    }
}
