package com.rcs_rocket_english.excObjects;

public class excC {

    private int id;
    private int layoutId;
    private String category;
    private String title;
    private String text1;
    private String text2;
    private String answer1;
    private String answer2;
    private boolean used;

    // Constructor
    public excC(int id, int layoutId, String category, String title,
                 String text1, String text2, String answer1,
                 String answer2, boolean used) {
        this.id = id;
        this.layoutId = layoutId;
        this.category = category;
        this.title = title;
        this.text1 = text1;
        this.text2 = text2;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.used = used;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }


}
