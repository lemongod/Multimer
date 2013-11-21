import javax.swing.*;
import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

public class Multistopwatch {

    private Timer timer = new Timer();
    private JLabel timeLabel = new JLabel(" ", JLabel.CENTER);

    public Multistopwatch() {
        JFrame f = new JFrame("Seconds");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(timeLabel);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        timer.schedule(new UpdateUITask(), 0, 1000);
    }

    private class UpdateUITask extends TimerTask {

        int nSeconds = 0;
        @Override
        public void run() 
        {
            EventQueue.invokeLater(new Runnable() 
            {
                @Override
                public void run() 
                {
                    timeLabel.setText(String.valueOf(nSeconds++));
                }
            });
        }
    }
}