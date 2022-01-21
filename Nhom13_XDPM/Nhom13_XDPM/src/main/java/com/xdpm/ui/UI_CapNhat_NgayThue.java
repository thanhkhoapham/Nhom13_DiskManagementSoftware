package com.xdpm.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.xdpm.dao.CategoryDAO;
import com.xdpm.entity.Category;

public class UI_CapNhat_NgayThue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6651440241729494153L;
	private JPanel contentPane;
	private DefaultComboBoxModel<Category> model;
	@SuppressWarnings("rawtypes")
	private JComboBox cboLoaiTieuDe;
	private CategoryDAO CategoryDAO;
	private JTextField txtNgayThueToiDa;
	private List<Category> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI_CapNhat_NgayThue frame = new UI_CapNhat_NgayThue();
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
	public UI_CapNhat_NgayThue() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		JLabel lbltGiThu = new JLabel("ĐẶT NGÀY THUÊ TỐI ĐA");
		lbltGiThu.setHorizontalAlignment(SwingConstants.CENTER);
		lbltGiThu.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbltGiThu.setBounds(84, 46, 292, 29);
		getContentPane().add(lbltGiThu);

		JLabel lblLoiTiu = new JLabel("Loại tiêu đề");
		lblLoiTiu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLoiTiu.setBounds(27, 112, 112, 16);
		getContentPane().add(lblLoiTiu);

		model = new DefaultComboBoxModel<Category>();
		cboLoaiTieuDe = new JComboBox<Category>(model);
		cboLoaiTieuDe.setBounds(179, 111, 167, 25);
		getContentPane().add(cboLoaiTieuDe);

		JLabel lblGiThu = new JLabel("Số ngày tối đa");
		lblGiThu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGiThu.setBounds(28, 156, 127, 25);
		getContentPane().add(lblGiThu);

		txtNgayThueToiDa = new JTextField();
		txtNgayThueToiDa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNgayThueToiDa.setColumns(10);
		txtNgayThueToiDa.setBackground(Color.WHITE);
		txtNgayThueToiDa.setBounds(179, 152, 167, 25);
		getContentPane().add(txtNgayThueToiDa);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBounds(207, 206, 100, 29);
		getContentPane().add(btnCapNhat);

		/**
		 * Xu ly doi tuong
		 */
		CategoryDAO = new CategoryDAO();
		list = CategoryDAO.getAllCategory();
		loadDataComboBox();
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("image/ok.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		/**
		 * Bat sự kiện
		 */
		cboLoaiTieuDe.addActionListener(e -> {
			Category category = CategoryDAO.getCategoryByName(cboLoaiTieuDe.getSelectedItem().toString());
			txtNgayThueToiDa.setText(String.valueOf(category.getRentalPeriod()));
		});
		btnCapNhat.addActionListener(e -> {
			if (txtNgayThueToiDa.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng không để trống", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (!(txtNgayThueToiDa.getText().length() > 0 && txtNgayThueToiDa.getText().matches("[0-9]*"))) {
				JOptionPane.showMessageDialog(this, "Không nhập chữ hoặc giá trị âm cho ngày thuê", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (Integer.parseInt(txtNgayThueToiDa.getText()) < 0) {
				JOptionPane.showMessageDialog(this, "Không nhập giá trị âm", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			String name = cboLoaiTieuDe.getSelectedItem().toString();
			int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc thay đổi giá hay không", "Xác nhận",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				Category category = new Category(getIdByName(name), name, Integer.parseInt(txtNgayThueToiDa.getText()));
				CategoryDAO.update(category);
				JOptionPane.showMessageDialog(this, "OK", "Thông báo", JOptionPane.INFORMATION_MESSAGE, imageIcon);
			} else
				return;
		});
	}

	private int getIdByName(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equalsIgnoreCase(name))
				return list.get(i).getId();
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	private void loadDataComboBox() {
		for (int i = 0; i < list.size(); i++) {
			this.cboLoaiTieuDe.addItem(list.get(i).getName());
			if (i == 0) {
				txtNgayThueToiDa.setText(String.valueOf(list.get(i).getRentalPeriod()));
			}
		}

	}

}
