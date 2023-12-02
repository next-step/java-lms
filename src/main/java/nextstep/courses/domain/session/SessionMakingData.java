package nextstep.courses.domain.session;

import org.springframework.util.Assert;

public class SessionMakingData {
    private final SessionInfo sessionInfo;
    private final SessionDate sessionDate;
    private CoverImage coverImage;

    public SessionMakingData(final SessionInfo sessionInfo, final SessionDate sessionDate, CoverImage coverImage) {
        validateSession(sessionInfo, sessionDate);

        this.sessionInfo = sessionInfo;
        this.sessionDate = sessionDate;
        this.coverImage = validateCoverImage(coverImage);
    }

    private CoverImage validateCoverImage(final CoverImage coverImage) {
        if (coverImage == null) {
            return CoverImage.defaultCoverImage();
        }

        return coverImage;
    }

    private void validateSession(final SessionInfo sessionInfo, final SessionDate sessionDate) {
        Assert.notNull(sessionInfo, "session info cannot be null");
        Assert.notNull(sessionDate, "session date cannot be null");
    }

    public CoverImage getCoverImage() {
        return this.coverImage;
    }

    public long getPrice() {
        return this.sessionInfo.getPrice();
    }

    public boolean isPaidSession() {
        return this.sessionInfo.isPaidSession();
    }
}
