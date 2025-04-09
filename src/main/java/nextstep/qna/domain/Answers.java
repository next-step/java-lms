package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public List<Answer> getAnswers() {
        return answers;
    }

    public Answers add(Answer answer) {
        answers.add(answer);
        return this;
    }

    public boolean isEmpty() {
        return answers.isEmpty();
    }

    public boolean hasOthersAnswers(NsUser writer) {
        return answers.stream().anyMatch(answer -> !answer.isOwner(writer));
    }

    public List<DeleteHistory> delete() {
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }
}
