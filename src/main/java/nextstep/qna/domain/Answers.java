package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList(answers);
    }

    public boolean isAllSameBy(NsUser writer) {
        if (hasNoAnswers()) {
            return false;
        }
        return answers.stream()
                .allMatch(answer -> answer.isOwner(writer));
    }

    private boolean hasNoAnswers() {
        return answers.isEmpty();
    }

    public void deleteAll(NsUser writer) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(writer);
        }
    }

    public boolean isDeleted() {
        return this.answers.stream()
                .allMatch(Answer::isDeleted);
    }

    public List<Answer> answers() {
        return Collections.unmodifiableList(answers);
    }

    public void toQuestion(Question question) {
        for (Answer answer : answers) {
            answer.toQuestion(question);
        }
    }

    public List<DeleteHistory> deleteHistory() {
        return answers.stream()
                .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}

