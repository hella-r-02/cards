package main.entity;

public class Card {
    private Long id;
    private Folder folder;
    private String question;
    private String answer;
    private int level;
    private byte[] question_image;
    private byte[] answer_image;

    public Card(Long id, Folder folder, String question, String answer, int level, byte[] question_image, byte[] answer_image) {
        this.id = id;
        this.folder = folder;
        this.question = question;
        this.answer = answer;
        this.level = level;
        this.question_image = question_image;
        this.answer_image = answer_image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

