package com.xdpm.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.xdpm.dao.CategoryDAO;
import com.xdpm.dao.DiskDAO;
import com.xdpm.dao.TitleDAO;
import com.xdpm.entity.Category;
import com.xdpm.entity.Disk;
import com.xdpm.entity.Title;

public class UI_QL_ChiTietTuaDe extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtTen;
	private JTextField txtSoBan;
	private JTextField txtRented;
	private DefaultTableModel dftable;
	private JTextField txtTheLoai;
	private JButton btnTim;
	private TitleDAO titleDAO;
	private CategoryDAO categoryDao;
	private DiskDAO diskDao;
	private List<Disk> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI_QL_ChiTietTuaDe frame = new UI_QL_ChiTietTuaDe();
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
	public UI_QL_ChiTietTuaDe() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("XEM CHI TIẾT TỰA ĐỀ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(85, 27, 267, 34);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(139, 85, 277, 146);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Tên:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(10, 60, 67, 23);
		panel_1.add(lblNewLabel_1_1_1_1);

		txtTen = new JTextField();
		txtTen.setEditable(false);
		txtTen.setColumns(10);
		txtTen.setBounds(60, 60, 168, 23);
		panel_1.add(txtTen);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Số bản");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1_2.setBounds(10, 98, 45, 23);
		panel_1.add(lblNewLabel_1_1_1_2);

		txtSoBan = new JTextField();
		txtSoBan.setEditable(false);
		txtSoBan.setColumns(10);
		txtSoBan.setBounds(60, 99, 45, 23);
		panel_1.add(txtSoBan);

		txtRented = new JTextField();
		txtRented.setEditable(false);
		txtRented.setColumns(10);
		txtRented.setBounds(183, 99, 45, 23);
		panel_1.add(txtRented);

		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Đang thuê:");
		lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1_2_1.setBounds(115, 99, 63, 23);
		panel_1.add(lblNewLabel_1_1_1_2_1);

		txtTheLoai = new JTextField();
		txtTheLoai.setEditable(false);
		txtTheLoai.setColumns(10);
		txtTheLoai.setBounds(60, 20, 168, 23);
		panel_1.add(txtTheLoai);

		JLabel lblNewLabel_1_1_1_3 = new JLabel("Thể loại:");
		lblNewLabel_1_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1_3.setBounds(10, 21, 45, 23);
		panel_1.add(lblNewLabel_1_1_1_3);

		btnTim = new JButton("Tìm");
		btnTim.setBounds(10, 138, 96, 21);
		panel.add(btnTim);

		txtId = new JTextField();
		txtId.setBounds(10, 105, 96, 23);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("ID:");
		lblNewLabel_1_1_1.setBounds(10, 79, 34, 23);
		panel.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));

		titleDAO = new TitleDAO();
		categoryDao = new CategoryDAO();
		diskDao = new DiskDAO();
		list = new ArrayList<Disk>();
		btnTim.addActionListener(e -> {
			if (txtId.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mã", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (!(txtId.getText().length() > 0 && txtId.getText().matches("[0-9]*"))) {
				JOptionPane.showMessageDialog(this, "Không nhập chữ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			Title cu = titleDAO.getTitleByID(Integer.parseInt(txtId.getText()));
			list = diskDao.getAllDiskRentedByTitleID(Integer.parseInt(txtId.getText()));
			if (cu != null) {
				Category category = categoryDao.getCategoryByID(cu.getCategory().getId());
				txtTen.setText(cu.getName());
				txtTheLoai.setText(category.getName());
				txtSoBan.setText(String.valueOf(cu.getNumberOfCopies()));
				txtRented.setText(String.valueOf(cu.getNumberOfCopies() - list.size()));
			} else
				JOptionPane.showMessageDialog(this, "Không tìm thấy mã : " + txtId.getText(), "Thông báo",
						JOptionPane.ERROR_MESSAGE);
//			if (Integer.parseInt(txtRented.getText()) < 0)
//				txtRented.setText("0");
		});
	}
}
