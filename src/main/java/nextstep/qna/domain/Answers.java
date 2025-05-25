package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.CannotAcquireLockException;

import nextstep.users.domain.NsUser;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void validateOwndBy(NsUser loginUser) {
        for (Answer answer: answers) {
            if (answer.isOwner(loginUser)) {
                throw new CannotAcquireLockException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }
    }

    public List<DeleteHistory> deleteAll() {
        return answers.stream()
        .map(answer -> {
            answer.delete();
            return new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());
        })
        .collect(Collectors.toList());
    }

    public List<Answer> getAnswers() {
        return answers;
    }
    
}
