package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Answers {
    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        try {
            for (Answer answer : answers) {
                answer.delete(loginUser);
            }
        } catch (CannotDeleteException e) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

    }

    public List<DeleteHistory> generateDeleteHistory() {
        return answers.stream().map(Answer::generateDeleteHistory).collect(Collectors.toList());
    }

    public Stream<Answer> stream() {
        return answers.stream();
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
