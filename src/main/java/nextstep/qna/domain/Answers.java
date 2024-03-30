package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void delete(NsUser user) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            answer.delete(user);
        }
    }

    public void addTo(DeleteHistories histories) {
        answers.forEach(answer -> answer.addTo(histories));
    }


    public List<Answer> get() {
        return Collections.unmodifiableList(answers);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
