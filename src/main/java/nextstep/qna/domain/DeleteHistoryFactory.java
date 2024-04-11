package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class DeleteHistoryFactory {

    public static DeleteHistory deleteHistoryForAnswer(NsUser user, Answer answer) {
        return new DeleteHistory(ContentType.ANSWER, answer.getId(), user, LocalDateTime.now());
    }

    public static DeleteHistory deleteHistoryForQuestion(NsUser user, Question question) {
        return new DeleteHistory(ContentType.QUESTION, question.getId(), user, LocalDateTime.now());
    }
}
