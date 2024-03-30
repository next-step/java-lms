package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void delete(NsUser user) throws CannotDeleteException {
        // validation
        if (this.answers.stream()
                .anyMatch(Predicate.not(answer -> answer.isOwner(user)))) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        this.answers.forEach(Answer::delete);
    }




    public List<Answer> get() {
        return Collections.unmodifiableList(answers);
    }
}
