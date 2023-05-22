package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    private Answers(List<Answer> answer) {
        this.answers = answer;
    }

    public static Answers of(List<Answer> answer) {
        return new Answers(answer);
    }

    public boolean hasAnotherOwner(NsUser requestUser) {
        return this.answers
                .stream()
                .anyMatch(answer -> answer.isOwner(requestUser));
    }

    public boolean hasAnswers() {
        return !answers.isEmpty();
    }

    public List<DeleteHistory> removeAll() {
        return answers.stream()
                .map(Answer::remove)
                .collect(Collectors.toList());
    }

}
