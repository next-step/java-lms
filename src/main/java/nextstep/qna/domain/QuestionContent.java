package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class QuestionContent {
	private String title;

	private String contents;


	public QuestionContent(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "QuestionContent [title=" + title + ", contents=" + contents + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof QuestionContent) {
			return title.equals(((QuestionContent)obj).title) && contents.equals(((QuestionContent)obj).contents);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return title.hashCode() + contents.hashCode();
	}
}
