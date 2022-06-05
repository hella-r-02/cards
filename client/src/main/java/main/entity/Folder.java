package main.entity;

import java.util.List;

public class Folder {
    private Long id;
    private String name;
    private Long repeating;
    private List<Card> cards;

    public Long getRepeating() {
        return repeating;
    }

    public void setRepeating(Long repeating) {
        this.repeating = repeating;
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

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
