package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList(answers);
    }

    public boolean hasOtherWriter(NsUser writer) {
        if (hasNoAnswers()) {
            return false;
        }
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(writer));
    }

    private boolean hasNoAnswers() {
        return answers.isEmpty();
    }

    public List<DeleteHistory> deleteAll(NsUser writer) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            DeleteHistory deleteHistory = answer.delete(writer);
            deleteHistories.add(deleteHistory);
        }
        return deleteHistories;
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

}

