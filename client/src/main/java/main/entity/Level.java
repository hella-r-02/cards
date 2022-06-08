package main.entity;

import java.util.Date;
import java.util.List;

public class Level {
    private Long id;
    private Date next_replay;
    private Folder folder;
    private List<Card> cards;
    private Long num_of_level;

    public Level() {
    }

    public Level(Long id, Date next_replay, Folder folder, List<Card> cards, Long num_of_level) {
        this.id = id;
        this.next_replay = next_replay;
        this.folder = folder;
        this.cards = cards;
        this.num_of_level = num_of_level;
    }

    public Long getNum_of_level() {
        return num_of_level;
    }

    public void setNum_of_level(Long num_of_level) {
        this.num_of_level = num_of_level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNext_replay() {
        return next_replay;
    }

    public void setNext_replay(Date next_replay) {
        this.next_replay = next_replay;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
