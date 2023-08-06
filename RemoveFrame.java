//RemoveFrame

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class RemoveFrame extends JFrame{
Container c;
JLabel labUserid;
JTextField txtUserid;
JButton btnRemove,btnBack;

RemoveFrame(){
c=getContentPane();
c.setLayout(null);

labUserid = new JLabel("Enter your user-id");
txtUserid = new JTextField(15);

btnRemove=new JButton("Remove");
btnBack=new JButton("Back");

Font f = new Font("Arial",Font.BOLD,20);

labUserid.setFont(f);
txtUserid.setFont(f);
btnRemove.setFont(f);
btnBack.setFont(f);

labUserid.setBounds(110,90,300,30);
txtUserid.setBounds(75,130,250,30);
btnRemove.setBounds(110,200,150,30);
btnBack.setBounds(110,250,150,30);


c.add(labUserid);
c.add(txtUserid);
c.add(btnRemove);
c.add(btnBack);


ActionListener a1 = (ae) -> {
    try {
        if (txtUserid.getText().equals("")) {
            JOptionPane.showMessageDialog(c, "User Id should not be empty");
        } else {
            int id = Integer.parseInt(txtUserid.getText());
            if (id <= 0) {
                txtUserid.setText("");
                txtUserid.requestFocus();
                JOptionPane.showMessageDialog(c, "User id must be greater than Zero");
            } else {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306/airdish";
                String un = "root";
                String pw = "abc456";
                Connection con = DriverManager.getConnection(url, un, pw);

                String sql = "select * from users where user_id=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String sql2 = "delete from users where user_id=?";
                    PreparedStatement pst2 = con.prepareStatement(sql2);
                    pst2.setInt(1, id);
                    pst2.executeUpdate();
                    con.close();
                    JOptionPane.showMessageDialog(c, "List deleted");
                    txtUserid.setText("");
                    txtUserid.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(c, id + " does not exist");
                    txtUserid.setText("");
                    txtUserid.requestFocus();
                }
                con.close();
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(c, " " + e);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(c, "Please enter a valid positive integer for User Id");
    }
};
btnRemove.addActionListener(a1);

ActionListener a2 = (ae) -> {
MainFrame l = new MainFrame();
dispose();
};
btnBack.addActionListener(a2);

setTitle("AIR-DISH");
setSize(400,500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}