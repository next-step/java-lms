package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nextstep.users.domain.NsUser;

public class Answers {

	private final List<Answer> answerList;

	public Answers() {
		this.answerList = new ArrayList<>();
	}

	public List<DeleteHistory> deleteAnswer(NsUser loginUser) {
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		for (Answer answer : answerList) {
			deleteHistories.add(answer.deleteAnswer(loginUser));
		}
		return deleteHistories;
	}

	public void add(Answer answer) {
		this.answerList.add(answer);
	}
}
