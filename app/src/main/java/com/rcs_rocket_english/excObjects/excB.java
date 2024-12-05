package com.rcs_rocket_english.excObjects;public class excB {
    private int id;
    private int layoutId;
    private String category;
    private String title;
    private String phrase;
    private String text;
    private boolean used;

    // Constructor
    public excB(int id, int layoutId, String category, String title,
                String phrase, String text, boolean used) {
        this.id = id;
        this.layoutId = layoutId;
        this.category = category;
        this.title = title;
        this.phrase = phrase;
        this.text = text;
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

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

}
