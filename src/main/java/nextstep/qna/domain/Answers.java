package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public Answers(Answer... answers) {
        this(Arrays.stream(answers).collect(Collectors.toList()));
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser logUser) throws CannotDeleteException {
        validDeletable(logUser);

        return Optional.of(answers.stream()
                .map(answer -> answer.delete(logUser))
                .collect(Collectors.toList()))
            .orElse(new ArrayList<>());
    }

    public boolean isOwners(NsUser logUser) {
        return answers.stream()
            .allMatch(answer -> answer.isOwner(logUser));
    }

    private void validDeletable(NsUser logUser) throws CannotDeleteException {
        if (!isOwners(logUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
