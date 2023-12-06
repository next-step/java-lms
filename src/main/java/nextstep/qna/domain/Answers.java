package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {

    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void deleteAll() {
        answers.stream().forEach(answer -> answer.delete());
    }

    public boolean isNotOwner(NsUser loginUser) {
        return answers.stream().anyMatch(answer -> !answer.isOwner(loginUser));
    }

    public List<DeleteHistory> createDeleteHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.stream()
                .forEach(answer -> deleteHistories.add(DeleteHistory.ofAnswer(
                        answer.getId(), answer.getWriter(), LocalDateTime.now()
                )));
        return deleteHistories;
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

    @Override
    public String toString() {
        return "Answers{" +
                "answers=" + answers +
                '}';
    }
}
