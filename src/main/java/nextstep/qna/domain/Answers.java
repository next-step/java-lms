package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public static Answers initialize() {
        return new Answers(Collections.emptyList());
    }

    public Answers addAnswer(Answer newAnswer) {
        List<Answer> answers = new ArrayList<>(this.answers);
        answers.add(newAnswer);
        return new Answers(answers);
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean validateDeleteOwner(NsUser questionWriter) {
        return answers.stream()
                .filter(answer -> !answer.isOwner(questionWriter))
                .findAny()
                .isPresent();
    }

    public List<DeleteHistory> deleteAll() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            answer.delete();
            deleteHistories.add(DeleteHistory.Answer(answer.getId(), answer.getWriter()));
        }
        return deleteHistories;
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
