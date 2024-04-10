package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public boolean containsNotOwner(NsUser writer) throws CannotDeleteException {
        return this.answers.stream()
                .anyMatch(answer -> !answer.isOwner(writer));
    }

    public void delete() {
        for (Answer answer : answers) {
            answer.delete();
        }
    }

    public List<DeleteHistory> deletedHistories() {
        return answers.stream()
                .map(Answer::deletedHistories)
                .collect(Collectors.toList());
    }

    public List<Answer> getValue() {
        return Collections.unmodifiableList(this.answers);
    }
}
