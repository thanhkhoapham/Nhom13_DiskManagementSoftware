package com.xdpm.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import com.xdpm.dao.CategoryDAO;
import com.xdpm.dao.CustomerDAO;
import com.xdpm.dao.TitleDAO;
import com.xdpm.entity.Category;
import com.xdpm.entity.Customer;
import com.xdpm.entity.Title;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;

public class UI_TimKiemTuaDe extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtSoBan;
	private JTextField txtTen;
	private JTextField txtTheLoai;
	private JButton btnTim;
	private TitleDAO titleDAO;
	private CategoryDAO categoryDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI_TimKiemTuaDe frame = new UI_TimKiemTuaDe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI_TimKiemTuaDe() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("TÌM KIẾM TỰA ĐĨA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(56, 13, 330, 39);
		panel.add(lblNewLabel);

		txtId = new JTextField();
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtId.setBounds(122, 62, 133, 22);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(83, 59, 39, 26);
		panel.add(lblNewLabel_1);

		btnTim = new JButton("Tìm ");
		btnTim.setBounds(259, 59, 96, 26);
		panel.add(btnTim);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin t\u1EF1a \u0111\u0129a", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBounds(25, 95, 361, 142);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("Tên:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(10, 31, 64, 26);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Thể loại");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(10, 65, 64, 26);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Số bản cho thuê");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(10, 106, 106, 26);
		panel_1.add(lblNewLabel_1_3);

		txtSoBan = new JTextField();
		txtSoBan.setEditable(false);
		txtSoBan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSoBan.setColumns(10);
		txtSoBan.setBounds(126, 106, 215, 22);
		panel_1.add(txtSoBan);

		txtTen = new JTextField();
		txtTen.setEditable(false);
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTen.setColumns(10);
		txtTen.setBounds(126, 31, 215, 22);
		panel_1.add(txtTen);

		txtTheLoai = new JTextField();
		txtTheLoai.setEditable(false);
		txtTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTheLoai.setColumns(10);
		txtTheLoai.setBounds(126, 65, 215, 22);
		panel_1.add(txtTheLoai);
		titleDAO = new TitleDAO();
		categoryDao = new CategoryDAO();

		btnTim.addActionListener(e -> {
			clear();
			if (txtId.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mã tựa đĩa", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				Title cu = titleDAO.getTitleByID(Integer.parseInt(txtId.getText()));
				if (cu != null) {
					Category category = categoryDao.getCategoryByID(cu.getCategory().getId());
					txtTen.setText(cu.getName());
					txtTheLoai.setText(category.getName());
					txtSoBan.setText(String.valueOf(cu.getNumberOfCopies()));
				} else
					JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng mã : " + txtId.getText(),
							"Thông báo", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private void clear() {
		txtTen.setText("");
		txtTheLoai.setText("");
		txtSoBan.setText("");

	}
}
