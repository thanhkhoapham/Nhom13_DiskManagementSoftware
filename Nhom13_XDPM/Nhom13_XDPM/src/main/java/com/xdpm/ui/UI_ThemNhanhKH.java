package com.xdpm.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.xdpm.dao.CustomerDAO;
import com.xdpm.entity.Customer;

import java.awt.Font;
import javax.swing.JButton;

public class UI_ThemNhanhKH extends JDialog{
	private UI_Main ui_Main;
	private JTextField tfCusName;
	private JTextField tfAddress;
	private JTextField tfPhoneNumber;
	private CustomerDAO customerDAO = new CustomerDAO();
	
	public UI_ThemNhanhKH() {
		super();
		setTitle("Thêm khách hàng");
		setSize(450, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setModal(rootPaneCheckingEnabled);
		
		JLabel lblCusName = new JLabel("Tên khách hàng");
		lblCusName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCusName.setBounds(20, 65, 115, 25);
		getContentPane().add(lblCusName);
		
		JLabel lblAddress = new JLabel("Địa chỉ");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(20, 145, 115, 25);
		getContentPane().add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Số điện thoại");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhoneNumber.setBounds(20, 225, 115, 25);
		getContentPane().add(lblPhoneNumber);
		
		tfCusName = new JTextField();
		tfCusName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCusName.setBounds(150, 65, 255, 25);
		getContentPane().add(tfCusName);
		tfCusName.setColumns(10);
		
		tfAddress = new JTextField();
		tfAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfAddress.setBounds(150, 145, 255, 25);
		getContentPane().add(tfAddress);
		tfAddress.setColumns(10);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfPhoneNumber.setBounds(150, 225, 255, 25);
		getContentPane().add(tfPhoneNumber);
		tfPhoneNumber.setColumns(10);
		
		JButton btnAddCustomer = new JButton("Thêm");
		btnAddCustomer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddCustomer.setBounds(141, 291, 137, 38);
		getContentPane().add(btnAddCustomer);
		
		btnAddCustomer.addActionListener(e ->{
			String cusName = tfCusName.getText();
			String address = tfAddress.getText();
			String phoneNumber = tfPhoneNumber.getText();
			
			Customer customer = customerDAO.add(new Customer(cusName, address, phoneNumber, true));
			JOptionPane.showMessageDialog(null, "Thêm thành công!!\n Mã khách hàng là: "+customer.getId(), "", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		});
	}
}
