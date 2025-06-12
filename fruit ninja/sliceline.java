import java.awt.*;

public class SliceLine {
    int x1, y1, x2, y2;

    public SliceLine(int x2, int y2) {
        this.x1 = this.x2;
        this.y1 = this.y2;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine(x1, y1, x2, y2);
    }
}
