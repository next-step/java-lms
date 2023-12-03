package nextstep.qna.domain;

import nextstep.qna.Exception.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public Answers(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> deleteAnswers(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            answer.checkOwner(loginUser);
            answer.deleted();
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer, now()));
        }
        return deleteHistories;
    }
}
