package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Answers {
    private final Question question;
    private final List<Answer> answers;

    public Answers(Question question) {
        this.question = question;
        this.answers = new ArrayList<>();
    }

    public List<DeleteHistory> deleteAll(NsUser loginUser) {
        return answers.stream()
                .map(answer -> answer.delete(loginUser))
                .collect(Collectors.toList());
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(question);
        answers.add(answer);
    }

    public Set<NsUser> getWriters() {
        return answers.stream()
                .map(Answer::getWriter)
                .collect(Collectors.toSet());
    }
}
