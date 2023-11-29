package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }


    public void add(Answer answer) {
        answers.add(answer);
    }

    public void deleteAll(NsUser user) {
        this.answers.forEach(answer -> answer.delete(user));
    }

    public List<DeleteHistory> makeDeleteHistories() {
        return answers.stream()
                .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
