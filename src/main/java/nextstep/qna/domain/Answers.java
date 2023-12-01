package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public Answers() {}

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public void deleteAll(NsUser loginUser, LocalDateTime now) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser, now);
        }
    }

    public boolean isDeleted(int idx) {
        return answers.get(idx).isDeleted();
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> createDeleteHistories() {
        return this.answers.stream()
            .map(Answer::createDeleteHistory)
            .collect(Collectors.toList());
    }
}
