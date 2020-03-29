import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

public class ConsumerThread extends Thread {
    ChargingBar con;
    int time;
    JLabel timeLabel;
    JLabel finishLabel;
    TabAndThreadEx tabAndThreadEx;
    boolean isFinished = false;

    ConsumerThread(TabAndThreadEx tabAndThreadEx, JLabel timeLabel, JLabel finishLabel, int time, ChargingBar con) {
        this.con = con;
        this.time = time;
        this.timeLabel = timeLabel;
        this.tabAndThreadEx = tabAndThreadEx;
        this.finishLabel = finishLabel;

    }

    public void run() {
        tabAndThreadEx.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if (!isFinished) {
                    con.fill();
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }

        });
        while (!isFinished) {
            try {
                sleep(200);
                time--;
                timeLabel.setText(time + "");
                con.consume();
                if (time == 0) {
                    isFinished = true;
                    finishLabel.setVisible(isFinished);
                }
            } catch (InterruptedException ie) {
                return;
            }
        }

    }

}