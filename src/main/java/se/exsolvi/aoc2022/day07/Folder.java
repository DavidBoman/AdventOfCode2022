package se.exsolvi.aoc2022.day07;

import java.util.*;
import java.util.stream.Collectors;

public class Folder extends FsObject {
    private final List<FsObject> children = new ArrayList<>();

    public Folder(String name, Folder parent) {
        super(name, parent);
    }

    @Override
    protected int getSize() {
        return this.children.stream().map(FsObject::getSize).reduce(0, Integer::sum);
    }

    public Folder createFolder(String name) {
        Folder folder = new Folder(name, this);
        addChild(folder);
        return folder;
    }

    public File createFile(String name) {
        return createFile(name,0);
    }
    public File createFile(String name, int size ) {
        File file = new File(name, this, size);
        addChild(file);
        return file;
    }

    public Folder chdir(String name) {
        if (name.equals("/")) {
            if (parent == null) {
                return this;
            } else {
                return parent.chdir("/");
            }
        }
        if (name.equals("..")) {
            return parent;
        } else {
            return (Folder) getChild(name);
        }
    }

    public Set<Folder> getFolders(boolean recursive) {
        if (recursive) {
            Set<Folder> folders = getFolders(false);
            folders.addAll(folders.stream().map(f -> f.getFolders(true)).flatMap(Collection::stream).collect(Collectors.toSet()));
            return folders;
        } else {
            Set<Folder> collect = children.stream().filter(Folder.class::isInstance).map(Folder.class::cast).collect(Collectors.toSet());
            return collect;
        }
    }

    public Set<File> getFiles(boolean recursive) {
        if (recursive) {
            Set<File> files = getFiles(false);
            files.addAll(getFolders(false).stream().map(f -> f.getFiles(true)).flatMap(Collection::stream).collect(Collectors.toSet()));
            return files;
        } else {
            return this.children.stream().filter(c -> c instanceof File).map(c -> (File) c).collect(Collectors.toSet());
        }
    }

    protected void addChild(FsObject child) {
        this.children.add(child);
    }

    public String toString() {
        return "[" + getName() + " | Size: " + getSize() + ", Folders: " + getFolders(false).size() + ", Files: " + getFiles(false).size() + "]";
    }

    private FsObject getChild(String name) {
        return this.children.stream().filter(c ->c.getName().equals(name)).findFirst().get();
    }
}
