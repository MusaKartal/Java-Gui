
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class FinalGUI extends JFrame{
private static Connection conn;
private static PreparedStatement select;
private static PreparedStatement search;
private static PreparedStatement update;
private static PreparedStatement insert;
private static void connectDB() {
	try {				
		String serverAddr="MOSES\\SQLEXPRESS";
		String UserName ="sa";
		String Password ="1";
		String dbName="BIP2007_2021";
		String URL="jdbc:sqlserver://"+serverAddr+";databaseName="+dbName;			
		conn= DriverManager.getConnection(URL, UserName, Password);
				System.out.println("connect");
				
		insert =
				conn.prepareStatement(
				          "INSERT INTO Users " +
				          "(TCIDNumber, UserName, UserPassword, Department, Title, Gender) " +
				          "VALUES (?, ?, ?, ?, ?, ?) "
				        );
		       search =
		    		   conn.prepareStatement("SELECT * FROM Users WHERE TCIDNumber=?");		     
			   update =
					   conn.prepareStatement(
			        		"UPDATE Users SET UserName = ?, UserPassword = ?, Department = ?,  Title = ?, Gender = ? WHERE TCIDNumber=?");   		     
			    } 
	catch (SQLException e) 
	{
		e.printStackTrace();
		System.exit(0);
	}
	
		}
//JPanel
private final JPanel north_panel;
private final JPanel west_panel;
private final JPanel center_panel;
private final JPanel south_panel;
private final JPanel search_panel;
//JLabel 
private final JLabel lb_search;
private final JLabel lb_user_register;
private final JLabel lb_footer;
private final JLabel lb_id_num;
private final JLabel lb_name;
private final JLabel lb_password;
private final JLabel lb_depart;
private final JLabel lb_gender;
private final JLabel lb_title;
//JTextField
private final JTextField tf_search;
private final JTextField tf_id_num;
private final JTextField tf_name;
private final JPasswordField pf_password;
//JButton
private final JButton btn_new_user;
private final JButton btn_delete_user;
private final JButton btn_list_all_users;
private final JButton btn_list_by_depart;
private final JButton btn_close_window;
private final JButton btn_add_database;
//Layout
private final BorderLayout main_border_layout;
private final BorderLayout north_border_layout;
private final FlowLayout search_flow_layout;
private final GridLayout west_grid_layout;
private final FlowLayout south_flow_layout;
//JComboBox
private final JComboBox cb_depart_list;
private final JComboBox cb_title_list;
//JRadioButton
private final JRadioButton btn_female;
private final JRadioButton btn_male;
private final JRadioButton btn_other;
//ButtonGroup
private final ButtonGroup btn_group;
//List
private final String department_list[] = {"","Finance", "Sales","Accounting","HR","Logistics"};
private final String title_list[] = {"","CEO", "Manager","Programmer","Analyst","Specialist"};
	
	
	
public FinalGUI(){
	super("User Registration");
	
	main_border_layout = new BorderLayout(1,1);
	setLayout(main_border_layout);
	//Create panels 
	north_panel = new JPanel();
	north_panel.setBackground(Color.BLUE);
	south_panel = new JPanel();
	south_panel.setBackground(Color.red);
	west_panel = new JPanel();
	west_panel.setBackground(Color.YELLOW);
	center_panel = new JPanel();
	search_panel = new JPanel();
	search_panel.setBackground(Color.BLUE);
	center_panel.setLayout(null);
	//BorderLayout
	north_border_layout = new BorderLayout(1,1);
	north_panel.setLayout(north_border_layout);
	//FlowLayout
	south_flow_layout = new FlowLayout();
	south_panel.setLayout(south_flow_layout);
	west_grid_layout = new GridLayout(12,1,5,5);
	west_panel.setLayout(west_grid_layout);
	//FlowLayout
	search_flow_layout = new FlowLayout();
	search_panel.setLayout(search_flow_layout);
	search_flow_layout.setAlignment(FlowLayout.RIGHT);	
	//JLabel
	lb_user_register = new JLabel("USER REGISTRATION");
	lb_user_register.setFont(new Font("Serif", Font.BOLD, 15));
	lb_user_register.setForeground(Color.WHITE);
	lb_search = new JLabel("Search");
	lb_search.setForeground(Color.WHITE);
	lb_footer = new JLabel("2021 � PRU");
	lb_footer.setFont(new Font("Serif", Font.ITALIC, 12));
	lb_footer.setForeground(Color.white);
	lb_id_num = new JLabel("ID Number:");
	lb_id_num.setBounds(10, 20, 150, 15); 
	lb_name = new JLabel("Name and Surname:");
	lb_name.setBounds(10, 50, 150, 15);
	lb_password = new JLabel("Password:");
	lb_password.setBounds(10, 80, 150, 25);
	lb_depart = new JLabel("Department:");
	lb_depart.setBounds(10, 110, 150, 25);
	lb_title = new JLabel("Title:");
	lb_title.setBounds(10, 140, 150, 25);
	lb_gender = new JLabel("Gender:");
	lb_gender.setBounds(10, 170, 150, 25);
	//JTextField
	tf_search = new JTextField(10);
	tf_id_num = new JTextField(20);
	tf_id_num.setBounds(150,20,150,25);
	tf_name = new JTextField(20);
	tf_name.setBounds(150,50,150,25);
	//JPasswordField
	pf_password = new JPasswordField(20);
	pf_password.setBounds(150,80,150,25);
	//JButton
	btn_new_user = new JButton("New User");
	btn_delete_user = new JButton("Delete User");
	btn_list_all_users = new JButton("List All Users");
	btn_list_by_depart = new JButton("List By Department");
	btn_close_window = new JButton("Close Window");
	btn_add_database= new JButton("Add to Database");
	btn_add_database.setBounds(150, 250, 150, 25);
	//JComboBox
	cb_depart_list = new JComboBox(department_list);
	cb_depart_list.setBounds(150,110,150,25);
	cb_title_list = new JComboBox(title_list);
	cb_title_list.setBounds(150,140,150,25);
	//JRadioButton
	btn_female = new JRadioButton("female");
	btn_male = new JRadioButton("male");
	btn_other = new JRadioButton("other");
	//ButtonGroup
	btn_group = new ButtonGroup();
	btn_group.add(btn_female);
	btn_group.add(btn_male);
	btn_group.add(btn_other);
	//SetBounds
	btn_female.setBounds(150,170,100,25);
	btn_male.setBounds(250,170,100,25);
	btn_other.setBounds(350,170,100,25);
	//add panels to frame
	add(north_panel,BorderLayout.NORTH);
	add(south_panel,BorderLayout.SOUTH);
	add(west_panel,BorderLayout.WEST);
	add(center_panel,BorderLayout.CENTER);
	//NorthPanelAdd
	//LB
	north_panel.add(lb_user_register);
	north_panel.add(search_panel,BorderLayout.EAST);
	//WestPanelAdd	
	//BTN
	west_panel.add(btn_new_user);
	west_panel.add(btn_delete_user);
	west_panel.add(btn_list_all_users);
	west_panel.add(btn_list_by_depart);
	west_panel.add(btn_close_window);
	//SearchPanelAdd
	//LB
	search_panel.add(lb_search);
	//TF
	search_panel.add(tf_search);
	//SouthPanelAdd
	//LB
	south_panel.add(lb_footer);
	//CenterPanelAdd
	//LB
	center_panel.add(lb_id_num);
	center_panel.add(lb_name);
	center_panel.add(lb_password);
	center_panel.add(lb_depart);
	center_panel.add(lb_title);
	center_panel.add(lb_gender);
	//BTN
	center_panel.add(btn_female);
	center_panel.add(btn_male);
	center_panel.add(btn_other);
	center_panel.add(btn_add_database);
	//TF
	center_panel.add(tf_id_num);
	center_panel.add(tf_name);
	center_panel.add(pf_password);
	//CB
	center_panel.add(cb_depart_list);
	center_panel.add(cb_title_list);
	//Listeners				
	keylistener_id_num  kl_id_num = new keylistener_id_num ();
	tf_id_num.addKeyListener(kl_id_num);
	
	keylistener_name  kl_name= new keylistener_name();
	tf_name.addKeyListener(kl_name);
	
	actionlistener_add_database al_add_database  = new actionlistener_add_database();
	btn_add_database.addActionListener(al_add_database);
	
	actionlistener_new_user  al_new_user = new actionlistener_new_user();
	btn_new_user.addActionListener(al_new_user);
	
	actionlistener_close_Window  close_window = new actionlistener_close_Window();
	btn_close_window.addActionListener(close_window);
				
	actionlistener_search al_search =new  actionlistener_search();
	tf_search.addActionListener(al_search);	
}
private class keylistener_id_num implements KeyListener 
{  
@Override
  public void keyTyped(KeyEvent e)
{
    // TODO Auto-generated method stub
	char c = e.getKeyChar();
	if (!((c >= '0') && (c <= '9'))) 
	{
	e.consume();     	        		
	}    
	if(tf_id_num.getText().length()>=11)
    {
    e.consume();      
    }    
}
@Override
 public void keyPressed(KeyEvent e) 
{
 // TODO Auto-generated method stub
}
@Override
public void keyReleased(KeyEvent e)
{
 // TODO Auto-generated method stub	  
}   
	   
}
private class keylistener_name implements KeyListener
{
	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		if (((c >= '0') && (c <= '9'))) 
		{	       	
			e.consume();	
		}    
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub  	   
	}   

}   
public void clear()
{
	tf_id_num.setText("");
	tf_name.setText("");
	pf_password.setText("");
	cb_depart_list.setSelectedIndex(0);
	cb_title_list.setSelectedIndex(0);
	btn_group.clearSelection();
	tf_search.setText("");
}

