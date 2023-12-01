package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public DeleteHistories delete(NsUser loginUser, LocalDateTime now) {
        return answers.stream()
                .map(answer -> answer.delete(loginUser, now))
                .collect(collectingAndThen(toList(), DeleteHistories::new));

    }
}
