package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isOwner(NsUser writer) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(writer));
    }

    public List<DeleteHistory> removeAll(NsUser writer) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.remove(writer));
        }
        return deleteHistories;
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

}
