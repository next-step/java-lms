package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers(final List<Answer> answers) {
        this.answers = answers;
    }

    public Answers() {
        answers = new ArrayList<>();
    }

    public List<DeleteHistory> delete(final NsUser loginUser) {
        return answers.stream()
                .map(answer -> {
                    try {
                        return answer.delete(loginUser);
                    } catch (CannotDeleteException e) {
                        throw new IllegalArgumentException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
                    }
                })
                .collect(Collectors.toList());
    }

    public void add(final Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
