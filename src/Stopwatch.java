//Stopwatch object, MUCH CREDIT to whomever created the template stopwatch code (I think some colorado.edu/physics person, thank you!) 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Stopwatch extends JPanel
{
    final String TIME = "Time spent on ";
    private String name;
    private Timer myTimer;
    private JButton start;
    private JButton reset;
    private JLabel timeDisplay;

    private boolean isRunning = false;
    private int clockTick;          //number of clock ticks; tick can be 1.0 s or 0.1 s
    private double clockTime;       //time in seconds
    private String clockTimeString;
    
    Multimer mult;
    
    public Stopwatch(String n, Multimer m)
    {
        mult =  m;
        name = n;
        clockTick = 0;                 
        clockTime = ((double)clockTick)/10.0;
        timeDisplay = new JLabel();
        timeDisplay.setText(new Double(clockTime).toString());

        int TENTH_SEC = 100;
        myTimer = new Timer(TENTH_SEC, new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                clockTick++; 
                clockTime = ((double)clockTick)/10.0;
                clockTimeString = new Double(clockTime).toString();
                isRunning = true;
                timeDisplay.setText(clockTimeString);
            }
            
        });

        start = new JButton(TIME + name + ": ");
        start.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    if (isRunning){
                        isRunning = false;
                        myTimer.stop();
                    }
                    else{                        
                        isRunning = true;
                        for (Stopwatch s : mult.list)
                        {
                            s.stopTimer();
                        }
                        myTimer.start();

                    }
                }
        });

        reset = new JButton("Reset");
        reset.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                        clockTick = 0;
                        clockTime = ((double)clockTick)/10.0;
                        clockTimeString = new Double(clockTime).toString();
                        timeDisplay.setText(clockTimeString);
                }
        });               
        add(timeDisplay);
        add(start);
        add(reset);
 
    }
    public void stopTimer()
    {
        myTimer.stop();
        isRunning = false;
    }
    public void setTime(double d)
    {
        clockTick = (int) d;
    }
    public double getTime()
    {
        return clockTick;
    }
    public String getName()
    {
        return name;
    }
}
