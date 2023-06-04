package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final Question question;
    private final List<Answer> answers = new ArrayList<>();

    public Answers(Question question) {
        this.question = question;
    }

    public void deleteAll() throws CannotDeleteException {
        validateIfAllRespondersAreSame();
        deleteAllAnswers();
    }

    private void deleteAllAnswers() throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete();
        }
    }

    private void validateIfAllRespondersAreSame() throws CannotDeleteException {
        if (!areAllRespondersSame()) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean areAllRespondersSame() {
        return answers.stream().allMatch(answer -> answer.isOwner(question.getWriterId()));
    }

    public List<DeleteHistory> toHistories() {
        return answers.stream().map(Answer::toHistory).collect(Collectors.toList());
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(question);
        answers.add(answer);
    }

    public boolean isAllDeleted() {
        return answers.stream().allMatch(Answer::isDeleted);
    }
}
