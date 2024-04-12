package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
	private List<Answer> answers = new ArrayList<>();

	public List<DeleteHistory> deleteAll(NsUser loginUser, LocalDateTime now) throws CannotDeleteException {
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		for (Answer answer : answers) {
			deleteHistories.add(answer.delete(loginUser, now));
		}
		return deleteHistories;
	}

	public void add(Answer answer) {
		answers.add(answer);
	}

	public boolean isOwner(NsUser nsUser) {
		return answers.stream().anyMatch(answer -> !answer.isOwner(nsUser));
	}

}