package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nextstep.users.domain.NsUser;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public Answers(Answer... answers) {
        Arrays.stream(answers)
                .forEach(this::add);
    }

    public int size() {
        return answers.size();
    }

    public List<DeleteHistory> deleteAnswers(NsUser loginUser) {
        return answers.stream()
                .map(answer -> answer.deleteAnswer(loginUser))
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}