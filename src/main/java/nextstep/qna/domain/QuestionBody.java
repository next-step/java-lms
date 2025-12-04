package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class QuestionBody {
    private final String title;
    private final ContentDetails contentDetails;


    public QuestionBody(NsUser writer, String title, String contents) {
        this(title, new ContentDetails(writer, contents));
    }

    public QuestionBody(String title, ContentDetails contentDetails) {
        this.title = title;
        this.contentDetails = contentDetails;
    }

    public boolean isWrittenBy(NsUser writer) {
        return contentDetails.isWrittenBy(writer);
    }

    public NsUser getWriter() {
        return contentDetails.getWriter();
    }

    @Override
    public String toString() {
        return  "title='" + title + '\'' + contentDetails.toString() + '\'';
    }
}
