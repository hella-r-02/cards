package main.entity;

import java.util.List;

public class Folder {
    private Long id;
    private String name;
    private int numOfLevels;
    private List<Level> levels;
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

    public int getNumOfLevels() {
        return numOfLevels;
    }

    public void setNumOfLevels(int numOfLevels) {
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
