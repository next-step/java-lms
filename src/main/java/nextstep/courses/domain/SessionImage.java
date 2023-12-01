package nextstep.courses.domain;

import nextstep.courses.enumeration.ExtensionType;

public class SessionImage extends BaseEntity {

    private final Long id;
    private final Long sessionId;
    private final String imageUrl;
    private final ExtensionType extensionType;

    public SessionImage(Long id, Long sessionId, String imageUrl, ExtensionType extensionType) {
        validate(id, sessionId, imageUrl, extensionType);
        this.id = id;
        this.sessionId = sessionId;
        this.imageUrl = imageUrl;
        this.extensionType = extensionType;
    }

    private void validate(Long id, Long sessionId, String imageUrl, ExtensionType extensionType) {

    }
}
