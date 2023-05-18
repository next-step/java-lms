package nextstep.courses.domain;

import nextstep.courses.domain.enums.ImageType;

public class Image extends BaseEntity {
    private Long id;

    private String name;

    private String uri;

    private Long size;

    private ImageType type;

    protected Image() {
    }
}
