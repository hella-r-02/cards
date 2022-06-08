package main.entity;

import java.util.List;

public class Folder {
    private Long id;
    private String name;
    private Long numOfLevels;
    private List<Level> levels;
    private Category category;

    public Long getNumOfLevels() {
        return numOfLevels;
    }

    public void setNumOfLevels(Long numOfLevels) {
        this.numOfLevels = numOfLevels;
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

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
