package dev.thekarancode.logUtilityClasses;

public enum LogCategory {
    INFO("#00FF00", ":D","ヽ(•‿•)ノ"),
    WARNING("#FFFF00", ":|","┐(˘_˘)┌"),
    ERROR("#FF0000", ":/","∩(◣_◢)∩");

    private final String colorCode;
    private final String emoticon1;
    private final String emoticon2;

    LogCategory(String colorCode, String emoticon1, String emoticon2) {
        this.colorCode = colorCode;
        this.emoticon1 = emoticon1;
        this.emoticon2 = emoticon2;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getEmoticon1() {
        return emoticon1;
    }

    public String getEmoticon2() {
        return emoticon2;
    }
}
