package com.xdpm.ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class UI_Login extends JDialog{
	private JPasswordField txtMatKhau;
	private UI_Main mainUI;

	public UI_Login(final UI_Main mainUI, boolean modal) {
		super(mainUI, modal);
		setTitle("Đăng nhập quản lý");
		setSize(350, 80);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(null);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu: ");
		lblMatKhau.setBounds(10, 10, 85, 24);
		getContentPane().add(lblMatKhau);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(75, 10, 144, 24);
		txtMatKhau.setText("123456");
		getContentPane().add(txtMatKhau);
		
		JButton btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setBounds(230, 10, 100, 24);
		getContentPane().add(btnDangNhap);
		
		btnDangNhap.addActionListener(e ->{
			char[] pw = txtMatKhau.getPassword();
			String password = new String(pw);
			if (password.trim().equals("123456")) {
				mainUI.changeUI(true);
				this.dispose();
			}else {
				txtMatKhau.requestFocus();
				txtMatKhau.selectAll();
				JOptionPane.showMessageDialog(null, "Sai mật khẩu!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	
}
