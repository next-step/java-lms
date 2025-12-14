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
        this(new ArrayList<>());
    }

    public Answers(Answer... answers) {
        this(List.of(answers));
    }

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void deleteAll(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public List<DeleteHistory> toDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.deleteHistory());
        }
        return deleteHistories;
    }
}
