import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

class ChargingBar extends JLabel {
    int barSize = 0;
    int maxBarSize;

    ChargingBar(int maxBarSize) {
        this.maxBarSize = maxBarSize;
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.magenta);
        int width = (int) (((double) (this.getWidth())) / maxBarSize * barSize);
        if (width == 0)
            return;
        g.fillRect(0, 0, width, this.getHeight());
    }

    synchronized void fill() {
        if (barSize == maxBarSize) {
            return;

        } else {
            barSize++;
            this.repaint();
            this.notify();
        }
    }

    synchronized void consume() {
        if (barSize == 0) {
            return;
        }
        barSize--;
        this.repaint();
        this.notify();
    }
}