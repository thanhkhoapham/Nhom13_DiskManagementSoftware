package com.xdpm.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.xdpm.dao.CustomerDAO;
import com.xdpm.dao.DiskDAO;
import com.xdpm.dao.RentalDAO;
import com.xdpm.entity.Customer;
import com.xdpm.entity.Disk;
import com.xdpm.entity.RentalRecord;
import com.xdpm.util.FormatString;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;

public class UI_TTTreHan extends JPanel{
	private JTextField tfCusID;
	private JTextField tfCusName;
	private JTextField tfAddress;
	private JTextField tfPhoneNumber;
	private DefaultTableModel modelDisk;
	private JTable tblDisk;
	private JTextField tfTienNoPhi;
	private boolean flag = true;
	
	private DiskDAO diskDAO = new DiskDAO();
	private CustomerDAO customerDAO = new CustomerDAO();
	private RentalDAO rentalDAO = new RentalDAO();
	private JTextField tfTongTien;
	private JButton btnThanhToan;
	private JButton btnHuy;
	private JButton btnThanhToanAll;
	public UI_TTTreHan() {
		setLayout(null);
		
		JPanel pnlKhachHang = new JPanel();
		pnlKhachHang.setBorder(new TitledBorder(null, "Khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlKhachHang.setBounds(712, 54, 344, 417);
		add(pnlKhachHang);
		pnlKhachHang.setLayout(null);
		
		JLabel lblID = new JLabel("Mã khách hàng");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblID.setBounds(10, 25, 100, 25);
		pnlKhachHang.add(lblID);
		
		tfCusID = new JTextField();
		tfCusID.setBounds(121, 25, 119, 25);
		pnlKhachHang.add(tfCusID);
		tfCusID.setColumns(10);
		
		JButton btnFindCustomer = new JButton("Tìm");
		btnFindCustomer.setBounds(250, 26, 84, 25);
		pnlKhachHang.add(btnFindCustomer);
		
		JLabel lblCusName = new JLabel("Tên khách hàng");
		lblCusName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCusName.setBounds(10, 70, 100, 25);
		pnlKhachHang.add(lblCusName);
		
		tfCusName = new JTextField();
		tfCusName.setBackground(Color.WHITE);
		tfCusName.setEditable(false);
		tfCusName.setBounds(121, 70, 213, 25);
		pnlKhachHang.add(tfCusName);
		tfCusName.setColumns(40);
		
		JLabel lblAddress = new JLabel("Địa chỉ");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(10, 115, 100, 25);
		pnlKhachHang.add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setEditable(false);
		tfAddress.setBackground(Color.WHITE);
		tfAddress.setBounds(121, 115, 213, 25);
		pnlKhachHang.add(tfAddress);
		tfAddress.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Số điện thoại");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhoneNumber.setBounds(10, 160, 84, 25);
		pnlKhachHang.add(lblPhoneNumber);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setEditable(false);
		tfPhoneNumber.setBackground(Color.WHITE);
		tfPhoneNumber.setText("");
		tfPhoneNumber.setBounds(123, 160, 211, 25);
		pnlKhachHang.add(tfPhoneNumber);
		tfPhoneNumber.setColumns(10);
		
		JLabel lblTienNoPhi = new JLabel("Tổng nợ phí");
		lblTienNoPhi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTienNoPhi.setBounds(10, 205, 70, 25);
		pnlKhachHang.add(lblTienNoPhi);
		
		tfTienNoPhi = new JTextField();
		tfTienNoPhi.setEditable(false);
		tfTienNoPhi.setForeground(Color.RED);
		tfTienNoPhi.setBackground(Color.WHITE);
		tfTienNoPhi.setBounds(121, 205, 213, 25);
		pnlKhachHang.add(tfTienNoPhi);
		tfTienNoPhi.setColumns(10);
		
		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setBounds(40, 365, 100, 30);
		pnlKhachHang.add(btnThanhToan);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setBounds(200, 365, 100, 30);
		pnlKhachHang.add(btnHuy);
		
		JLabel lblThanhToan = new JLabel("Thông tin thanh toán");
		lblThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblThanhToan.setBounds(10, 285, 200, 25);
		pnlKhachHang.add(lblThanhToan);
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTongTien.setBounds(10, 320, 80, 25);
		pnlKhachHang.add(lblTongTien);
		
		tfTongTien = new JTextField();
		tfTongTien.setEditable(false);
		tfTongTien.setBackground(Color.WHITE);
		tfTongTien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfTongTien.setBounds(121, 320, 213, 25);
		pnlKhachHang.add(tfTongTien);
		tfTongTien.setColumns(10);
		tfTongTien.setText("0 đ");
		
		btnThanhToanAll = new JButton("Thanh toán toàn bộ");
		btnThanhToanAll.setBounds(121, 250, 150, 25);
		pnlKhachHang.add(btnThanhToanAll);
		btnThanhToanAll.setEnabled(false);
		
		JPanel pnlDiaTreHan = new JPanel();
		pnlDiaTreHan.setBorder(new TitledBorder(null, "Thông tin phí trễ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDiaTreHan.setBounds(10, 54, 698, 417);
		add(pnlDiaTreHan);
		pnlDiaTreHan.setLayout(null);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 20, 678, 387);
		pnlDiaTreHan.add(scrollPane2);

		String[] title2 = { "STT", "Mã đĩa", "Tựa đề", "Hạn trả", "Ngày trả", "Phí trễ"};
		modelDisk = new DefaultTableModel(title2, 0);
		tblDisk = new JTable(modelDisk) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tblDisk.getTableHeader().setReorderingAllowed(false);
		tblDisk.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblDisk.setBounds(10, 45, 678, 247);
		scrollPane2.setViewportView(tblDisk);
		
		tableDesign(tblDisk);
		tableRenderer();
		setTBColumnWidth();
		
		btnFindCustomer.addActionListener(e ->{
			try {
				clearTable();
				int cusID = Integer.parseInt(tfCusID.getText());
				if (cusID < 0) {
					JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}else {
					Customer customer = customerDAO.getCustomerByID(cusID);
					if (customer != null) {
						tfCusName.setText(customer.getName());
						tfAddress.setText(customer.getAddress());
						tfPhoneNumber.setText(customer.getPhoneNumber());
						
						loadTableLateFee(cusID);
						btnThanhToanAll.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng này!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (Exception e2) {
				if (tfCusID.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Chưa nhập mã khách hàng!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnHuy.addActionListener(e ->{
			if (JOptionPane.showConfirmDialog(this, "Hủy giao dịch này?", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				clearAll();
			}
		});
		
		btnThanhToan.addActionListener(e ->{
			try {
				if (validData()) {
					int rowCount = tblDisk.getRowCount();
					int[] sRow = tblDisk.getSelectedRows();
					List<Integer> selectedRow = convertArrayToList(sRow);
					
					String str_cusID = tfCusID.getText().toString();
					int cusID = Integer.parseInt(str_cusID);
					Customer customer = customerDAO.getCustomerByID(cusID);
					
					for (int i = 0; i < rowCount; i++) {
						if (selectedRow.contains(i)) {
							int diskID = Integer.parseInt(tblDisk.getValueAt(i, 1).toString());
							Disk disk = diskDAO.getDiskByID(diskID);
							Date returnDate = convertDate(tblDisk.getValueAt(i, 4)+"");
							RentalRecord record = rentalDAO.getByDiskIDAndCusID(diskID, cusID, returnDate);
							record.setPaid(true);
							rentalDAO.update(record);
						}
					}
					JOptionPane.showMessageDialog(null, "Thanh toán thành công!!", "", JOptionPane.INFORMATION_MESSAGE);
					clearAll();
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnThanhToanAll.addActionListener(e ->{
			if (tfTienNoPhi.getText().equals("0 đ")) {
				JOptionPane.showMessageDialog(null, "Khách hàng này không có phí trễ!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}else {
				if (flag) {
					double total = getDoubleValue(tfTienNoPhi);
					tfTongTien.setText(FormatString.formatTienVN(total)+ " đ");
					flag = false;
					btnThanhToanAll.setText("Thanh toán từng đĩa");
					tblDisk.setRowSelectionInterval(0, modelDisk.getRowCount()-1);
				}else {
					flag = true;
					btnThanhToanAll.setText("Thanh toán toàn bộ");
					tblDisk.setRowSelectionInterval(0, 0);
					tfTongTien.setText(FormatString.formatTienVN(totalSelectedLateFee(tblDisk))+ " đ");
				}
			}
		});
		
		tblDisk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfTongTien.setText(FormatString.formatTienVN(totalSelectedLateFee(tblDisk))+ " đ");
			}
		});
	}
	
	private void clearTable() {
		while(modelDisk.getRowCount()>0) {
			modelDisk.removeRow(0);
		}
	}
	
	private void clearAll() {
		clearTable();
		tfCusID.setText("");
		tfAddress.setText("");
		tfCusName.setText("");
		tfPhoneNumber.setText("");
		tfTienNoPhi.setText("");
		tfTongTien.setText("0 đ");
		flag = true;
		btnThanhToanAll.setEnabled(false);
	}
	
	private List<Integer> convertArrayToList(int[] array){
		List<Integer> list = new ArrayList<Integer>();
		for (int i : array) {
			list.add(i);
		}
		return list;
	}

	//Tính phí trễ của các record đang chọn
	private double totalSelectedLateFee(JTable table) {
		int rowCount = table.getRowCount();
		double total = 0;
		int[] sRow = tblDisk.getSelectedRows();
		List<Integer> selectedRow = convertArrayToList(sRow);
		
		for (int i = 0; i < rowCount; i++) {
			if (selectedRow.contains(i)) {
				total += Double.parseDouble(table.getValueAt(i, 5)+"");
			}
	    }
		return total;
	}
	
	//Lấy giá trị tiền từ text field
	private double getDoubleValue(JTextField textField) {
		String string = textField.getText().toString();
		String[] parts = string.split(" ");
		String str_value = parts[0];
		return Double.parseDouble(str_value.replace(",", ""));
	}
	
	private Date convertDate(String str) throws ParseException {
		 SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
		 return formatter.parse(str);  
	}
	
	private void loadTableLateFee(int cusID) {
		List<RentalRecord> record = rentalDAO.getListUnpaidRecord(cusID);
		double lateFee = 0;
		for (RentalRecord rentalRecord : record) {
			int i = modelDisk.getRowCount();
			String[] rowData = {i+1+"", rentalRecord.getDisk().getId()+"", rentalRecord.getDisk().getTitle().getName(),
					FormatString.formatDate(rentalRecord.getDueDate()),
					FormatString.formatDate(rentalRecord.getReturnDate()), rentalRecord.getLateFee()+""};
			lateFee += rentalRecord.getLateFee();
			modelDisk.addRow(rowData);
		}
		tblDisk.setModel(modelDisk);
		tfTienNoPhi.setText(FormatString.formatTienVN(lateFee)+" đ");
	}
	
	private boolean validData() {
		if (tfCusID.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập mã khách hàng!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}else if (tfTongTien.getText().equals("0 đ")) {
			JOptionPane.showMessageDialog(null, "Chưa có thông tin thanh toán!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
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
		
		tblDisk.getColumn("STT").setCellRenderer(centerCellRenderer);
		tblDisk.getColumn("Mã đĩa").setCellRenderer(centerCellRenderer);
		tblDisk.getColumn("Hạn trả").setCellRenderer(centerCellRenderer);
		tblDisk.getColumn("Ngày trả").setCellRenderer(centerCellRenderer);
		tblDisk.getColumn("Phí trễ").setCellRenderer(rightCellRenderer);
	}
	
	private void setTBColumnWidth() {	
		TableColumn column = null;
		for (int i = 0; i < tblDisk.getColumnCount(); i++) {
			column = tblDisk.getColumnModel().getColumn(i);
			if(i==2) {
				column.setPreferredWidth(200);
			}
		}
	}
}
