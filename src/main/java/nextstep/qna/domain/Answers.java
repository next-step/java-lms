package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Answers {

    private final List<Answer> values;

    public Answers(Answer... answers) {
        this(Arrays.stream(answers)
                   .collect(toUnmodifiableList()));
    }

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> values) {
        this.values = values;
    }

    public void add(Answer answer) {
        this.values.add(answer);
    }

    public Answers delete(NsUser loginUser) {
        return this.values.stream()
                          .map(answer -> answer.delete(loginUser))
                          .collect(collectingAndThen(toUnmodifiableList(), Answers::new));
    }

    public List<DeletedHistory> buildHistories() {
        return this.values.stream()
                          .map(Answer::build)
                          .collect(toUnmodifiableList());
    }
}
