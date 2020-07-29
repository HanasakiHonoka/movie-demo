package com.xzx.constant;


public enum SearchTypeEnum {
    MOVIE(1),
    DIRECTOR(2),
    ACTOR(3),
    SCENARIST(4),

    ;

    private Integer id;

    SearchTypeEnum(int id) {
        this.id = id;
    }

    public Integer getValue()  {
        return this.id;
    }

    public static SearchTypeEnum getByValue(Integer id) {
        SearchTypeEnum[] values = values();
        for (SearchTypeEnum value : values) {
            if (value.id.equals(id)) {
                return value;
            }
        }
        return null;
    }
}
