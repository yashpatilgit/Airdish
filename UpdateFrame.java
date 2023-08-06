// Update Frame

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UpdateFrame extends JFrame {
    Container c;
    JLabel labUserid, labChannel1,labChannel2,labChannel3;
    JTextField txtUserid, txtChannel1,txtChannel2,txtChannel3;
    JButton btnSave, btnBack;

    UpdateFrame() {
        c = getContentPane();
        c.setLayout(null);

       labUserid = new JLabel("Enter your user-id");
	txtUserid = new JTextField(15);
	labChannel1=new JLabel("Enter first channel name");
	txtChannel1 = new JTextField(15);
	labChannel2=new JLabel("Enter second channel name");
	txtChannel2 = new JTextField(15);
	labChannel3=new JLabel("Enter third channel name");
	txtChannel3 = new JTextField(15);

        btnSave = new JButton("Save");
        btnBack = new JButton("Back");

        Font f = new Font("Arial", Font.BOLD, 20);

        labUserid.setFont(f);
        txtUserid.setFont(f);
        labChannel1.setFont(f);
        txtChannel1.setFont(f);
  labChannel2.setFont(f);
        txtChannel2.setFont(f);
  labChannel3.setFont(f);
        txtChannel3.setFont(f);
        btnSave.setFont(f);
        btnBack.setFont(f);

labUserid.setBounds(110,40,300,30);
txtUserid.setBounds(75,80,250,30);

labChannel1.setBounds(80,135,300,30);
txtChannel1.setBounds(75,175,250,30);

labChannel2.setBounds(71,215,300,30);
txtChannel2.setBounds(75,250,250,30);

labChannel3.setBounds(80,290,300,30);
txtChannel3.setBounds(75,320,250,30);

btnSave.setBounds(130,400,150,30);
btnBack.setBounds(130,450,150,30);

c.add(labUserid);
c.add(txtUserid);
c.add(labChannel1);
c.add(txtChannel1);
c.add(labChannel2);
c.add(txtChannel2);
c.add(labChannel3);
c.add(txtChannel3);
c.add(btnSave);
c.add(btnBack);

        ActionListener a1 = (ae) -> {
            try {
                if (txtUserid.getText().equals("")) {
                    throw new Exception("User Id should not be empty");
                }

                int id = Integer.parseInt(txtUserid.getText());
                if (id <= 0) {
                    txtUserid.setText("");
                    txtUserid.requestFocus();
                    throw new Exception("User Id should be greater than Zero");
                }

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306/airdish";
                String un = "root";
                String pw = "abc456";
                Connection con = DriverManager.getConnection(url, un, pw);

                String sql = "select * from users where user_id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String name1 = txtChannel1.getText();
		if(txtChannel1.getText().equals("")){
		txtChannel1.setText("");
                txtChannel1.requestFocus();
		throw new Exception("Channel name should not be empty");}

		 if (!name1.matches("[a-zA-Z ]+")) {
                        txtChannel1.setText("");
                        txtChannel1.requestFocus();
                        throw new Exception("Channel name should contain letters only!");
                    }
                    if (name1.length() < 2) {
                        throw new Exception("Channel name should not be a letter");
                    }

		String name2 = txtChannel2.getText();
		if(txtChannel2.getText().equals("")){
		throw new Exception("Channel name should not be empty");}

		 if (!name2.matches("[a-zA-Z ]+")) {
                        txtChannel2.setText("");
                        txtChannel2.requestFocus();
                        throw new Exception("Channel name should contain letters only!");
                    }
                    if (name2.length() < 2) {
                        throw new Exception("Channel name should not be a letter");
                    }

		String name3 = txtChannel3.getText();
		if(txtChannel3.getText().equals("")){
		throw new Exception("Channel name should not be empty");}

		 if (!name3.matches("[a-zA-Z ]+")) {
                        txtChannel3.setText("");
                        txtChannel3.requestFocus();
                        throw new Exception("Channel name should contain letters only!");
                    }
                    if (name3.length() < 2) {
                        throw new Exception("Channel name should not be a letter");
                    }
		
		if (txtChannel1.getText().equals("") || txtChannel2.getText().equals("") || txtChannel3.getText().equals("")) {
                        throw new Exception("Channel field should not be empty");}

                    String sqll = "update users set channel1=?,channel2=?,channel3=? where user_id=?";
                    PreparedStatement pst = con.prepareStatement(sqll);
                    pst.setString(1, name1);
			pst.setString(2, name2);
			pst.setString(3, name3);
                    pst.setInt(4, id);
                    pst.executeUpdate();
                    con.close();
                    JOptionPane.showMessageDialog(c, "Channel List Updated");
                    txtUserid.setText("");
                    txtChannel1.setText("");
		txtChannel2.setText("");
		txtChannel3.setText("");
                    txtUserid.requestFocus();
                } else {
                    throw new Exception(id + " doesn't exists");
                }
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(c, " " + e);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(c, "User id should be an integer");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(c, " " + e.getMessage());
            }
        };

        btnSave.addActionListener(a1);

        ActionListener a2 = (ae) -> {
            MainFrame l = new MainFrame();
            dispose();
        };
        btnBack.addActionListener(a2);

        setTitle("AIR-DISH");
        setSize(425,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
