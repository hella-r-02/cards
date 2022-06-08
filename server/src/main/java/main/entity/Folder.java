package main.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "num_of_levels", nullable = false)
    private int numOfLevels;

    @JsonManagedReference
    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, targetEntity = Level.class)
    private List<Level> levels;

    @JsonIgnore
    @ManyToOne(optional = false, targetEntity = Category.class)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Folder() {

    }

    public Folder(Long id, String name, int numOfLevels, List<Level> levels, Category category) {
        this.id = id;
        this.name = name;
        this.numOfLevels = numOfLevels;
        this.levels = levels;
        this.category = category;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
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

    public int getNumOfLevels() {
        return numOfLevels;
    }

    public void setNumOfLevels(int numOfLevels) {
        this.numOfLevels = numOfLevels;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
