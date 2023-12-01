package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(Answer... answers) {
        this.answers = Arrays.stream(answers)
                             .collect(toList());
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) {
        return answers.stream()
                      .map(answer -> answer.delete(loginUser))
                      .collect(toUnmodifiableList());
    }
}
