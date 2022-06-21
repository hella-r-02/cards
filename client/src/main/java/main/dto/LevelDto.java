package main.dto;

import java.util.Date;
import java.util.List;

import main.entity.Card;
import main.entity.Category;
import main.entity.Folder;

public class LevelDto {
    private Long id;
    private Date next_replay;
    private Folder folder;
    private List<Card> cards;
    private Long num_of_level;
    private Category category;

    public LevelDto() {
    }

    public LevelDto(Long id, Date next_replay, Folder folder, List<Card> cards, Long num_of_level, Category category) {
        this.id = id;
        this.next_replay = next_replay;
        this.folder = folder;
        this.cards = cards;
        this.num_of_level = num_of_level;
        this.category = category;
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

    public Long getNum_of_level() {
        return num_of_level;
    }

    public void setNum_of_level(Long num_of_level) {
        this.num_of_level = num_of_level;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
