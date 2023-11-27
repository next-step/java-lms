package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }


    public void add(Answer answer) {
        answers.add(answer);
    }

    public void validateAllOwner(NsUser user) {
        this.answers.stream()
                .filter(answer -> !answer.isOwner(user))
                .findAny()
                .ifPresent(a -> {
                    throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
                });
    }

    public void deleteAll() {
        this.answers.forEach(Answer::delete);
    }

    public List<DeleteHistory> makeDeleteHistories() {
        return answers.stream()
                .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
