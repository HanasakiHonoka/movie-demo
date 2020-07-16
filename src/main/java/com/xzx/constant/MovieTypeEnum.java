package com.xzx.constant;

public enum MovieTypeEnum {
    JU_QING("剧情", "genre_剧情"),
    AI_QING("爱情", "genre_爱情"),
    XI_JU("喜剧", "genre_喜剧"),
    DONG_ZUO("动作", "genre_动作"),
    JING_SONG("惊悚","genre_惊悚"),
    DONG_HUA("动画", "genre_动画"),
    XUAN_YI("悬疑", "genre_悬疑"),
    MAO_XIAN("冒险", "genre_冒险"),
    FAN_ZUI("犯罪", "genre_犯罪"),
    ZHAN_ZHENG("战争", "genre_战争"),
    KONG_BU("恐怖", "genre_恐怖"),
    QIHUAN_QIHUAN("奇幻", "genre_奇幻"),
    ER_TONG("儿童", "genre_儿童"),
    JI_LU_PIAN("纪录片", "genre_纪录片"),
    QING_CHUN("青春", "genre_青春")
    ;

    private String rawName;
    private String paramName;

    MovieTypeEnum(String rawName, String paramName) {
        this.rawName = rawName;
        this.paramName = paramName;
    }

    public String getRawName() {
        return rawName;
    }

    public String getParamName() {
        return paramName;
    }

    public static MovieTypeEnum getByRaWName(String name) {
        MovieTypeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            MovieTypeEnum t = var1[var3];
            if (t.getRawName().equals(name)) {
                return t;
            }
        }

        return null;
    }

}
