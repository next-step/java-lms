package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers(Answer... answers) {
        this.answers = List.of(answers);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public DeleteHistories deleteAll(NsUser requestUser) throws CannotDeleteException {
        DeleteHistories deleteHistories = new DeleteHistories();
        List<Answer> aliveAnswers = getAliveAnswers();
        for (Answer answer : aliveAnswers) {
            deleteHistories.add(tryDelete(requestUser, answer, aliveAnswers));
        }
        return deleteHistories;
    }

    private DeleteHistory tryDelete(NsUser requestUser, Answer answer, List<Answer> restoreTarget) throws CannotDeleteException {
        try {
            return answer.delete(requestUser);
        } catch (CannotDeleteException e) {
            restoreAnswers(restoreTarget);
            throw new CannotDeleteException(e.getMessage());
        }
    }

    private List<Answer> getAliveAnswers() {
        return answers.stream()
                .filter(answer -> !answer.isDeleted())
                .collect(Collectors.toList());
    }

    private void restoreAnswers(List<Answer> aliveAnswers) {
        for (Answer aliveAnswer : aliveAnswers) {
            aliveAnswer.setDeleted(false);
        }
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
