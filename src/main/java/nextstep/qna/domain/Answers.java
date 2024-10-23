package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
