package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<DeleteHistory> delete() {
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }
}
