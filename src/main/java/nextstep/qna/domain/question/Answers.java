package nextstep.qna.domain.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nextstep.qna.domain.history.DeleteHistory;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(final Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> delete(final NsUser questionWriter, final LocalDateTime deleteDateTime) {
        return this.answers.stream()
                .map(answer -> answer.delete(questionWriter, deleteDateTime))
                .collect(Collectors.toUnmodifiableList());
    }
}
