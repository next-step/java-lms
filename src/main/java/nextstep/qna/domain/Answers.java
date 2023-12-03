package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {
    private List<Answer> answers;

    private Answers() {}

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void deleteBy(NsUser user) {
        for(Answer answer : answers) {
            answer.deleteBy(user);
        }
    }

    public List<DeleteHistory> addDeleteHistories(NsUser user) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for(Answer answer : answers) {
            deleteHistories.add(answer.addDeleteHistory(user));
        }

        return deleteHistories;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
