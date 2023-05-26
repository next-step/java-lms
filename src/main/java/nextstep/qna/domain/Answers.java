package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new LinkedList<>();
    }

    public void checkCanDelete(NsUser loginUser) {
        answers.forEach(answer -> answer.checkCanDelete(loginUser));
    }

    public List<DeleteHistory> makeDeleteHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.forEach(answer -> deleteHistories.add(answer.makeDeleteHistory()));
        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
