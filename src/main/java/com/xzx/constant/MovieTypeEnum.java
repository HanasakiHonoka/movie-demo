package com.xzx.constant;

public enum MovieTypeEnum {
    JU_QING("剧情", 4731),
    AI_QING("爱情", 5737),
    XI_JU("喜剧", 10255),
    DONG_ZUO("动作", 17809),
    JING_SONG("惊悚",2366),
    //DONG_HUA("动画", ),
    XUAN_YI("悬疑", 6568),
    FAN_ZUI("犯罪", 12922),
    ZHAN_ZHENG("战争", 9678),
    KONG_BU("恐怖", 824),
    JI_LU_PIAN("纪录片", 821),
    QING_CHUN("青春", 10562),
    QIHUAN_QIHUAN("奇幻", 23722),
    MAO_XIAN("冒险", 24216),
    ER_TONG("儿童", 226),
    ZHUAN_JI("传记", 2778),
    GU_ZHUANG("古装", 10136),
    LI_ZHI("励志", 2582),
    QIN_QING("亲情", 11703),
    KE_HUAN("科幻", 30194),
    LI_SHI("历史", 16153),

    ;

    private String rawName;
    private Integer score;

    MovieTypeEnum(String rawName, Integer score) {
        this.rawName = rawName;
        this.score = score;
    }

    public String getRawName() {
        return rawName;
    }

    public Integer getScore() {
        return score;
    }

    public static MovieTypeEnum getByRawName(String name) {
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
