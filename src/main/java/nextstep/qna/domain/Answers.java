package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public List<DeleteHistory> deleteAnswers(NsUser loginUser, LocalDateTime deleteTime) {
        return this.answers.stream()
                .map(answer -> answer.deleteAnswer(loginUser, deleteTime))
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
