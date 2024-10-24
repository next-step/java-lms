package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class  Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers() {
        this(new ArrayList<>());
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public int size() {
        return answers.size();
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public boolean isDeletable(NsUser questionUser) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(questionUser));
    }

    public void delete(NsUser user) {
        answers.forEach(answer -> answer.delete(user));
    }
}
