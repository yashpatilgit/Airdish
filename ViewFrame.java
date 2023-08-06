//ViewFrame

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ViewFrame extends JFrame{
Container c;
JLabel labUserid;
JTextField txtUserid;
TextArea taData;
JButton btnSearch,btnBack;

ViewFrame(){
c=getContentPane();
c.setLayout(null);

labUserid=new JLabel("Enter your user-id");
txtUserid=new JTextField(15);
taData=new TextArea(10,60);
btnSearch=new JButton("Search");
btnBack=new JButton("Back");
		
Font f=new Font("Arial",Font.BOLD,20);
	
labUserid.setFont(f);
txtUserid.setFont(f);
taData.setFont(f);
btnSearch.setFont(f);
btnBack.setFont(f);
	
c.add(labUserid);
c.add(txtUserid);
c.add(taData);
c.add(btnSearch);
c.add(btnBack);
		
labUserid.setBounds(100,20,300,30);
txtUserid.setBounds(40,60,300,30);
taData.setBounds(40,100,310,200);
btnSearch.setBounds(130,340,150,40);
btnBack.setBounds(130,390,150,40);

ActionListener a1 = (ae) -> {
    try {
        if (txtUserid.getText().equals("")) {
            JOptionPane.showMessageDialog(c, "User Id should not be empty");
        } else {
            int id = Integer.parseInt(txtUserid.getText());
            if (id <= 0) {
                JOptionPane.showMessageDialog(c, "User id must be greater than Zero");
            } else {
                StringBuffer data = new StringBuffer();

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
                    String name1 = rs.getString(2);
                    String name2 = rs.getString(3);
                    String name3 = rs.getString(4);
                    data.append("First Channel name  = " + name1 + "\n" + "Second Channel name  = " + name2 + "\n" + "Third Channel name  = " + name3 + "\n");
                    taData.setText(data.toString());
                } else {
                    JOptionPane.showMessageDialog(c, "User Id doesn't exist");
                    txtUserid.setText("");
                    taData.setText("");
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

btnSearch.addActionListener(a1);

ActionListener a2=(ae) ->{
MainFrame ll =new MainFrame();
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
