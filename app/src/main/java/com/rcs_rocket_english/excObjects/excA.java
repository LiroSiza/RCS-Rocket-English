package com.rcs_rocket_english.excObjects;

public class excA {
    int layoutId;
    String category;
    String title;
    String phrase;
    String option1;
    String option2;
    String option3;
    String answer;
    int used;

    public excA(int layoutId, String category, String title, String phrase,
                    String option1, String option2, String option3, String answer, int used) {
        this.layoutId = layoutId;
        this.category = category;
        this.title = title;
        this.phrase = phrase;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answer = answer;
        this.used = used;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCategory() {
        return category;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getPhrase() {
        return phrase;
    }

    public String getTitle() {
        return title;
    }

    public int getUsed() {
        return used;
    }
}
