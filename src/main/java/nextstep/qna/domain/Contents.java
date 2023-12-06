package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class Contents {

    private Long id;

    private NsUser writer;

    private String contents;

    public Contents(NsUser writer, String contents){
        this(0L, writer, contents);
    }

    public Contents(Long id, NsUser writer, String contents){
        this.id = id;
        this.writer = writer;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isNotOwner(NsUser loginUser) {
        return !writer.equals(loginUser);
    }
}
