package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
	private final List<Answer> answers;

	public Answers() {
		this.answers = new ArrayList<>();
	}

	public List<DeleteHistory> deleteAnswers(NsUser loginUser, LocalDateTime now) throws CannotDeleteException {
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		for (Answer answer : answers) {
			answer.deleteAnswer(loginUser);
			deleteHistories.add(answer.createDeleteHistory(now));
		}
		return deleteHistories;
	}

	public void add(Answer answer) {
		this.answers.add(answer);
	}
}
