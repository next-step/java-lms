package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nextstep.users.domain.NsUser;

public class Answers {

	private final List<Answer> answers = new ArrayList<>();

	public Answers() {
	}

	public void add(Answer answer) {
		this.answers.add(answer);
	}

	public boolean isOwner(NsUser loginUser) {
		return this.answers.stream().allMatch(answer -> answer.isOwner(loginUser));
	}

	public void delete() {
		this.answers.forEach(answer -> answer.setDeleted(true));
	}

	public List<DeleteHistory> deleteHistories(LocalDateTime deleteTime) {
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		for (Answer answer : answers) {
			deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), deleteTime));
		}
		return deleteHistories;
	}
}
