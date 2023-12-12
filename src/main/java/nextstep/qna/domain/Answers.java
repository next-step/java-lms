package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public static Answers initialize() {
        return new Answers(Collections.emptyList());
    }

    public void addAnswer(Answer newAnswer) {
        answers.add(newAnswer);
        new Answers(answers);
    }

    private Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public boolean validateDeleteOwner(NsUser questionWriter) {
        return answers.stream()
                .filter(answer -> !answer.isOwner(questionWriter))
                .findAny()
                .isPresent();
    }

    public List<DeleteHistory> deleteAll(NsUser writer) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(writer));
        }
        return deleteHistories;
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public int size() {
        return answers.size();
    }

    @Override
    public String toString() {
        return "Answers{" +
                "answers=" + answers +
                '}';
    }
}
