package nextstep.courses.domain;

public enum SessionCoverImageFixture {
    TDD_SESSION_COVER_IMAGE(new byte[]{ });

    private final byte[] data;

    SessionCoverImageFixture(byte[] data) {
        this.data = data;
    }

    public SessionCoverImage sessionCoverImage() {
        return new SessionCoverImage(data);
    }
}
