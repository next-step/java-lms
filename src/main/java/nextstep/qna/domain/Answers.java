package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers;

    public Answers() {
        this.answers  = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addOne(Answer answer) {
        this.answers.add(answer);
    }

    public void addList(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public boolean hasAnswers() {
        return !this.answers.isEmpty();
    }
    public List<DeleteHistory> deleteAnswers(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.deleteAnswer(loginUser));
        }
        return deleteHistories;
    }

}
