package nextstep.courses.domain;

import nextstep.courses.domain.vo.ImageInfo;

public class SessionImage {

    private Long id;

    private ImageInfo imageInfo;

    private BaseInfo baseInfo;

    public SessionImage() {
    }

    public SessionImage(ImageInfo imageInfo,
                        Long creatorId) {
        this(null, imageInfo, new BaseInfo(creatorId));
    }

    public SessionImage(Long id,
                        ImageInfo imageInfo,
                        BaseInfo baseInfo) {
        this.id = id;
        this.imageInfo = imageInfo;
        this.baseInfo = baseInfo;
    }
}
