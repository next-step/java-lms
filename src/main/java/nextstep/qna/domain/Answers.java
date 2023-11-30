package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public static Answers init() {
        return new Answers(Collections.emptyList());
    }

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers add(Answer answer) {
        List<Answer> answers = new ArrayList<>(this.answers);
        answers.add(answer);
        return new Answers(answers);
    }

    public boolean checkAnswer(NsUser writer) {
        return this.answers.stream()
                .anyMatch(answer -> !answer.isOwner(writer));
    }

    public List<DeleteHistory> deleted() {
        return Collections.unmodifiableList(this.answers.stream()
                .map(Answer::deleted)
                .collect(Collectors.toList()));
    }
}
