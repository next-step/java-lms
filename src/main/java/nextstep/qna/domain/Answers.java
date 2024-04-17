package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Answers implements Iterable<Answer> {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void validateDelete(NsUser loginUser) throws CannotDeleteException {
         if (this.answers.stream().anyMatch(answer -> !answer.isOwner(loginUser))) {
             throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
         }
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public List<DeleteHistory> delete(LocalDateTime deleteTime) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        this.answers.forEach(answer -> {
            answer.delete();
            deleteHistories.add(answer.createDeleteHistory(deleteTime));
        });

        return deleteHistories;

    }

    @Override
    public Iterator<Answer> iterator() {
        return answers.iterator();
    }
}
