package nextstep.courses.domain.image;

import nextstep.courses.InvalidImageFormatException;
import nextstep.courses.domain.SystemTimeStamp;
import nextstep.courses.domain.session.Session;

import java.time.LocalDateTime;

public class SessionImage {
    private long id;

    private String name;

    private long sessionId;

    private ImageFormat imageFormat;

    private SystemTimeStamp systemTimeStamp;


    public static SessionImage valueOf(long id, Session session, int size, int width, int height, String imageType) {
        return new SessionImage(id, "tmp", session.getSessionId()
                , new ImageFormat(size, width, height, ImageType.validateImageType(imageType))
                , new SystemTimeStamp(LocalDateTime.now(), null));
    }

    public SessionImage(long id, String name, long sessionId, int size, int width, int height, ImageType imageType) {
        this(id, name, sessionId, new ImageFormat(size, width, height, imageType), new SystemTimeStamp(LocalDateTime.now(), null));
    }

    public SessionImage(long id, String name, long sessionId, ImageFormat imageFormat, SystemTimeStamp systemTimeStamp) {
        this.id = id;
        this.name = name;
        this.sessionId = sessionId;
        this.imageFormat = imageFormat;
        this.systemTimeStamp = systemTimeStamp;
    }

    public String getName() {
        return name;
    }

    public long getSessionId() {
        return sessionId;
    }

    public ImageFormat getImageFormat() {
        return imageFormat;
    }

    public LocalDateTime getCreatedAt() {
        return systemTimeStamp.getCreatedAt();
    }

    public LocalDateTime getUpdatedAt() {
        return systemTimeStamp.getCreatedAt();
    }

}
