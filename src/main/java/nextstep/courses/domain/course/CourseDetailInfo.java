package nextstep.courses.domain.course;

import java.util.Objects;

public class CourseDetailInfo {

  public static final String COURSE_TITLE_IS_INCORRECT = "과정 명이 올바르지 않습니다. 다시 확인해주세요. input: %s";
  public static final String NEGATIVE_NUMBER_OR_ZERO_IS_NOT_ALLOWED = "유저 id는 0이나 음수일 수 없습니다. input: %s";

  private final Long creatorId;
  private final String titleName;

  public CourseDetailInfo(Long creatorId, String titleName) {
    validate(creatorId, titleName);
    this.creatorId = creatorId;
    this.titleName = titleName;
  }

  public Long getCreatorId() {
    return creatorId;
  }

  public String getTitleName() {
    return titleName;
  }

  private boolean validate(Long creatorId, String title) {
    if (creatorId <= 0) {
      throw new IllegalArgumentException(String.format(NEGATIVE_NUMBER_OR_ZERO_IS_NOT_ALLOWED, creatorId));
    }

    if (Objects.isNull(title) || title.isBlank()) {
      throw new IllegalArgumentException(String.format(COURSE_TITLE_IS_INCORRECT, title));
    }

    return true;
  }
}
