package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private static final String CANNOT_DELETE_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    private final List<Answer> answerList;

    public Answers(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (answerList.stream()
                .anyMatch(it -> !it.isOwner(loginUser))
        ) {
            throw new CannotDeleteException(CANNOT_DELETE_MESSAGE);
        }

        return answerList.stream()
                .map(this::deleteAnswer)
                .collect(Collectors.toList());
    }

    private DeleteHistory deleteAnswer(Answer answer) {
        answer.setDeleted(true);
        return DeleteHistory.from(answer);
    }
}