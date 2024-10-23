package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean isContainsOtherUser(NsUser writer) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(writer));
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void setAllDelete() {
        answers.forEach(answer -> answer.setDeleted(true));
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
