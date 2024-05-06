package nextstep.qna.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers(Answer ... answers){
        this(Arrays.stream(answers).collect(Collectors.toList()));
    }

    public void delete(NsUser logUser) throws CannotDeleteException {
        validDeletable(logUser);
    }

    public boolean isOwners(NsUser logUser) {
        return answers.stream()
            .allMatch(answer -> answer.isOwner(logUser));
    }

    private void validDeletable(NsUser logUser) throws CannotDeleteException {
        if(!isOwners(logUser)){
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
