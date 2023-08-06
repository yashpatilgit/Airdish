//Main Frame which will consists of add new channel,remove a channel,view the list of channels

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame{
Container c;
JButton btnAdd,btnView,btnRemove,btnUpdate;
JLabel label,labeltitle,labeltitle2,labeldesc;

MainFrame(){
c = getContentPane();
c.setLayout(null);

btnAdd = new JButton("New User");
btnView = new JButton("View channel list");
btnUpdate = new JButton("Update channel list");
btnRemove = new JButton("Remove channel list");
label = new JLabel();
labeltitle = new JLabel("AIR-DISH");
labeltitle2 = new JLabel("Dish of your happiness");
labeldesc = new JLabel("Thankyou for joining us.");


Font f = new Font("Arial",Font.BOLD,20);
Font ff = new Font("Arial",Font.BOLD,40);
Font fff = new Font("Arial",Font.BOLD,10);

btnAdd.setFont(f);
btnView.setFont(f);
btnUpdate.setFont(f);
btnRemove.setFont(f);
label.setFont(f);
labeltitle.setFont(ff);
labeltitle2.setFont(fff);
labeldesc.setFont(f);
label.setIcon(new ImageIcon("ss.png"));

btnAdd.setBounds(400,200,300,30);
btnView.setBounds(400,250,300,30);
btnUpdate.setBounds(400,300,300,30);
btnRemove.setBounds(400,350,300,30);
label.setBounds(20,40,308,308);
labeltitle.setBounds(470,70,300,50);
labeltitle2.setBounds(500,100,300,30);
labeldesc.setBounds(300,400,300,30);

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnRemove);
c.add(label);
c.add(labeltitle);
c.add(labeltitle2);
c.add(labeldesc);

ActionListener a1 = (ae) -> {
AddFrame a = new AddFrame();
dispose();
};
btnAdd.addActionListener(a1);

ActionListener a2 = (ae) -> {
ViewFrame v = new ViewFrame();
dispose();
};
btnView.addActionListener(a2);

ActionListener a3 = (ae) -> {
UpdateFrame a = new UpdateFrame();
dispose();
};
btnUpdate.addActionListener(a3);

ActionListener a4 = (ae) -> {
RemoveFrame r = new RemoveFrame();
dispose();
};
btnRemove.addActionListener(a4);


setTitle("AIR-DISH");
setSize(800,500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}