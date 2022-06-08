package main.entity;

import java.util.Date;

public class Card {
    private Long id;
//    private  folder;
    private String question;
    private String answer;
    private Level level;
    private Date next_replay;
    private byte[] question_image;
    private byte[] answer_image;

    public Card() {
    }

    public Card(Long id, String question, String answer, Level level, Date next_replay, byte[] question_image, byte[] answer_image) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.level = level;
        this.next_replay = next_replay;
        this.question_image = question_image;
        this.answer_image = answer_image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Date getNext_replay() {
        return next_replay;
    }

    public void setNext_replay(Date next_replay) {
        this.next_replay = next_replay;
    }

    public byte[] getQuestion_image() {
        return question_image;
    }

    public void setQuestion_image(byte[] question_image) {
        this.question_image = question_image;
    }

    public byte[] getAnswer_image() {
        return answer_image;
    }

    public void setAnswer_image(byte[] answer_image) {
        this.answer_image = answer_image;
    }
}

