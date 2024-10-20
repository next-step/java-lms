package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(final List<Answer> answers) {
        this.answers = answers;
    }

    public boolean allMatch(final NsUser user) {
        return answers.stream()
            .allMatch(answer -> answer.isOwner(user));
    }

    public void add(final Answer answer) {
        answers.add(answer);
    }
}
