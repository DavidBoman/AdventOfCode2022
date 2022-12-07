package se.exsolvi.aoc2022.day07;

import lombok.Getter;
import lombok.NonNull;

public abstract class FsObject {

    @Getter
    @NonNull
    private final String name;

    public enum FsObjectType {
        FOLDER,
        FILE
    }
    private FsObjectType type;
    protected final Folder parent;

    protected FsObject(String name, Folder parent) {
        this.name = name;
        this.parent = parent;
    }

    protected abstract int getSize();

}
