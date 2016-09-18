package com.example.railroads.railroads;

/**
 * Created by Vinit Soni on 2016-09-17.
 */
public class AdvQuestion {
    private String question;
    private int questionType;
    private int answer;

    public AdvQuestion(String question, int questionType) {
        this.question = question;
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }
    public int getQuestionType (){
        return questionType;
    }
}
