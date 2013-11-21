import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class Multimer extends JFrame implements Serializable
{
    ArrayList<Stopwatch> list;
    private JFileChooser myChooser;
    final String TIME = "Time spent on ";
    
    public static void main(String[] args)
    {
        Multimer m = new Multimer();
    }
    public Multimer()
    {
        super("My Stop Watch");
        setTitle("My Multimer");
        list = new ArrayList<Stopwatch>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(0, 1));  
        myChooser = new JFileChooser(System.getProperties().getProperty("user.dir"));
        
        setJMenuBar(makeMenus());
        
        pack();
        setVisible(true);
    }
    public void addStopwatch(String n, double time)
    {
        Stopwatch sw = new Stopwatch(TIME + n + ": ", this);
        list.add(sw);
        sw.setTime(time);
        add(sw);
        pack();
    }
    private JMenuBar makeMenus () 
    {
        JMenuBar result = new JMenuBar();
        result.add(makeFileMenu());
        return result;
    }
    private JMenu makeFileMenu () 
    {
        JMenu result = new JMenu("File Menu");
        result.add(new AbstractAction("Create stopwatch") {
            public void actionPerformed(ActionEvent e)
            {
                String s = (String)JOptionPane.showInputDialog("Please enter task you're spending time on: ", "Work");
                addStopwatch(s, 0);
            }
        });
        result.add(new AbstractAction("Save") {
            public void actionPerformed(ActionEvent e) 
            {
                myChooser.setCurrentDirectory(new File("src/"));
                int retrival = myChooser.showSaveDialog(null);
                if (retrival == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileOutputStream fout = new FileOutputStream("list");
                        ObjectOutputStream oos = new ObjectOutputStream(fout);
                        oos.writeObject(list);
                        oos.close();
                        fout.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }   
        });
        result.add(new AbstractAction("Open") {
            public void actionPerformed (ActionEvent e) {
                myChooser.setCurrentDirectory(new File("src/"));
                int response = myChooser.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) 
                {
                    try {
                        new FileReader(myChooser.getSelectedFile());
                    }
                    catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("unserializing list");
                    try {
                        FileInputStream fin = new FileInputStream("list");
                        ObjectInputStream ois = new ObjectInputStream(fin);
                        ArrayList<Stopwatch> l = (ArrayList) ois.readObject();
                        list = l;
                        for (Stopwatch stop : list)
                        {
                            add(stop);
                        }
                        pack();
                        ois.close();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } 
            }
        });
        result.add(new AbstractAction("Quit") {
            public void actionPerformed (ActionEvent e) {
                System.exit(0);
            }
        });
        return result;
    }
}