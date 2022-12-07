package se.exsolvi.aoc2022.day08;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Tree {

    @Getter
    @NonNull
    private final Integer height;

    @Getter
    @Setter
    @NonNull
    private boolean visible = false;

    @Setter
    private int visibilityRight = 0;
    @Setter
    private int visibilityLeft = 0;
    @Setter
    private int visibilityUp = 0;
    @Setter
    private int visibilityDown = 0;

    public int incVisRight() {
        return ++visibilityRight;
    }

    public int incVisLeft() {
        return ++visibilityLeft;
    }

    public int incVisUp() {
        return ++visibilityUp;
    }

    public int incVisDown() {
        return ++visibilityDown;
    }

    public int getScenicScore() {
        return visibilityRight*visibilityLeft*visibilityUp*visibilityDown;
    }

    public void setVisible() {
        this.setVisible(true);
    }

    public String toString() {
        return height.toString();
    }
}
