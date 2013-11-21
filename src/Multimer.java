import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Multimer extends JFrame
{
    ArrayList<Stopwatch> list;
    Stopwatch sw1;
    Stopwatch sw2;
    
    public static void main(String[] args)
    {
            Multimer m = new Multimer();
    }
    public Multimer()
    {
            super("My Stop Watch");
            list = new ArrayList<Stopwatch>();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container myPane = getContentPane();
            this.setLayout(new BorderLayout());  
            
            sw1 = new Stopwatch("Name1", this);
            list.add(sw1);
            add(sw1, BorderLayout.SOUTH);
            
            sw2 = new Stopwatch("Name2", this);
            list.add(sw2);
            add(sw2, BorderLayout.NORTH);
            
            pack();
            setVisible(true);
    }
}