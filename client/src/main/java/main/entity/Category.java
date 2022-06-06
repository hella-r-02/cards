package main.entity;

import java.util.List;

public class Category {
    private Long id;
    private String name;
    private List<Folder> folders;

    public Category() {
    }

    public Category(Long id, String name, List<Folder> folders) {
        this.id = id;
        this.name = name;
        this.folders = folders;
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

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }
}
