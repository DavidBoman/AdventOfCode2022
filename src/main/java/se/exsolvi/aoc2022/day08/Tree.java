package se.exsolvi.aoc2022.day08;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Tree implements Comparable {
    @NonNull
    private Tree[][] forest;

    @Getter
    @NonNull
    private final int x;
    @Getter
    @NonNull
    private final int y;

    @Getter
    @NonNull
    private final Integer height;

    @Getter
    @Setter
    @NonNull
    private boolean visible = false;
    public int getRightLoS() {
     int los = 0;
     if (x == forest[y].length-1) {
         los = 0;
     } else {
         for (int dx = x+1; dx < forest[y].length; dx++) {
             if (height <= forest[dx][y].getHeight()) {
                 los++;
                 break;
             } else {
                 los++;
             }

         }
     }
     return los;
 }
    public int getLeftLoS() {
        int los = 0;
        if (x == 0) {
            los = 0;
        } else {
            for (int dx = x-1; dx >= 0; dx--) {
                if (height <= forest[dx][y].getHeight()) {
                    los++;
                    break;
                } else {
                    los++;
                }

            }
        }
        return los;
    }
    public int getLowerLoS() {
        int los = 0;
        if (y == forest.length-1) {
            los = 0;
        } else {
            for (int dy = y+1; dy < forest.length; dy++) {
                if (height <= forest[x][dy].getHeight()) {
                    los++;
                    break;
                } else {
                    los++;
                }

            }
        }
        return los;
    }

    public int getUpperLoS() {
        int los = 0;
        if (this.y == 0) {
            los = 0;
        } else {
            for (int dy = y-1; dy >= 0; dy--) {
                if (height <= forest[x][dy].getHeight()) {
                    los++;
                    break;
                } else {
                    los++;
                }
            }
        }
        return los;
    }

    public int getScenicScore() {
     int rlos = getRightLoS();
     int llos = getLeftLoS();
     int ulos = getUpperLoS();
     int dlos = getLowerLoS();

        return rlos*llos*ulos*dlos;
    }
    public void setVisible() {
        this.setVisible(true);
    }

    public String toString() {
        return height.toString();
    }

    @Override
    public int compareTo(Object o) {
        Tree t = (Tree) o;
        return t.getHeight().compareTo(this.height);
    }
}
