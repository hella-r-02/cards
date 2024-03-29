package main.entity;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonBackReference
    @ManyToOne(optional = false, targetEntity = Level.class)
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @Column(name = "question")
    @NotNull
    private String question;

    @Column(name = "answer")
    @NotNull
    private String answer;

    @Column(name = "next_replay")
    @NotNull
    private Date next_replay;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "question_image")
    private byte[] question_image;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "answer_image")
    private byte[] answer_image;

    public Card() {

    }

    public Card(Long id, Level level, String question, String answer, Date next_replay, byte[] question_image, byte[] answer_image) {
        this.id = id;
        this.level = level;
        this.question = question;
        this.answer = answer;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
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
