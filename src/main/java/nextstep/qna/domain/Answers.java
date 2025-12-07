package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Arrays;
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

    public List<DeleteHistory> deleteAll(NsUser user) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.addAll(answer.delete(user));
        }
        return deleteHistories;
    }
}
