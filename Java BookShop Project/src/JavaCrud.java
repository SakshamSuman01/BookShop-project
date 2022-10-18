import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaCrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtename;
	private JTextField txtpname;
	private JTable table;
	private JTextField idname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	
	public JavaCrud() {
		initialize();
		connect();
		table_load();
	}
	private void table_load() {
		// TODO Auto-generated method stub

		try
		{
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void connect()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bookshop", "root", "7808");
		}
		catch(ClassNotFoundException ex) {
			
		}
		catch (SQLException ex) 
		{
			
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 982, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setForeground(new Color(128, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(400, 25, 171, 47);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(32, 119, 394, 231);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(21, 42, 97, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(21, 101, 97, 25);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(21, 156, 97, 25);
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(128, 44, 198, 25);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtename = new JTextField();
		txtename.setColumns(10);
		txtename.setBounds(128, 103, 198, 25);
		panel.add(txtename);
		
		txtpname = new JTextField();
		txtpname.setColumns(10);
		txtpname.setBounds(128, 160, 198, 25);
		panel.add(txtpname);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setForeground(new Color(219, 26, 84));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
				
				
			}
		});
		btnNewButton.setBounds(178, 361, 89, 47);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price;
				bname = txtbname.getText();
				edition = txtename.getText();
				price = txtpname.getText();
				
				try 
				{
					pst = con.prepareStatement("insert into book(name,edition,price) values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added...");
					//here JoptionPane is the class which is used to display different types of dialog boxes.
					table_load();
					txtbname.setText("");
					txtename.setText("");
					txtpname.setText("");
					txtbname.requestFocus();//requestFocus() makes a request that the given Component gets set to a focused state.
					//also the  mouse cursor should be again focussed on book name after record adding thats why requestFocus.
					
					
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
			}
			
//			public void table_load()
//			{
//				try
//				{
//					pst = con.prepareStatement("select * from book");
//					rs = pst.executeQuery();
//					table.setModel(Db.utils.resultSetToTableModel(rs));
//				}
//				catch(SQLException e)
//				{
//					e.printStackTrace();
//				}
//			}
		});
		btnNewButton_1.setForeground(new Color(0, 166, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(32, 361, 89, 47);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbname.setText("");
				txtename.setText("");
				txtpname.setText("");
				txtbname.requestFocus();
			}
		});
		btnClear.setForeground(new Color(29, 197, 205));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(337, 361, 89, 47);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(450, 119, 462, 281);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("Saksham's");
		lblNewLabel_2.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_2.setForeground(new Color(176, 53, 202));
		lblNewLabel_2.setBounds(247, 37, 143, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(32, 427, 381, 67);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setBounds(62, 22, 64, 19);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lblNewLabel_1_1_2);
		
		idname = new JTextField();
		idname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try 
				{
					String id = idname.getText();
					pst = con.prepareStatement("select name,edition,price from book where id = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();	
					if(rs.next()==true)
					{
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						txtbname.setText(name);
						txtename.setText(edition);
						txtpname.setText(price);
					}
					else 
					{
						txtbname.setText("");
						txtename.setText("");
						txtpname.setText("");	
					}
				}
				catch(SQLException se)
				{
					
				}
				
				
				
				
				
				
			}
		});
		idname.setBounds(136, 22, 162, 21);
		idname.setColumns(10);
		panel_1.add(idname);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price,bid;
				bname = txtbname.getText();
				edition = txtename.getText();
				price = txtpname.getText();
				bid = idname.getText();
				
				try 
				{
					pst = con.prepareStatement("update book set name = ?, edition = ?, price = ? where id = ?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated!");
					//here JoptionPane is the class which is used to display different types of dialog boxes.
					table_load();
					txtbname.setText("");
					txtename.setText("");
					txtpname.setText("");
					txtbname.requestFocus();//requestFocus() makes a request that the given Component gets set to a focused state.
					//also the  mouse cursor should be again focussed on book name after record adding thats why requestFocus.
					
					
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.setBounds(557, 427, 89, 47);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Delete");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bid;
				
				bid = idname.getText();
				
				try 
				{
					pst = con.prepareStatement("delete from book where id = ?");
					
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted!");
					//here JoptionPane is the class which is used to display different types of dialog boxes.
					table_load();
					txtbname.setText("");
					txtename.setText("");
					txtpname.setText("");
					txtbname.requestFocus();//requestFocus() makes a request that the given Component gets set to a focused state.
					//also the  mouse cursor should be again focussed on book name after record adding thats why requestFocus.
					
					
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2_1.setBounds(698, 427, 89, 47);
		frame.getContentPane().add(btnNewButton_2_1);
	}
}
