package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = List.copyOf(answers);
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public void delete(NsUser longinUser) {
        this.answers.forEach(answer -> answer.delete(longinUser));
    }
}
