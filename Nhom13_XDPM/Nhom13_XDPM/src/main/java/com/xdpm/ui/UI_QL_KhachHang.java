package com.xdpm.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.xdpm.dao.CustomerDAO;
import com.xdpm.entity.Customer;
import javax.swing.border.TitledBorder;

public class UI_QL_KhachHang extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4440702554365484612L;
	private JTable table;
	private List<Customer> list = new ArrayList<Customer>();
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnTimKiem;
	private CustomerDAO customerDAO = new CustomerDAO();
	private DefaultTableModel dfTable;
	private JButton btnThem;
	private JTextField txtId;
	private JTextField txtHoTen;
	private JTextField txtDiaChi;
	private JTextField txtSdt;

	/**
	 * Create the panel.
	 */
	public UI_QL_KhachHang() {
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1066, 500);
		add(panel);
		panel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 62, 803, 416);
		panel.add(scrollPane);
		/**
		 * Set table
		 */
		table = new JTable() {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		dfTable = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã KH", "Họ tên khách hàng", "Địa chỉ", "Số điện thoại" });
		table.setModel(dfTable);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnThem.setBounds(846, 402, 210, 31);
		panel.add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXoa.setBounds(964, 361, 92, 31);
		panel.add(btnXoa);
		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSua.setBounds(846, 361, 92, 31);
		panel.add(btnSua);
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTimKiem.setBounds(20, 21, 92, 31);
		panel.add(btnTimKiem);

		tableDesign(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(846, 62, 210, 289);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã khách hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 37, 190, 25);
		panel_1.add(lblNewLabel);

		txtId = new JTextField();
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtId.setColumns(10);
		txtId.setBounds(10, 68, 94, 22);
		panel_1.add(txtId);

		JLabel lblHTn = new JLabel("Họ tên:");
		lblHTn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHTn.setBounds(10, 100, 190, 25);
		panel_1.add(lblHTn);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(10, 131, 190, 22);
		panel_1.add(txtHoTen);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDiaChi.setBounds(10, 163, 190, 25);
		panel_1.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(10, 194, 190, 22);
		panel_1.add(txtDiaChi);

		JLabel lblSinThoi = new JLabel("Số điện thoại:");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSinThoi.setBounds(10, 226, 190, 25);
		panel_1.add(lblSinThoi);

		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSdt.setColumns(10);
		txtSdt.setBounds(10, 257, 190, 22);
		panel_1.add(txtSdt);
		tableRenderer();
		setTBColumnWidth();

		/**
		 * Xu ly
		 */
		customerDAO = new CustomerDAO();
		list = customerDAO.getAllCustomer();
		loadListCustomer(list);

		openText(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				int index = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));
				txtId.setText((String) table.getValueAt(table.getSelectedRow(), 1));
				txtHoTen.setText((String) table.getValueAt(table.getSelectedRow(), 2));
				txtSdt.setText((String) table.getValueAt(table.getSelectedRow(), 4));
				txtDiaChi.setText((String) table.getValueAt(table.getSelectedRow(), 3));
			}
		});
