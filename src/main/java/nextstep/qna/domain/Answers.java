package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers(Answer... answers) {
        this(new ArrayList<>(Arrays.asList(answers)));
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> answers() {
        return Collections.unmodifiableList(new ArrayList<>(answers));
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void delete(NsUser user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(user);
        }
    }

    public List<DeleteHistory> toDeleteHistories() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.addAll(answer.toDeleteHistories());
        }
        return deleteHistories;
    }

}
