package se.exsolvi.aoc2022.day07;

public class File extends FsObject {
    private final int size;

    public File(String name, Folder parent, int size) {
        super(name, parent);
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
