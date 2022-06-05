package main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.sun.istack.NotNull;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false, targetEntity = Folder.class)
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder;

    @Column(name = "question")
    @NotNull
    private String question;

    @Column(name = "answer")
    @NotNull
    private String answer;

    @Column(name = "level", nullable = false)
    private int level;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name="question_image")
    private byte[] question_image;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name="answer_image")
    private byte[] answer_image;

    public Card() {

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

    public Card(Long id, Folder folder, String question, String answer, int level, byte[] question_image, byte[] answer_image) {
        this.id = id;
        this.folder = folder;
        this.question = question;
        this.answer = answer;
        this.level = level;
        this.question_image = question_image;
        this.answer_image = answer_image;
    }
}
