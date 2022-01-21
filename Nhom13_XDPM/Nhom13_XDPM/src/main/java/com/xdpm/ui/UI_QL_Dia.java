package com.xdpm.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.xdpm.dao.CategoryDAO;
import com.xdpm.dao.DiskDAO;
import com.xdpm.dao.TitleDAO;
import com.xdpm.entity.Category;
import com.xdpm.entity.Disk;
import com.xdpm.entity.Title;

public class UI_QL_Dia extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4440702554365484612L;
	private JTextField txtId;
	private JTable tbDia;
	private JTextField txtStatus;
	private JTextField txtTitleName;
	private JComboBox cbbLoc;
	private JTextField txtTenTua;
	private JTextField txtMaTua;
	private JTable tbTuaDia;
	private DefaultTableModel dfTableDia;
	private DefaultTableModel dftbTuaDia;
	private DiskDAO diskDao;
	private TitleDAO titleDAO;
	private List<Title> titles = new ArrayList<Title>();
	private List<Disk> disks = new ArrayList<Disk>();
	private JTextField txtSoLuong;
	private JButton btnGiam;
	private JButton btnTang;
	private JButton btnTimTua;
	private JButton btnSave;
	private JButton btnAdd;
	private JButton btnDelete;
	private DefaultComboBoxModel<Category> model;
	private CategoryDAO categoryDao;
	private List<Title> titlesByTrend;

	/**
	 * Create the panel.
	 */
	public UI_QL_Dia() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1066, 488);
		add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(357, 62, 334, 420);
		panel.add(scrollPane);
		/**
		 * Set table
		 */
		tbDia = new JTable(){
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		dfTableDia = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã đĩa", "Trạng thái" });
		tbDia.setModel(dfTableDia);
		tbDia.getTableHeader().setReorderingAllowed(false);
		tbDia.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tbDia.getColumnModel().getColumn(0).setPreferredWidth(100);
		tbDia.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbDia.getColumnModel().getColumn(2).setPreferredWidth(170);
		tbDia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(tbDia);
		tbDia.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		/**
		 * Set combobox
		 */
		cbbLoc = new JComboBox();
		model = new DefaultComboBoxModel<Category>();
		cbbLoc = new JComboBox<Category>(model);
		cbbLoc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbbLoc.setBounds(101, 33, 73, 22);
		panel.add(cbbLoc);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Thêm mới", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(709, 308, 347, 174);
		panel.add(panel_1);
		panel_1.setLayout(null);

		btnSave = new JButton("Lưu");
		btnSave.setBounds(245, 125, 92, 31);
		panel_1.add(btnSave);
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblMTa = new JLabel("Mã tựa đề:");
		lblMTa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMTa.setBounds(22, 26, 80, 25);
		panel_1.add(lblMTa);

		txtTenTua = new JTextField();
		txtTenTua.setEditable(false);
		txtTenTua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTenTua.setColumns(10);
		txtTenTua.setBounds(112, 60, 215, 22);
		panel_1.add(txtTenTua);

		txtMaTua = new JTextField();
		txtMaTua.setEditable(false);
		txtMaTua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaTua.setColumns(10);
		txtMaTua.setBounds(112, 28, 122, 22);
		panel_1.add(txtMaTua);

		JLabel lblTnTa = new JLabel("Tên tựa đề:");
		lblTnTa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTnTa.setBounds(22, 61, 80, 25);
		panel_1.add(lblTnTa);

		btnTimTua = new JButton("Tìm");
		btnTimTua.setEnabled(false);
		btnTimTua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTimTua.setBounds(245, 28, 80, 22);
		panel_1.add(btnTimTua);
		btnAdd = new JButton("Thêm");
		btnAdd.setBounds(143, 125, 92, 31);
		panel_1.add(btnAdd);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSLng.setBounds(22, 93, 69, 25);
		panel_1.add(lblSLng);

		txtSoLuong = new JTextField();
		txtSoLuong.setText("0");
		txtSoLuong.setEditable(false);
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(150, 92, 45, 27);
		panel_1.add(txtSoLuong);

		btnTang = new JButton("");
		ImageIcon imageTang = new ImageIcon(
				new ImageIcon("image/plus.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
		ImageIcon imageGiam = new ImageIcon(
				new ImageIcon("image/minus.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
		btnTang.setIcon(imageTang);
		btnTang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTang.setBounds(200, 92, 35, 25);
		panel_1.add(btnTang);

		btnGiam = new JButton("");
		btnGiam.setIcon(imageGiam);
		btnGiam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGiam.setBounds(110, 93, 35, 25);
		panel_1.add(btnGiam);

		JLabel lblNewLabel_1 = new JLabel("Thể loại:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(30, 33, 62, 22);
		panel.add(lblNewLabel_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 62, 327, 420);
		panel.add(scrollPane_1);

		tbTuaDia = new JTable(){
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		dftbTuaDia = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "ID", "Tên đĩa" });
		tbTuaDia.setModel(dftbTuaDia);
		scrollPane_1.setViewportView(tbTuaDia);

		tbTuaDia.getTableHeader().setReorderingAllowed(false);
		tbTuaDia.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tbTuaDia.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbTuaDia.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbTuaDia.getColumnModel().getColumn(2).setPreferredWidth(170);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Thông tin đĩa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(709, 86, 347, 195);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã đĩa:");
		lblNewLabel.setBounds(24, 74, 88, 25);
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtId = new JTextField();
		txtId.setBounds(122, 76, 210, 22);
		panel_2.add(txtId);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtId.setColumns(10);

		txtStatus = new JTextField();
		txtStatus.setBounds(122, 117, 210, 22);
		panel_2.add(txtStatus);
		txtStatus.setEditable(false);
		txtStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtStatus.setColumns(10);

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setBounds(24, 118, 88, 25);
		panel_2.add(lblTrangThai);
		lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtTitleName = new JTextField();
		txtTitleName.setEditable(false);
		txtTitleName.setBounds(122, 36, 210, 22);
		panel_2.add(txtTitleName);
		txtTitleName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTitleName.setColumns(10);

		JLabel lblTuaDe = new JLabel("Tựa đề:");
		lblTuaDe.setBounds(24, 34, 88, 25);
		panel_2.add(lblTuaDe);
		lblTuaDe.setFont(new Font("Tahoma", Font.PLAIN, 12));

		btnDelete = new JButton("Xóa");
		btnDelete.setBounds(122, 149, 92, 31);
		panel_2.add(btnDelete);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		tableDesign(tbDia);
		tableDesign(tbTuaDia);
		tableRenderer();
		setTBDiaColumnWidth();
		setTBTuaDiaColumnWidth();

		/**
		 * Xu ly doi tuong
		 */
		titleDAO = new TitleDAO();
		diskDao = new DiskDAO();
		categoryDao = new CategoryDAO();
		titles = titleDAO.getAllTitles();
		loadListTitle(titles);
		List<Category> ds = categoryDao.getAllCategory();
		loadComboBox(ds);
		/**
		 * Bat su kien
		 */
		btnGiam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int soluong = Integer.parseInt(txtSoLuong.getText());
				if (soluong <= 0)
					txtSoLuong.setText("0");
				else
					txtSoLuong.setText(String.valueOf(soluong - 1));
			}
		});
		btnTang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int soluong = Integer.parseInt(txtSoLuong.getText());
				if (soluong < 0)
					txtSoLuong.setText("0");
				else
					txtSoLuong.setText(String.valueOf(soluong + 1));
			}
		});
		btnDelete.addActionListener(e -> {
			if (txtId.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Mã đĩa rỗng !!", "Thông báo", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("image/ok.png"));
				return;
			}
			String maDia = txtId.getText().trim();
			if (!(maDia.length() > 0 && maDia.matches("[0-9]+"))) {
				JOptionPane.showMessageDialog(this, "Không nhập chuỗi!!", "Thông báo", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("image/ok.png"));
				return;
			}
			Disk temp = diskDao.getDiskByID(Integer.parseInt(maDia));
			if (temp == null) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy mã: " + maDia, "Thông báo",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/ok.png"));
				return;
			}
			temp = diskDao.CheckOnShelfDiskByID(Integer.parseInt(maDia));
			if (temp == null) {
				JOptionPane.showMessageDialog(this, "Không thể xóa vì đĩa đang cho thuê!", "Thông báo",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/ok.png"));
				return;
			}
			int id = Integer.parseInt(maDia);
			int idTitle = diskDao.getDiskByID(id).getTitle().getId();

			int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa đĩa mã: '" + id + "' ", "Xác nhận",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				if (disks.size() > 0) {
					Title titleTemp = titleDAO.getTitleByID(idTitle);
					Title titleEdit = new Title(titleTemp.getId(), titleTemp.getNumberOfCopies() - 1);
					titleDAO.update(titleEdit);
					diskDao.deleteDisk(id);
					txtId.setText("");
					txtTitleName.setText("");
					txtStatus.setText("");
					disks = diskDao.getAllDiskByTitleID(idTitle);
					loadListByTitle(disks);
					if (disks.size() == 0) {
						tableEmpty();
					}
					clear();
				}
			} else
				return;

		});
		cbbLoc.addActionListener(e -> {
			if (cbbLoc.getSelectedIndex() > 0) {
				String name = cbbLoc.getSelectedItem().toString();
				Category category = categoryDao.getCategoryByName(name);
				titlesByTrend = titleDAO.getAllTitleByCategoryID(category.getId());
				loadListTitle(titlesByTrend);
			} else
				loadListTitle(titles);
		});
		tbDia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String idDia = (String) tbDia.getValueAt(tbDia.getSelectedRow(), 1);
				String trangThai = (String) tbDia.getValueAt(tbDia.getSelectedRow(), 2);
				Disk disk = diskDao.getDiskByID(Integer.parseInt(idDia));
				Title title = titleDAO.getTitleByID(disk.getTitle().getId());
				txtTitleName.setText(title.getName());
				txtId.setText(idDia);
				txtStatus.setText(trangThai);

			}
		});
		tbTuaDia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtId.setText("");
				txtStatus.setText("");
				String id = (String) tbTuaDia.getValueAt(tbTuaDia.getSelectedRow(), 1);
				txtTitleName.setText((String) tbTuaDia.getValueAt(tbTuaDia.getSelectedRow(), 2));
				txtTenTua.setText((String) tbTuaDia.getValueAt(tbTuaDia.getSelectedRow(), 2));
				txtMaTua.setText(id);
				disks = diskDao.getAllDiskSorfByTitleID(Integer.parseInt(id));
				loadListByTitle(disks);
				if (disks.size() == 0) {
					tableEmpty();
				}
			}

		});
		btnTimTua.addActionListener(e -> {
			if (txtMaTua.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn id hoặc chọn tên tựa đề phù hợp!", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Title title = titleDAO.getTitleByID(Integer.parseInt(txtMaTua.getText()));
			if (title != null) {
				txtTenTua.setText(title.getName());
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy mã :" + txtMaTua.getText(), "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		});
		btnAdd.addActionListener(e -> {
			clear();
			txtMaTua.setEditable(true);
			openButton(true);
		});
		btnSave.addActionListener(e -> {
			if (txtMaTua.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn id hoặc chọn tên tựa đề phù hợp!", "Thông báo",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/ok.png"));
				return;
			}
//			Title title = new Title();
			Disk disk = new Disk(new Title(Integer.parseInt(txtMaTua.getText())));
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			if (soLuong <= 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn số lượng", "Thông báo", JOptionPane.ERROR_MESSAGE,
						new ImageIcon("image/ok.png"));
				return;
			}
			if (txtMaTua.getText().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn id hoặc chọn tên tựa đề phù hợp!", "Thông báo",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/ok.png"));
				return;
			} else {
				if (diskDao.add(disk) != null) {
					JOptionPane.showMessageDialog(this, "Đã thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("image/ok.png"));
					for (int i = 0; i < soLuong - 1; i++) {
						Disk disk1 = new Disk(new Title(Integer.parseInt(txtMaTua.getText())));
						disk1 = diskDao.add(disk1);
					}

					Title titleTemp = titleDAO.getTitleByID(Integer.parseInt(txtMaTua.getText()));
					Title titleEdit = new Title(Integer.parseInt(txtMaTua.getText()),
							titleTemp.getNumberOfCopies() + soLuong);
					titleDAO.update(titleEdit);
					disks = diskDao.getAllDiskSorfByTitleID(Integer.parseInt(txtMaTua.getText()));
					loadListByTitle(disks);
					clear();
					openButton(false);
				} else {
					JOptionPane.showMessageDialog(this, "Lỗi: không thêm được!", "Thông báo", JOptionPane.ERROR_MESSAGE,
							new ImageIcon("image/ok.png"));
				}
			}
		});

	}

	@SuppressWarnings("unchecked")
	private void loadComboBox(List<Category> ds) {
		this.cbbLoc.addItem("----");
		for (int i = 0; i < ds.size(); i++)
			this.cbbLoc.addItem(ds.get(i).getName());
	}

	private void tableEmpty() {
		Vector<String> vt = new Vector<String>();
		dfTableDia.addRow(vt);
		dfTableDia.getDataVector().removeAllElements();
	}

	private void openButton(boolean hope) {
		if (hope) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(true);
			btnTimTua.setEnabled(true);
			btnDelete.setEnabled(false);
		} else {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnDelete.setEnabled(true);
		}

	}

	private void clear() {
		txtMaTua.setText("");
		txtTenTua.setText("");
		txtSoLuong.setText("0");

	}

	private void loadListByTitle(List<Disk> ds) {
		dfTableDia.getDataVector().removeAllElements();
		for (int i = 0; i < ds.size(); i++) {
			Vector<String> vt = new Vector<String>();
			vt.addElement(String.valueOf(i + 1));
			vt.addElement(String.valueOf(ds.get(i).getId()));
			vt.addElement(String.valueOf(ds.get(i).getStatus()));
			dfTableDia.addRow(vt);
		}

	}

	private void loadListTitle(List<Title> ds) {
		dftbTuaDia.getDataVector().removeAllElements();
		for (int i = 0; i < ds.size(); i++) {
			Vector<String> vt = new Vector<String>();
			vt.addElement(String.valueOf(i + 1));
			vt.addElement(String.valueOf(ds.get(i).getId()));
			vt.addElement(String.valueOf(ds.get(i).getName()));
			dftbTuaDia.addRow(vt);
		}

	}

	private boolean validData() {

		String maDia = txtId.getText().trim();
		if (!(maDia.length() > 0 && maDia.matches("[0-9]+"))) {
			JOptionPane.showMessageDialog(this, "Không nhập chuỗi!!", "Thông báo", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("image/ok.png"));
			return false;
		}

		return true;
	}
	
	private void tableRenderer() {
		DefaultTableCellRenderer rightCellRenderer = new DefaultTableCellRenderer();
		DefaultTableCellRenderer centerCellRenderer = new DefaultTableCellRenderer();
		
		rightCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		centerCellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		tbTuaDia.getColumn("STT").setCellRenderer(centerCellRenderer);
		tbTuaDia.getColumn("ID").setCellRenderer(centerCellRenderer);
		
		tbDia.getColumn("STT").setCellRenderer(centerCellRenderer);
		tbDia.getColumn("Mã đĩa").setCellRenderer(centerCellRenderer);
		tbDia.getColumn("Trạng thái").setCellRenderer(centerCellRenderer);
	}
	
	private void setTBDiaColumnWidth() {	
		TableColumn column = null;
		for (int i = 0; i < tbDia.getColumnCount(); i++) {
			column = tbDia.getColumnModel().getColumn(i);
			if(i==2) {
				column.setPreferredWidth(150);
			}
		}
	}
	
	private void setTBTuaDiaColumnWidth() {	
		TableColumn column = null;
		for (int i = 0; i < tbTuaDia.getColumnCount(); i++) {
			column = tbTuaDia.getColumnModel().getColumn(i);
			if(i==2) {
				column.setPreferredWidth(150);
			}
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
}
