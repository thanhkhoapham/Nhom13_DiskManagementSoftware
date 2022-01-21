package com.xdpm.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.xdpm.dao.DiskDAO;
import com.xdpm.dao.RentalDAO;
import com.xdpm.dao.ReservationDAO;
import com.xdpm.entity.Disk;
import com.xdpm.entity.RentalRecord;
import com.xdpm.entity.ReservationRecord;

import javax.swing.JButton;
import java.awt.Font;

public class UI_KiemTraDia extends JDialog{
	private UI_Main mainUI;
	private JTextField tfDiskID;
	private JLabel lblID;
	
	private DiskDAO diskDAO = new DiskDAO();
	private RentalDAO rentalDAO = new RentalDAO();
	private ReservationDAO reservationDAO = new ReservationDAO();
	private JLabel lblTitle;
	private JLabel lblStatus;
	private JLabel lblCustomer;
	
	public UI_KiemTraDia(final UI_Main mainUI, boolean modal) {
		super(mainUI, modal);
		setTitle("Kiểm tra tình trạng đĩa");
		setSize(400, 350);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblDiskID = new JLabel("Nhập mã đĩa");
		lblDiskID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiskID.setBounds(65, 35, 80, 25);
		getContentPane().add(lblDiskID);
		
		tfDiskID = new JTextField();
		tfDiskID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfDiskID.setBounds(150, 35, 80, 25);
		getContentPane().add(tfDiskID);
		tfDiskID.setColumns(10);
		
		JButton btnFindDisk = new JButton("Kiểm tra");
		btnFindDisk.setBounds(240, 35, 85, 25);
		getContentPane().add(btnFindDisk);
		
		lblID = new JLabel("");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblID.setBounds(40, 85, 300, 25);
		getContentPane().add(lblID);
		
		lblTitle = new JLabel("");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitle.setBounds(40, 130, 300, 25);
		getContentPane().add(lblTitle);
		
		lblStatus = new JLabel("");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStatus.setBounds(40, 175, 300, 25);
		getContentPane().add(lblStatus);
		
		lblCustomer = new JLabel("");
		lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCustomer.setBounds(40, 220, 300, 25);
		getContentPane().add(lblCustomer);
		
		btnFindDisk.addActionListener(e ->{
			try {
				int diskID = Integer.parseInt(tfDiskID.getText());
				if (diskID < 0) {
					JOptionPane.showMessageDialog(null, "Mã đĩa không hợp lệ!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}else {
					Disk disk = diskDAO.getDiskByID(diskID);
					if (disk == null) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy đĩa!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}else {
						if (disk.getStatus().equals("onShelf")) {
							lblID.setText("ID: "+disk.getId());
							lblTitle.setText("Tựa đĩa: "+disk.getTitle().getName());
							lblStatus.setText("Trạng thái: trên kệ");
							lblCustomer.setText("");
						}else if (disk.getStatus().equals("rented")) {
							RentalRecord rentalRecord = rentalDAO.getByDiskID(diskID);
							lblID.setText("ID: "+disk.getId());
							lblTitle.setText("Tựa đĩa: "+disk.getTitle().getName());
							lblStatus.setText("Trạng thái: đang được thuê");
							lblCustomer.setText("Người thuê: "+rentalRecord.getCustomer().getName());
						}else {
							ReservationRecord reservationRecord = reservationDAO.getByDiskID(diskID);
							lblID.setText("ID: "+disk.getId());
							lblTitle.setText("Tựa đĩa: "+disk.getTitle().getName());
							lblStatus.setText("Trạng thái: đang được giữ");
							lblCustomer.setText("Được giữ cho: "+reservationRecord.getCustomer().getName());
						}
					}
				}
			} catch (Exception e2) {
				if (tfDiskID.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Chưa nhập mã đĩa!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Mã đĩa không hợp lệ!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
}
