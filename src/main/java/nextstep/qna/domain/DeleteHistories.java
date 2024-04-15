package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    List<DeleteHistory> deleteHistories = new ArrayList<>();

    public List<DeleteHistory> process(long questionId, NsUser writer, List<Answer> answers) {
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, writer, LocalDateTime.now()));
        procesRelated(answers);
        return deleteHistories;
    }

    private void procesRelated(List<Answer> answers) {
        for (Answer answer : answers) {
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
