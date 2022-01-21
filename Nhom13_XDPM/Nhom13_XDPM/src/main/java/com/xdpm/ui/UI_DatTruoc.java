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
import com.xdpm.dao.ReservationDAO;
import com.xdpm.dao.TitleDAO;
import com.xdpm.entity.Customer;
import com.xdpm.entity.Disk;
import com.xdpm.entity.RentalRecord;
import com.xdpm.entity.ReservationRecord;
import com.xdpm.entity.Title;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class UI_DatTruoc extends JPanel{
	private JTextField tfCusID;
	private JTextField tfCusName;
	private JTextField tfAddress;
	private JTextField tfPhoneNumber;
	private DefaultTableModel modelDisk;
	private JTable tblDisk;
	
	private DiskDAO diskDAO = new DiskDAO();
	private CustomerDAO customerDAO = new CustomerDAO();
	private TitleDAO titleDAO = new TitleDAO();
	private ReservationDAO reservationDAO = new ReservationDAO();
	
	private JTextField tfDiskTitle;
	public UI_DatTruoc() {
		setLayout(null);
		
		JPanel pnlKhachHang = new JPanel();
		pnlKhachHang.setBorder(new TitledBorder(null, "Khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlKhachHang.setBounds(718, 108, 338, 273);
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
		btnFindCustomer.setBounds(250, 26, 76, 25);
		pnlKhachHang.add(btnFindCustomer);
		
		JLabel lblCusName = new JLabel("Tên khách hàng");
		lblCusName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCusName.setBounds(10, 70, 100, 25);
		pnlKhachHang.add(lblCusName);
		
		tfCusName = new JTextField();
		tfCusName.setBackground(Color.WHITE);
		tfCusName.setEditable(false);
		tfCusName.setBounds(121, 70, 205, 25);
		pnlKhachHang.add(tfCusName);
		tfCusName.setColumns(40);
		
		JLabel lblAddress = new JLabel("Địa chỉ");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(10, 115, 100, 25);
		pnlKhachHang.add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setEditable(false);
		tfAddress.setBackground(Color.WHITE);
		tfAddress.setBounds(121, 115, 205, 25);
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
		tfPhoneNumber.setBounds(121, 160, 205, 25);
		pnlKhachHang.add(tfPhoneNumber);
		tfPhoneNumber.setColumns(10);
		
		JButton btnDatTruoc = new JButton("Đặt trước");
		btnDatTruoc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDatTruoc.setBounds(60, 213, 100, 35);
		pnlKhachHang.add(btnDatTruoc);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHuy.setBounds(194, 213, 100, 35);
		pnlKhachHang.add(btnHuy);
		
		JPanel pnlDiaTreHan = new JPanel();
		pnlDiaTreHan.setBorder(new TitledBorder(null, "Tựa đĩa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDiaTreHan.setBounds(10, 54, 698, 417);
		add(pnlDiaTreHan);
		pnlDiaTreHan.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 678, 323);
		pnlDiaTreHan.add(scrollPane);

		String[] titleTable = { "STT", "Mã tựa", "Tựa đề", "Loại đĩa"};
		modelDisk = new DefaultTableModel(titleTable, 0);
		tblDisk = new JTable(modelDisk) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tblDisk.getTableHeader().setReorderingAllowed(false);
		tblDisk.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tblDisk.setBounds(10, 45, 678, 247);
		scrollPane.setViewportView(tblDisk);
		
		JButton btnAddDisk = new JButton("Thêm");
		btnAddDisk.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddDisk.setBounds(430, 40, 85, 25);
		pnlDiaTreHan.add(btnAddDisk);
		
		JLabel lblDiskTitle = new JLabel("Nhập tựa đĩa");
		lblDiskTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiskTitle.setBounds(150, 40, 100, 25);
		pnlDiaTreHan.add(lblDiskTitle);
		
		tfDiskTitle = new JTextField();
		tfDiskTitle.setBounds(240, 40, 180, 25);
		pnlDiaTreHan.add(tfDiskTitle);
		tfDiskTitle.setColumns(10);
		
		tableDesign(tblDisk);
		tableRenderer();
		setTBColumnWidth();
		
		btnFindCustomer.addActionListener(e ->{
			try {
				if (validCustomer()) {
					int cusID = Integer.parseInt(tfCusID.getText());
					Customer customer = customerDAO.getCustomerByID(cusID);
					if (customer == null) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng này!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}else {
						tfCusName.setText(customer.getName());
						tfAddress.setText(customer.getAddress());
						tfPhoneNumber.setText(customer.getPhoneNumber());
					}
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnAddDisk.addActionListener(e ->{
			String titleName = tfDiskTitle.getText();
			if (validDisk()) {
				clearTable();
				loadTable(titleName);
			}
		});
		
		btnDatTruoc.addActionListener(e ->{
			if (validData()) {
				int cusID = Integer.parseInt(tfCusID.getText());
				int selectedRow = tblDisk.getSelectedRow();
				int titleID = Integer.parseInt(tblDisk.getValueAt(selectedRow, 1)+"");
				
				if (!reservationDAO.checkReservationRecord(cusID, titleID)) {
					Customer customer = customerDAO.getCustomerByID(cusID);
					Title title = titleDAO.getTitleByID(titleID);
					
					ReservationRecord record = new ReservationRecord();
					record.setCustomer(customer);
					record.setTitle(title);
					record.setOrderDate(new Date());
					reservationDAO.add(record);
					
					JOptionPane.showMessageDialog(null, "Đặt trước thành công!!", "", JOptionPane.INFORMATION_MESSAGE);
					clearAll();
				}else {
					JOptionPane.showMessageDialog(null, "Khách hàng đã đặt tựa đĩa này!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		btnHuy.addActionListener(e ->{
			if (JOptionPane.showConfirmDialog(this, "Hủy đặt trước?", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				clearAll();
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
		tfDiskTitle.setText("");
	}
	
	private void loadTable(String name) {
		List<Title> list = titleDAO.findTitleByName(name);
		if (list.size() == 0) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy tựa đề này!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}else {
			for (Title title : list) {
				int i = modelDisk.getRowCount();
				String[] rowData = {i+1+"", title.getId()+"", title.getName(), title.getCategory().getName()};		
				modelDisk.addRow(rowData);
			}
			tblDisk.setModel(modelDisk);
		}
	}
	
	private boolean validDisk() {
		if (tfDiskTitle.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tựa đĩa!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private boolean validCustomer() {
		if (tfCusID.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập mã khách hàng!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!tfCusID.getText().trim().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private boolean validData() {
		if (tfDiskTitle.getText().trim().equals("") || tfCusID.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}else if (tblDisk.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "Chưa chọn đĩa!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}else if (!tfCusID.getText().trim().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
		tblDisk.getColumn("Mã tựa").setCellRenderer(centerCellRenderer);
		tblDisk.getColumn("Loại đĩa").setCellRenderer(centerCellRenderer);
	}
	
	private void setTBColumnWidth() {	
		TableColumn column = null;
		for (int i = 0; i < tblDisk.getColumnCount(); i++) {
			column = tblDisk.getColumnModel().getColumn(i);
			if(i==2) {
				column.setPreferredWidth(250);
			}
		}
	}
}
