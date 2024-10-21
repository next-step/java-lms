package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<DeleteHistory> deleteAll(final NsUser user) {
        return answers.stream()
            .map(answer -> answer.delete(user))
            .collect(Collectors.toUnmodifiableList());
    }
}
