package com.rcs_rocket_english.exc2Objects;
public class RecordVocabulary {
    private int layoutId;
    private String category;
    private String title;
    private String r1;
    private String r2;
    private String r3;
    private String r4;
    private String r5;
    private String r6;
    private String used;

    public RecordVocabulary(int layoutId, String category, String title, String r1, String r2, String r3, String r4, String r5, String r6, String used) {
        this.layoutId = layoutId;
        this.category = category;
        this.title = title;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
        this.r6 = r6;
        this.used = used;
    }

    // Getters y Setters
    public int getLayoutId() {
        return layoutId;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getR1() {
        return r1;
    }

    public String getR2() {
        return r2;
    }

    public String getR3() {
        return r3;
    }

    public String getR4() {
        return r4;
    }

    public String getR5() {
        return r5;
    }

    public String getR6() {
        return r6;
    }

    public String getUsed() {
        return used;
    }
}
