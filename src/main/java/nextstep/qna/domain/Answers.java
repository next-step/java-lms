package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answers){
        this.answers = answers;
    }

    public void deleteAnswers(NsUser loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public List<DeleteHistory> deleteAllHistories() {
        return answers.stream().map((answer) -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public boolean isAllDeleted() {
        return this.answers.stream().allMatch(Answer::isDeleted);
    }
}
