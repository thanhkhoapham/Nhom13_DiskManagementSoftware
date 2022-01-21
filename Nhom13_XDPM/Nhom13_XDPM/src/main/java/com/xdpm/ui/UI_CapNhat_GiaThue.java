package com.xdpm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
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

public class UI_CapNhat_GiaThue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6651440241729494153L;
	private JPanel contentPane;
	private DefaultComboBoxModel<Category> model;
	@SuppressWarnings("rawtypes")
	private JComboBox cboLoaiTieuDe;
	private CategoryDAO CategoryDAO;
	private JTextField txtPhiTraTre;
	private JTextField txtGiaThue;
	private List<Category> list = new ArrayList<Category>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI_CapNhat_GiaThue frame = new UI_CapNhat_GiaThue();
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
	public UI_CapNhat_GiaThue() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		CategoryDAO = new CategoryDAO();

		setTitle("Đặt giá thuê");

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		setSize(450, 370);
		getContentPane().setLayout(null);

		JLabel lbltGiThu = new JLabel("ĐẶT GIÁ THUÊ");
		lbltGiThu.setHorizontalAlignment(SwingConstants.CENTER);
		lbltGiThu.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbltGiThu.setBounds(0, 30, 440, 36);
		getContentPane().add(lbltGiThu);

		JLabel lblLoiTiu = new JLabel("Thể loại");
		lblLoiTiu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLoiTiu.setBounds(27, 96, 105, 30);
		getContentPane().add(lblLoiTiu);

		model = new DefaultComboBoxModel<Category>();
		cboLoaiTieuDe = new JComboBox<Category>(model);
		cboLoaiTieuDe.setBounds(139, 96, 245, 30);

		getContentPane().add(cboLoaiTieuDe);

		JLabel lblGiThu = new JLabel("Giá thuê");
		lblGiThu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiThu.setBounds(27, 156, 105, 30);
		getContentPane().add(lblGiThu);

		JLabel lblLoiTiu_1_1 = new JLabel("Phí trả trễ");
		lblLoiTiu_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLoiTiu_1_1.setBounds(27, 216, 105, 30);
		getContentPane().add(lblLoiTiu_1_1);

		txtGiaThue = new JTextField();
		txtGiaThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtGiaThue.setColumns(10);
		txtGiaThue.setBackground(Color.WHITE);
		txtGiaThue.setBounds(140, 156, 244, 30);
		getContentPane().add(txtGiaThue);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBounds(140, 276, 98, 26);
		getContentPane().add(btnCapNhat);

		txtPhiTraTre = new JTextField();
		txtPhiTraTre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPhiTraTre.setColumns(10);
		txtPhiTraTre.setBackground(Color.WHITE);
		txtPhiTraTre.setBounds(139, 216, 244, 30);
		getContentPane().add(txtPhiTraTre);

		/**
		 * Xu ly
		 */
		list = CategoryDAO.getAllCategory();
		loadComboBox();
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("image/ok.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

		/**
		 * Bắt sự kiện
		 */
		cboLoaiTieuDe.addActionListener(e -> {
			Category category = CategoryDAO.getCategoryByName(cboLoaiTieuDe.getSelectedItem().toString());
			txtGiaThue.setText(String.format("%.0f", category.getRentalCharge()));
			txtPhiTraTre.setText(String.format("%.0f", category.getLateFee()));

		});
		btnCapNhat.addActionListener(e -> {

			if (txtGiaThue.getText().equals("") || txtPhiTraTre.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng không để trống", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (!(txtGiaThue.getText().length() > 0 && txtGiaThue.getText().matches("[0-9]*"))) {
				JOptionPane.showMessageDialog(this, "Không nhập chữ hoặc giá trị âm cho giá thuê", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (!(txtPhiTraTre.getText().length() > 0 && txtPhiTraTre.getText().matches("[0-9]*"))) {
				JOptionPane.showMessageDialog(this, "Không nhập chữ hoặc giá trị âm cho phí trễ ", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (Integer.parseInt(txtGiaThue.getText()) < 0 || Integer.parseInt(txtPhiTraTre.getText()) < 0) {
				JOptionPane.showMessageDialog(this, "Không nhập giá trị âm", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String name = cboLoaiTieuDe.getSelectedItem().toString();
			int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc thay đổi giá hay không", "Xác nhận",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				Category category = new Category(getIdByName(name), name, Double.parseDouble(txtGiaThue.getText()),
						Double.parseDouble(txtPhiTraTre.getText()));
				CategoryDAO.update(category);
				JOptionPane.showMessageDialog(this, "OK", "Thông báo", JOptionPane.INFORMATION_MESSAGE, imageIcon);
			} else
				return;
		});

	}

	@SuppressWarnings("unchecked")
	private void loadComboBox() {
		for (int i = 0; i < list.size(); i++) {
			this.cboLoaiTieuDe.addItem(list.get(i).getName());
			if (i == 0) {
				txtGiaThue.setText(String.format("%.0f", list.get(i).getRentalCharge()));
				txtPhiTraTre.setText(String.format("%.0f", list.get(i).getLateFee()));
			}
		}
	}

	public int getIdByName(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equalsIgnoreCase(name))
				return list.get(i).getId();
		}
		return -1;
	}

}
