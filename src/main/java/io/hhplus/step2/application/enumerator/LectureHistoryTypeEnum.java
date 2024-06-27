package io.hhplus.step2.application.enumerator;

public enum LectureHistoryTypeEnum {

    SUCCESS(0, "성공"),
    CAPACITY_FAIL(1, "인원 초과 실패"),

    APPLICATION_DATE_FAIL(2, "수강 신청 기간 아님 실패");
    private final Integer value;
    private final String name;

    private LectureHistoryTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Integer getValue() {
        return this.value;
    }
}
