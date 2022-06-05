package main.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

@Entity
@Table(name = "folders")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "repeating", nullable = false)
    private int repeating;

    @JsonManagedReference
    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, targetEntity = Card.class)
    private List<Card> cards;

    public Folder() {

    }

    public Folder(Long id, String name, int repeating, List<Card> cards) {
        this.id = id;
        this.name = name;
        this.repeating = repeating;
        this.cards = cards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepeating() {
        return repeating;
    }

    public void setRepeating(int repeating) {
        this.repeating = repeating;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
