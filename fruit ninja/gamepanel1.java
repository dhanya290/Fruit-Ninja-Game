import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, MouseMotionListener {
    private java.util.List<Fruit> fruits = new ArrayList<>();
    private java.util.List<SliceLine> slices = new ArrayList<>();
    private Timer timer;
    private int score = 0;

    public GamePanel() {
        setBackground(Color.BLACK);
        addMouseMotionListener(this);
        timer = new Timer(16, this); // ~60 FPS
    }

    public void startGame() {
        timer.start();
        new Timer(1000, e -> spawnFruit()).start();
    }

    private void spawnFruit() {
        Random rand = new Random();
        int x = rand.nextInt(getWidth() - 40);
        fruits.add(new Fruit(x, 0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Fruit fruit : fruits) {
            fruit.update();
        }

        Iterator<Fruit> it = fruits.iterator();
        while (it.hasNext()) {
            Fruit fruit = it.next();
            for (SliceLine slice : slices) {
                if (fruit.getBounds().intersectsLine(slice.x1, slice.y1, slice.x2, slice.y2)) {
                    it.remove();
                    score += 10;
                    break;
                }
            }
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Fruit fruit : fruits) {
            fruit.draw(g);
        }

        for (SliceLine slice : slices) {
            slice.draw(g);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (slices.size() > 5) {
            slices.remove(0);
        }
        slices.add(new SliceLine(e.getX(), e.getY()));
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
