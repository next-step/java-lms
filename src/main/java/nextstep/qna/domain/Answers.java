package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import nextstep.users.domain.NsUser;

public class Answers {

    private List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isContainOtherOwner(NsUser loginUser) {
       return answers.stream()
           .anyMatch(answer -> !answer.isOwner(loginUser));
    }

    public void delete() {
        answers.forEach(answer -> answer.delete());
    }

    public List<DeleteHistory> getDeleteHistory() {
        return answers.stream()
            .map(DeleteHistory::ofAnswer)
            .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
