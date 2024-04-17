package nextstep.courses.domain;

import nextstep.courses.domain.vo.ImageSpec;

public class SessionImage {

    private Long id;

    private ImageSpec imageSpec;

    private DataStatus dataStatus;

    public SessionImage() {
    }

    public SessionImage(ImageSpec imageSpec,
                        Long creatorId) {
        this(null, imageSpec, new DataStatus(creatorId));
    }

    public SessionImage(Long id,
                        ImageSpec imageSpec,
                        DataStatus dataStatus) {
        this.id = id;
        this.imageSpec = imageSpec;
        this.dataStatus = dataStatus;
    }
}
