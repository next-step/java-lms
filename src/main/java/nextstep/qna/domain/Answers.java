package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Answers implements Iterable<Answer> {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public boolean validateWriter(NsUser loginUser) {
        return this.answers.stream().anyMatch(answer -> !answer.isOwner(loginUser));
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    @Override
    public Iterator<Answer> iterator() {
        return answers.iterator();
    }
}
