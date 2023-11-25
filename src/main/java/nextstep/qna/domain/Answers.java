package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        deleteValidation(loginUser);
        return deleteHistories(loginUser);
    }

    private void deleteValidation(NsUser loginUser) throws CannotDeleteException {
        boolean cannotDeleteAnswers = answers.stream().anyMatch(answer -> !answer.isOwner(loginUser));
        if (cannotDeleteAnswers) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private List<DeleteHistory> deleteHistories(NsUser loginUser) {
        return answers.stream()
                .map(answer -> {
                    try {
                        return answer.delete(loginUser);
                    } catch (CannotDeleteException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
