package main.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

@Entity
@Table(name ="levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="next_replay")
    @NotNull
    private Date next_replay;

    @Column(name="num_of_level")
    @NotNull
    private Long num_of_level;

    @JsonIgnore
    @ManyToOne(optional = false, targetEntity = Folder.class)
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder;

    @JsonManagedReference
    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, targetEntity = Card.class)
    private List<Card> cards;


    public Level() {
    }

    public Level(Long id, Date next_replay, Long num_of_level, Folder folder, List<Card> cards) {
        this.id = id;
        this.next_replay = next_replay;
        this.num_of_level = num_of_level;
        this.folder = folder;
        this.cards = cards;
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
