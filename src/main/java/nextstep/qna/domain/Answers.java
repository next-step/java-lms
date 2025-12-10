package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(Answer... answers) {
        this(List.of(answers));
    }

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void validateDeletableUser(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }
    }

    public List<DeleteHistory> deleteAll() {
        List<DeleteHistory> list = new ArrayList<>();
        for (Answer answer : answers) {
            list.add(answer.delete());
        }
        return list;
    }
}
