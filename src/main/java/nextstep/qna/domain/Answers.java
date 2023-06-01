package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Answers {
    private List<Answer> answers = new ArrayList<>();
    public void add(Answer answer) {
        answers.add(answer);
    }

    public void delete(NsUser loginUser, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        Optional<Answer> any = answers.stream()
                .filter(answer -> !answer.isOwner(loginUser))
                .findAny();
        if (any.isPresent()) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        answers.forEach(answer -> answer.delete(deleteHistories));
    }
}
