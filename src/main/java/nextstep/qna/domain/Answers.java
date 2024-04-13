package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {

    private List<Answer> answers;

    public Answers() {
        answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void deleted(NsUser writer) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.deleted(writer);
        }
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public List<DeleteHistory> toDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.toDeleteHistory(LocalDateTime.now()));
        }
        return deleteHistories;
    }

}
