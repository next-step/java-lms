package nextstep.qna.domain.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.history.DeleteHistory;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(final Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete(
            final NsUser questionWriter,
            final LocalDateTime deleteDateTime
    ) throws CannotDeleteException {

        final List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            final DeleteHistory answerDeleteHistory = answer.delete(questionWriter, deleteDateTime);
            deleteHistories.add(answerDeleteHistory);
        }

        return deleteHistories;
    }
}
