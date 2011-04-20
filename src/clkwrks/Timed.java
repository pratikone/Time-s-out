package clkwrks;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

/**
 *
 * Small widget clock to be on top right side of screen
 * displays time and other essential notifications
 * always on top
 * @author Pratik Anand <pratik.preet@gmail.com>
 */
public final class Timed extends JDialog implements ActionListener, MouseMotionListener, MouseListener {

    String date;
    JButton jb = new JButton("Ninja");
    JLabel label = new JLabel(" " + new Date());
    DateFormat df=new SimpleDateFormat("HH:mm:ss");
    
    Point p_initial = null;

    //transparency
    /*
    DateFormat df=new  SimpleDateFormat("MM/dd/yyyy");
    Date today=Calendar.getInstance().getTime();
    date=df.format("today");
     */
    
    //system tray
    SystemTray systray = SystemTray.getSystemTray();
    final PopupMenu popup = new PopupMenu();
    final MenuItem mItem1 = new MenuItem("Exit");
    
    URL myurl = this.getClass().getResource("/images/wlan.png");
    Toolkit tk = this.getToolkit();
    Image image = tk.getImage(myurl);
    
    //Image image = Toolkit.getDefaultToolkit().getImage("src/images/wlan.png");
    TrayIcon tray = new TrayIcon(image,"Timed",popup);
     
    

    
    
    
    Timed() throws AWTException{
        //super("Trial by fire");
        setLayout(new FlowLayout());

        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), label.getFont().getSize()+6));
        Container content = getContentPane();
        content.add(label, JLabel.CENTER);
        addMouseMotionListener(this);
        addMouseListener(this);


        javax.swing.Timer t = new javax.swing.Timer(1000, this);
        setUndecorated(true);
        com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.6f);
         
        //system tray
        mItem1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        popup.add(mItem1);
        systray.add(tray);
        tray.setImageAutoSize(true);
        t.start();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //date = new Date().toString();
       // System.out.println(df.format(new Date()));

        date=df.format(new Date());
        
        label.setText(date);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        Point p = e.getLocationOnScreen();    //global location of mouse pointer
        this.setLocation(new Point(-p_initial.x + p.x, -p_initial.y + p.y));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public static void main(String args[]) throws AWTException {

        Timed tm = new Timed();
        tm.setSize(125, 23);
        //tm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
              
        
        
        //tm.setUndecorated(true);
        //tm.getRootPane().putClientProperty("Window.alpha", new Float(0.8f)); works on Mac OS X only
        tm.setAlwaysOnTop(true);
        tm.setVisible(true);

    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        p_initial = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        //this.setLocation(e.getX(), e.getY());
    }
}
