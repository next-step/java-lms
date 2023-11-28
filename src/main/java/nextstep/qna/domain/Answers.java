package nextstep.qna.domain;

import static java.time.LocalDateTime.now;
import static nextstep.qna.domain.ContentType.ANSWER;

import java.util.ArrayList;
import java.util.List;

import nextstep.qna.error.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers of(List<Answer> answers) {
        return new Answers(answers);
    }

    public List<DeleteHistory> deleteAnswers(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> histories = new ArrayList<>();
        for (Answer answer : answers) {
            answer.checkOwner(loginUser);
            answer.deleted();

            histories.add(new DeleteHistory(ANSWER, answer, now()));
        }

        return histories;
    }
}