private class actionlistener_add_database implements ActionListener 
{
	private ResultSet selectResult;
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		connectDB();
		String Unit = "";
		if (btn_female.isSelected()) Unit = "female";
		if (btn_male.isSelected()) Unit = "male";
		if (btn_other.isSelected()) Unit = "other";
		if(tf_id_num.getText().length()<=10)
		{
		System.out.println("please enter 11 digit number");
		}
	 		if(
	 			tf_id_num.getText().length() < 11  			|| 
	 			tf_name.getText().length() == 0 		|| 
	 			pf_password.getPassword().toString().length()==0 ||
	 			btn_group .getSelection() == null 		||
	 			cb_depart_list.getSelectedItem()== "" 	||
	 			cb_title_list.getSelectedItem()== "" )  
	 		{ 	  	  			
	 			JOptionPane.showMessageDialog(null,"please fill in the information ");	
	 		}
	 		else 
	 		{
	 			try
	 			{
	 				String pw=new String(pf_password.getPassword());
	 				//Search vehicle number in table
	 				search.setString(1, tf_ıd_num.getText()); 
	 				selectResult = search.executeQuery(); 
	 				//UPDATE row If there is a row returning from SELECT statement
	 				if (selectResult.next())
	 				{ //is there any row returning from SELECT
	 					//Prepare SQL Statement = Write the values for ?
	 					update.setString(1, tf_name.getText());
	 					update.setString(2, pw);
	 					update.setString(3, (String) cb_depart_list.getSelectedItem());
	 					update.setString(4,(String) cb_title_list.getSelectedItem());
	 					update.setString(5, Unit );
	 					update.setString(6, tf_id_num.getText());
	 					System.out.println("Data Update");
	 					//update the entry
	 					//Execution
	 					update.execute();
	 					conn.close();
	 					//Prepare SQL Statement = Write the values for ?
	 				} 
	 				else 
	 				{      
	 					//INSERT if no data returned from SELECT
	 					insert.setString(1, tf_id_num.getText());
	 					insert.setString(2, tf_name.getText());
	 					insert.setString(3, pw);
	 					insert.setString(4, (String) cb_depart_list.getSelectedItem());
	 					insert.setString(5, (String) cb_title_list.getSelectedItem());
	 					insert.setString(6, Unit);  
	 					System.out.println("Data insert");
	 					//insert the new entry
	 					//Execution
	 					insert.execute(); 
	 					conn.close(); 		        
	 				}
	 			} 
	 			catch (SQLException e1) 
	 			{
		        e1.printStackTrace();
		        System.exit(1);
	 			}
		       
	 		}	
	 		clear();			
	} 
}
private class actionlistener_new_user implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event)
	{		
		clear();		
	} 
}
private class actionlistener_close_Window implements ActionListener
	{
		@Override
	public void actionPerformed(ActionEvent event) 
	{
			int n = JOptionPane.showConfirmDialog(  
					null,
					"Save File and Close Window!" ,
					"",
					JOptionPane.YES_NO_OPTION);

			if(n == JOptionPane.YES_OPTION)
			{
				JOptionPane.showMessageDialog(null, "Goodbye");
				System.exit(0);	    
			}		
	} 
}

private class actionlistener_search implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event)
	{
      JOptionPane.showMessageDialog(null, tf_search.getText());
    }
	}
}


