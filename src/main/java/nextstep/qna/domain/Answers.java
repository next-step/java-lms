package nextstep.qna.domain;

 import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAll(NsUser loginUser) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : this.answers) {
            answer.delete(loginUser, LocalDateTime.now());
            deleteHistories.add(answer.delete(loginUser, LocalDateTime.now()));
        }

        return deleteHistories;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }
}
