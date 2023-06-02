package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers = new ArrayList<>();
    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        deleteValidation(loginUser);
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    private void deleteValidation(NsUser loginUser) throws CannotDeleteException {
        Optional<Answer> any = answers.stream()
                .filter(answer -> !answer.isOwner(loginUser))
                .findAny();
        if (any.isPresent()) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
