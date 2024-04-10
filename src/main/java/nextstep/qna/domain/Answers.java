package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers) ;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) {
        return new ArrayList<>();
    }
}
