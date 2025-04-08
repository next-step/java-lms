package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answerList) {
        this.answers = new ArrayList<>(answerList);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    private void delete(NsUser writer) throws CannotDeleteException {
        try {
            for (Answer answer : answers) {
                answer.delete(writer);
            }
        } catch (CannotDeleteException e) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void delete(NsUser writer, DeleteHistories deleteHistories) throws CannotDeleteException {
        this.delete(writer);

        if (deleteHistories != null) {
            deleteHistories.add(this);
        }
    }

    public boolean isDeleted() {
        return answers.stream().allMatch(Answer::isDeleted);
    }
}
