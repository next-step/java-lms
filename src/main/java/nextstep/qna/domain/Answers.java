package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAllBy(NsUser user) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.stream()
            .map(answer -> answer.deleteBy(user))
            .forEach(deleteHistories::add);
        return deleteHistories;
    }
}
