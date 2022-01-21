package com.xdpm.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.xdpm.dao.DiskDAO;
import com.xdpm.dao.ReservationDAO;
import com.xdpm.entity.Disk;
import com.xdpm.entity.ReservationRecord;
import com.xdpm.util.FormatString;

import javax.swing.JButton;
import javax.swing.JTextField;

public class UI_HuyDatTruoc extends JPanel{
	private DefaultTableModel modelDisk;
	private JTable tblDisk;
	private JTextField tfCusID;
	
	private ReservationDAO reservationDAO = new ReservationDAO();
	private DiskDAO diskDAO = new DiskDAO();

	public UI_HuyDatTruoc() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 94, 818, 358);
		add(scrollPane);
		
		JLabel lblDsDatTruoc = new JLabel("Danh sách đặt trước");
		lblDsDatTruoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDsDatTruoc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDsDatTruoc.setBounds(220, 45, 454, 29);
		add(lblDsDatTruoc);
		
		String[] titleTable = { "STT", "Mã tựa", "Tựa đề", "Mã KH", "Tên khách hàng", "Số ĐT", "Ngày đặt", "Trạng thái"};
		modelDisk = new DefaultTableModel(titleTable, 0);
		tblDisk = new JTable(modelDisk) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tblDisk.getTableHeader().setReorderingAllowed(false);
		tblDisk.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblDisk);
		
		JButton btnHuyDatTruoc = new JButton("Hủy đặt trước");
		btnHuyDatTruoc.setBounds(860, 325, 153, 40);
		add(btnHuyDatTruoc);
		
		JLabel lblCusID = new JLabel("Nhập mã khách hàng");
		lblCusID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCusID.setBounds(860, 175, 125, 25);
		add(lblCusID);
		
		tfCusID = new JTextField();
		tfCusID.setBounds(860, 204, 85, 25);
		add(tfCusID);
		tfCusID.setColumns(10);
		
		JButton btnFindCustomer = new JButton("Tìm");
		btnFindCustomer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFindCustomer.setBounds(960, 204, 85, 25);
		add(btnFindCustomer);
		
		tableDesign(tblDisk);
		tableRenderer();
		setTBColumnWidth();
		
		loadTable("");
		
		btnFindCustomer.addActionListener(e ->{
			try {
				loadTable(tfCusID.getText());
			} catch (Exception e2) {
				loadTable("");
				JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnHuyDatTruoc.addActionListener(e ->{
			int row = tblDisk.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "Chưa chọn phiếu đặt trước!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}else {
				if (JOptionPane.showConfirmDialog(this, "Hủy đặt trước?", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					int cusID = Integer.parseInt(tblDisk.getValueAt(row, 3)+"");
					int titleID = Integer.parseInt(tblDisk.getValueAt(row, 1)+"");
					
					ReservationRecord record = reservationDAO.getRecordByCusIDAndTitleID(cusID, titleID);
					Disk disk = record.getDisk();
					reservationDAO.delete(record);
					if (disk != null) {
						int i = checkAndHoldDiskForCustomer(titleID, disk.getId());
						if (i == 0) {
							JOptionPane.showMessageDialog(null, "Hủy đặt trước thành công.", "", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "Hủy đặt trước thành công, đĩa đã được chuyển cho khách hàng khác.", "", JOptionPane.INFORMATION_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Hủy đặt trước thành công.", "", JOptionPane.INFORMATION_MESSAGE);
					}
					clearTable();
					loadTable("");
				}
			}
		});
	}
	
	private void clearTable() {
		while(modelDisk.getRowCount()>0) {
			modelDisk.removeRow(0);
		}
	}

	private void loadTable(String cusID) {
		clearTable();
		List<ReservationRecord> records = new ArrayList<ReservationRecord>();
		if (cusID.trim().equals("")) {
			records = reservationDAO.getAllRecord();
		}else {
			Integer customerID = Integer.parseInt(cusID);
			if (customerID < 0) {
				loadTable("");
				JOptionPane.showMessageDialog(null, "Mã khách hàng không hợp lệ!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}else {
				records = reservationDAO.getListByCusID(customerID);
			}
		}
		
		for (ReservationRecord reservationRecord : records) {
			int i = modelDisk.getRowCount();
			String status = "Đang chờ";
			if (reservationRecord.getDisk() != null) {
				status = "Đã được giữ";
			}
			String[] rowData = {i+1+"", reservationRecord.getTitle().getId()+"", reservationRecord.getTitle().getName(), reservationRecord.getCustomer().getId()+"",
					reservationRecord.getCustomer().getName(), reservationRecord.getCustomer().getPhoneNumber(),
					FormatString.formatDate(reservationRecord.getOrderDate()), status};
			modelDisk.addRow(rowData);
		}
		tblDisk.setModel(modelDisk);
	}
	
	//Trả về cusID khi đặt trước thành công
	private int checkAndHoldDiskForCustomer(int titleID, int diskID) {
		int temp = -1;
		//Lấy ds đặt trước
		List<ReservationRecord> list = reservationDAO.getListByTitleID(titleID);
		//Nếu có ds đặt trước thì gán cho ng đăt đầu tiên
		if (list.size() > 0) {
			ReservationRecord record = list.get(0);
			Disk disk = diskDAO.getDiskByID(diskID);
			disk.setStatus("onHold");
			diskDAO.update(disk);
			record.setDisk(disk);
			reservationDAO.update(record);
			temp = 1;
		}
		//Nếu không có thì set status là onShelf
		else {
			Disk disk = diskDAO.getDiskByID(diskID);
			disk.setStatus("onShelf");
			diskDAO.update(disk);
			temp = 0;
		}
		return temp;
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
		tblDisk.getColumn("Mã KH").setCellRenderer(centerCellRenderer);
		tblDisk.getColumn("Mã tựa").setCellRenderer(centerCellRenderer);
		tblDisk.getColumn("Ngày đặt").setCellRenderer(centerCellRenderer);
	}
	
	private void setTBColumnWidth() {	
		TableColumn column = null;
		for (int i = 0; i < tblDisk.getColumnCount(); i++) {
			column = tblDisk.getColumnModel().getColumn(i);
			if(i==2 || i == 4) {
				column.setPreferredWidth(150);
			}
		}
	}
}
