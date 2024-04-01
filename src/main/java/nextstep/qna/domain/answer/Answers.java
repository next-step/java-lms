package nextstep.qna.domain.answer;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.deleteHistory.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
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

        for (Answer answer : answers) {
            answer.delete(nsUser);
        }
    }

    public List<DeleteHistory> getDeleteHistories(LocalDateTime regDatetime) {
        return this.answers.stream()
                .map(answer -> answer.convertDeleteHistory(regDatetime))
                .collect(Collectors.toList());
    }

}