//		btnThem.addActionListener(e -> {
//			clear();
//			openText(true);
//			openButton(false);
//		});
		btnThem.addActionListener(e -> {
			if (txtHoTen.getText().equals("") || txtSdt.getText().equals("") || txtDiaChi.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			String sdt = txtSdt.getText();
			if (!(sdt.length() > 0 && sdt.matches("0[0-9]{9}"))) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không đúng (gồm 10 con số và bắt đầu bằng 0)",
						"Thông báo", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Customer customer = new Customer(txtHoTen.getText(), txtDiaChi.getText(), txtSdt.getText());
			if (customerDAO.add(customer) != null) {
				JOptionPane.showMessageDialog(this, "Đã thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon("image/ok.png"));
//			if (customerDAO.addCustomer(customer)) {
//				JOptionPane.showMessageDialog(this, "Đã thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE,
//						new ImageIcon("image/ok.png"));
			} else
				JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
			clear();
			openText(true);
			openButton(true);
			list = customerDAO.getAllCustomer();
			loadListCustomer(list);
		});
		btnSua.addActionListener(e -> {
			if (txtHoTen.getText().equals("") || txtSdt.getText().equals("") || txtDiaChi.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			String sdt = txtSdt.getText();
			if (!(sdt.length() > 0 && sdt.matches("0[0-9]{9}"))) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không đúng (gồm 10 con số và bắt đầu bằng 0)",
						"Thông báo", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (txtId.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin khách hàng", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				int result = JOptionPane.showConfirmDialog(this,
						"Bạn có chắc muốn thay đổi khách hàng mã: '" + txtId.getText() + "' ", "Xác nhận",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					Customer customer = new Customer(Integer.parseInt(txtId.getText()), txtHoTen.getText(),
							txtDiaChi.getText(), txtSdt.getText());
					customer.setEnabled(true);
					customerDAO.update(customer);
					list = customerDAO.getAllCustomer();
					loadListCustomer(list);
					JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else
					return;
			}
		});
		btnXoa.addActionListener(e -> {
			if (txtId.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Mã khách hàng đang rỗng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!(txtId.getText().length() > 0 && txtId.getText().matches("[0-9]*"))) {
				JOptionPane.showMessageDialog(this, "Không nhập chữ", "Thông báo", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (customerDAO.checkCustomerRented(Integer.parseInt(txtId.getText())).size() > 0) {
				JOptionPane.showMessageDialog(this, "Khách hàng đang thuê không thể xóa", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (customerDAO.checkCustomerResertvation(Integer.parseInt(txtId.getText())).size() > 0) {
				JOptionPane.showMessageDialog(this, "Khách hàng đang thuê không thể xóa", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			String id = (String) table.getValueAt(table.getSelectedRow(), 1);
			int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa khách hàng mã: '" + id + "' ",
					"Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				customerDAO.deleteCustomer(Integer.parseInt(id));
				list = customerDAO.getAllCustomer();
				loadListCustomer(list);
				clear();
			} else
				return;

		});
		btnTimKiem.addActionListener(e -> {
			JFrame fram = new UI_TimKiemKhachHang();
			fram.setVisible(true);
		});
	}

	public void loadListCustomer(List<Customer> list) {
		dfTable.getDataVector().removeAllElements();
		int i = 1;
		for (Customer customer : list) {
			Vector<String> vt = new Vector<String>();
			vt.addElement(String.valueOf(i));
			vt.addElement(String.valueOf(customer.getId()));
			vt.addElement(String.valueOf(customer.getName()));
			vt.addElement(String.valueOf(customer.getAddress()));
			vt.addElement(String.valueOf(customer.getPhoneNumber()));
			i++;
			dfTable.addRow(vt);
		}
	}

	public void clear() {
		txtDiaChi.setText("");
		txtId.setText("");
		txtHoTen.setText("");
		txtSdt.setText("");
	}

	public void openText(boolean hope) {
		if (hope) {
			txtDiaChi.setEditable(true);
			txtHoTen.setEditable(true);
			txtSdt.setEditable(true);
		} else {
			txtDiaChi.setEditable(false);
			txtHoTen.setEditable(false);
			txtSdt.setEditable(false);
		}
	}

	public void openButton(boolean hope) {
		if (hope) {
			btnThem.setEnabled(true);
//			btnLuu.setEnabled(false);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
		} else {
			btnThem.setEnabled(false);
//			btnLuu.setEnabled(true);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		}

	}

	private void tableDesign(JTable tb) {
		tb.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		tb.getTableHeader().setOpaque(false);
		tb.getTableHeader().setBackground(new Color(32, 136, 203));
		tb.getTableHeader().setForeground(Color.white);
		tb.setRowHeight(25);
		tb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	}

	private void tableRenderer() {
		DefaultTableCellRenderer rightCellRenderer = new DefaultTableCellRenderer();
		DefaultTableCellRenderer centerCellRenderer = new DefaultTableCellRenderer();

		rightCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		centerCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

		table.getColumn("STT").setCellRenderer(centerCellRenderer);
		table.getColumn("Mã KH").setCellRenderer(centerCellRenderer);
	}

	private void setTBColumnWidth() {
		TableColumn column = null;
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 2) {
				column.setPreferredWidth(150);
			}
			if (i == 3) {
				column.setPreferredWidth(200);
			}
		}
	}
}
