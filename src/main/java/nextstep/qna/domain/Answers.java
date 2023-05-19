package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Answers implements Iterable<Answer> {
    private final Question question;
    private final List<Answer> answers;

    public Answers(Question question) {
        this.question = question;
        answers = new ArrayList<>();
    }

    public Answers(Question question, List<Answer> answers) {
        this(question);
        this.answers.addAll(answers);
    }

    @Override
    public Iterator<Answer> iterator() {
        return answers.iterator();
    }

    public void add(Answer answer) {
        answer.toQuestion(question);
        this.answers.add(answer);
    }

    public List<DeleteHistory> deleteAll() {
        for (Answer answer : answers) {
            validateDeletion(answer);
        }

        return this.answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    private void validateDeletion(Answer answer) {
        if (!answer.isOwner(question.getWriter())) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
