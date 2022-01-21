package com.xdpm.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JTabbedPane;

import SQLServerConnUtils.SQLServerConnUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class UI_Main extends JFrame {

	private JMenuBar menuBar;
	private JMenu mnThueDia;
	private JMenu mnDatTruoc;
	private JMenu mnTraTre;
	private JMenu mnQuanLy;
	private JMenu mnBaoCao;
	private JMenu mnKhac;
	private JMenuItem jmiThueDia;
	private JMenuItem jmiTraDia;
	private JMenuItem jmiKTTTDia;
	private JMenuItem jmiThemDatTruoc;
	private JMenuItem jmiHuyDatTruoc;
	private JMenuItem jmiThanhToan;
	private JMenuItem jmiQLTuaDe;
	private JMenuItem jmiQLKhachHang;
	private JMenu jmiBCKhachHang;
	private JMenu jmiBCTuaDe;
	private JMenuItem jmiCapNhatGiaThue;
	private JMenuItem jmiCapNhatNgayThue;
	private JMenuItem jmiQLDia;
	private JButton btnDangNhap;
	private UI_ThueDia pnlThueDia;
	private UI_TraDia pnlTraDia;
	private JTabbedPane tabbedPane;
	private UI_TTTreHan pnlTTTreHan;
	private UI_DatTruoc pnlDatTruoc;
	private UI_HuyDatTruoc pnlHuyDatTruoc;
	private JMenuItem jmiBCTatCaKH;
	private JMenuItem jmiBCKhChuaTraDia;
	private JMenuItem jmiKHDangNo;
	private JMenuItem jmiQLTuaDe_Chitiet;
	private UI_QL_Dia pnlQLDia;
	private UI_QL_TuaDe pnlQLTua;
	private UI_QL_KhachHang pnlQLKhachHang;
	private JMenuItem jmiTTCBDia;
	private JMenuItem jmiDiaDangThue;
	private JMenuItem jmiDiaDangGiu;
	private JMenuItem jmiDiaTrenKe;
	private JMenuItem jmiTongSoDia;
	private JMenuItem jmiBCKhSoHuuDia;

	public UI_Main() {
		Font font = new Font("Tahoma", Font.PLAIN, 16);
		Font font2 = new Font("Tahoma", Font.PLAIN, 14);

		setTitle("Quản lý thuê đĩa");
		setSize(1100, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		ImageIcon mainIcon = new ImageIcon("image/disk.png");
		setIconImage(mainIcon.getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnThueDia = new JMenu("Thuê - Trả đĩa");
		mnThueDia.setFont(font);
		menuBar.add(mnThueDia);

		mnDatTruoc = new JMenu("Đặt trước");
		mnDatTruoc.setFont(font);
		menuBar.add(mnDatTruoc);

		mnTraTre = new JMenu("Trả trễ");
		mnTraTre.setFont(font);
		menuBar.add(mnTraTre);

		mnQuanLy = new JMenu("Quản lý");
		mnQuanLy.setFont(font);
		menuBar.add(mnQuanLy);

		// ==========================================================
		jmiThueDia = new JMenuItem("Thuê đĩa", new ImageIcon("image/Thue.png"));
		jmiThueDia.setFont(font2);
		mnThueDia.add(jmiThueDia);

		jmiTraDia = new JMenuItem("Trả đĩa", new ImageIcon("image/TraDia.png"));
		jmiTraDia.setFont(font2);
		mnThueDia.add(jmiTraDia);

		jmiKTTTDia = new JMenuItem("Kiểm tra trạng thái đĩa", new ImageIcon("image/KtraTrangThai.png"));
		jmiKTTTDia.setFont(font2);
		mnThueDia.add(jmiKTTTDia);

		jmiThemDatTruoc = new JMenuItem("Thêm đặt trước", new ImageIcon("image/ThemDatTruoc.png"));
		jmiThemDatTruoc.setFont(font2);
		mnDatTruoc.add(jmiThemDatTruoc);

		jmiHuyDatTruoc = new JMenuItem("Hủy đặt trước", new ImageIcon("image/HuyDatTruoc.png"));
		jmiHuyDatTruoc.setFont(font2);
		mnDatTruoc.add(jmiHuyDatTruoc);

		jmiThanhToan = new JMenuItem("Thanh toán phí trễ", new ImageIcon("image/money.png"));
		jmiThanhToan.setFont(font2);
		mnTraTre.add(jmiThanhToan);

		jmiQLKhachHang = new JMenuItem("Quản lý khách hàng", new ImageIcon("image/customer.png"));
		jmiQLKhachHang.setFont(font2);
		mnQuanLy.add(jmiQLKhachHang);

		jmiQLTuaDe_Chitiet = new JMenuItem("Xem chi tiết tựa phim/game", new ImageIcon("image/ChiTietTua.png"));
		jmiQLTuaDe_Chitiet.setFont(font2);
		mnQuanLy.add(jmiQLTuaDe_Chitiet);

		// ==========================================================
		mnThueDia.setPreferredSize(new Dimension(165, 40));
		mnDatTruoc.setPreferredSize(new Dimension(165, 40));
		mnTraTre.setPreferredSize(new Dimension(165, 40));
		mnQuanLy.setPreferredSize(new Dimension(165, 40));
		jmiThemDatTruoc.setPreferredSize(new Dimension(165, 40));
		jmiHuyDatTruoc.setPreferredSize(new Dimension(165, 40));
		// ==========================================================
		mnThueDia.setIcon(new ImageIcon("image/disk.png"));
		mnDatTruoc.setIcon(new ImageIcon("image/DatTruoc.png"));
		mnTraTre.setIcon(new ImageIcon("image/money.png"));
		mnQuanLy.setIcon(new ImageIcon("image/quanLy.png"));
		getContentPane().setLayout(null);

		JPanel pnlDangNhap = new JPanel();
		pnlDangNhap.setBackground(Color.WHITE);
		pnlDangNhap.setBounds(10, 561, 1066, 50);
		getContentPane().add(pnlDangNhap);
		pnlDangNhap.setLayout(null);

		btnDangNhap = new JButton("Đăng nhập quản lý");
		btnDangNhap.setBounds(896, 10, 142, 30);
		pnlDangNhap.add(btnDangNhap);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 1066, 541);
		tabbedPane.setFont(font);
		getContentPane().add(tabbedPane);

		// ==========================================================
		btnDangNhap.addActionListener(e -> {
			if (btnDangNhap.getText().equals("Đăng nhập quản lý")) {
				UI_Login loginUI = new UI_Login(this, true);
				loginUI.setVisible(true);
			} else {
				changeUI(false);
				repaint();
				validate();
			}
		});

		// ==========================================================
		pnlThueDia = new UI_ThueDia();
		jmiThueDia.addActionListener(e -> {
			tabbedPane.removeAll();
			tabbedPane.addTab("Thuê đĩa", new ImageIcon("image/Thue.png"), pnlThueDia, "Thuê đĩa");
		});

		pnlTraDia = new UI_TraDia();
		jmiTraDia.addActionListener(e -> {
			tabbedPane.removeAll();
			tabbedPane.addTab("Trả đĩa", new ImageIcon("image/TraDia.png"), pnlTraDia, "Trả đĩa");
		});

		jmiKTTTDia.addActionListener(e -> {
			UI_KiemTraDia ui_ktdia = new UI_KiemTraDia(this, true);
			ui_ktdia.setVisible(true);
		});

		pnlTTTreHan = new UI_TTTreHan();
		jmiThanhToan.addActionListener(e -> {
			tabbedPane.removeAll();
			tabbedPane.addTab("Thanh toán trễ hạn", new ImageIcon("image/money.png"), pnlTTTreHan,
					"Thanh toán trễ hạn");
		});

		pnlDatTruoc = new UI_DatTruoc();
		jmiThemDatTruoc.addActionListener(e -> {
			tabbedPane.removeAll();
			tabbedPane.addTab("Đặt trước tựa đề", new ImageIcon("image/ThemDatTruoc.png"), pnlDatTruoc,
					"Đặt trước tựa đề");
		});

		pnlHuyDatTruoc = new UI_HuyDatTruoc();
		jmiHuyDatTruoc.addActionListener(e -> {
			tabbedPane.removeAll();
			tabbedPane.addTab("Hủy đặt trước", new ImageIcon("image/HuyDatTruoc.png"), pnlHuyDatTruoc, "Hủy đặt trước");
		});

		jmiQLTuaDe_Chitiet.addActionListener(e -> {
			JFrame chiTietTuaDe = new UI_QL_ChiTietTuaDe();
			chiTietTuaDe.setVisible(true);
		});

		pnlQLKhachHang = new UI_QL_KhachHang();
		jmiQLKhachHang.addActionListener(e -> {
			tabbedPane.removeAll();
			tabbedPane.addTab("Quản lý thông tin khách", new ImageIcon("image/customer.png"), pnlQLKhachHang,
					"Quản lý thông tin khách");
		});
	}

	public void changeUI(boolean isManager) {
		Font font = new Font("Tahoma", Font.PLAIN, 16);
		Font font2 = new Font("Tahoma", Font.PLAIN, 14);
		if (isManager) {
			mnBaoCao = new JMenu("Báo cáo");
			mnBaoCao.setFont(font);
			menuBar.add(mnBaoCao);

			mnKhac = new JMenu("Khác");
			mnKhac.setFont(font);
			menuBar.add(mnKhac);

			// ==========================================================
			jmiQLDia = new JMenuItem("Quản lý đĩa", new ImageIcon("image/QLDia.png"));
			jmiQLDia.setFont(font2);
			mnQuanLy.add(jmiQLDia);

			jmiBCKhachHang = new JMenu("Báo cáo khách hàng");
			jmiBCKhachHang.setFont(font2);
			mnBaoCao.add(jmiBCKhachHang);

			jmiBCTatCaKH = new JMenuItem("Tất cả khách hàng", new ImageIcon("image/disk.png"));
			jmiBCTatCaKH.setFont(font2);
			jmiBCKhachHang.add(jmiBCTatCaKH);

			jmiBCKhChuaTraDia = new JMenuItem("Khách hàng quá hạn thuê đĩa", new ImageIcon("image/disk.png"));
			jmiBCKhChuaTraDia.setFont(font2);
			jmiBCKhachHang.add(jmiBCKhChuaTraDia);

			jmiKHDangNo = new JMenuItem("Khách hàng đang nợ", new ImageIcon("image/disk.png"));
			jmiKHDangNo.setFont(font2);
			jmiBCKhachHang.add(jmiKHDangNo);

			jmiBCKhSoHuuDia = new JMenuItem("Số đĩa đang sở hữu của khách", new ImageIcon("image/disk.png"));
			jmiBCKhSoHuuDia.setFont(font2);
			jmiBCKhachHang.add(jmiBCKhSoHuuDia);

			jmiBCTuaDe = new JMenu("Báo cáo tựa đề");
			jmiBCTuaDe.setFont(font2);
			mnBaoCao.add(jmiBCTuaDe);

			jmiTTCBDia = new JMenuItem("Thông tin cơ bản", new ImageIcon("image/disk.png"));
			jmiTTCBDia.setFont(font2);
			jmiBCTuaDe.add(jmiTTCBDia);

			jmiDiaDangThue = new JMenuItem("Số đĩa đang thuê", new ImageIcon("image/disk.png"));
			jmiDiaDangThue.setFont(font2);
			jmiBCTuaDe.add(jmiDiaDangThue);

			jmiDiaDangGiu = new JMenuItem("Số đĩa đang giữ", new ImageIcon("image/disk.png"));
			jmiDiaDangGiu.setFont(font2);
			jmiBCTuaDe.add(jmiDiaDangGiu);

			jmiDiaTrenKe = new JMenuItem("Số đĩa trên kệ", new ImageIcon("image/disk.png"));
			jmiDiaTrenKe.setFont(font2);
			jmiBCTuaDe.add(jmiDiaTrenKe);

			jmiCapNhatGiaThue = new JMenuItem("Cập nhật giá thuê", new ImageIcon("image/giaThue.png"));
			jmiCapNhatGiaThue.setFont(font2);
			mnKhac.add(jmiCapNhatGiaThue);

			jmiCapNhatNgayThue = new JMenuItem("Cập nhật số ngày thuê", new ImageIcon("image/clock.png"));
			jmiCapNhatNgayThue.setFont(font2);
			mnKhac.add(jmiCapNhatNgayThue);

			jmiQLTuaDe = new JMenuItem("Quản lý tựa đề", new ImageIcon("image/QLTuaDe.png"));
			jmiQLTuaDe.setFont(font2);
			mnQuanLy.add(jmiQLTuaDe);
			// ==========================================================
			mnBaoCao.setPreferredSize(new Dimension(165, 40));
			mnKhac.setPreferredSize(new Dimension(165, 40));

			mnBaoCao.setIcon(new ImageIcon("image/report.png"));
			mnKhac.setIcon(new ImageIcon("image/update.png"));
			jmiBCKhachHang.setIcon(new ImageIcon("image/report.png"));
			jmiBCTuaDe.setIcon(new ImageIcon("image/report.png"));

			jmiBCTatCaKH.setPreferredSize(new Dimension(210, 40));
			jmiBCKhChuaTraDia.setPreferredSize(new Dimension(210, 40));
			jmiKHDangNo.setPreferredSize(new Dimension(210, 40));

			tabbedPane.removeAll();
			btnDangNhap.setText("Đăng xuất");

			// ===========Bat su kien ben trong admin
			pnlQLDia = new UI_QL_Dia();
			pnlQLTua = new UI_QL_TuaDe();

			jmiCapNhatGiaThue.addActionListener(e -> {
				tabbedPane.removeAll();
				JFrame jfrmCapNhapGiaThue = new UI_CapNhat_GiaThue();
				jfrmCapNhapGiaThue.setVisible(true);
			});
			jmiCapNhatNgayThue.addActionListener(e -> {
				tabbedPane.removeAll();
				JFrame jfrmCapNhapNgayThue = new UI_CapNhat_NgayThue();
				jfrmCapNhapNgayThue.setVisible(true);
			});

			jmiQLDia.addActionListener(e -> {
				tabbedPane.removeAll();
				tabbedPane.addTab("Quản lý đĩa", new ImageIcon("image/disk.png"), pnlQLDia, "Quản lý đĩa");
			});

			jmiQLTuaDe.addActionListener(e -> {
				tabbedPane.removeAll();
				tabbedPane.addTab("Quản lý tựa đề", new ImageIcon("image/disk.png"), pnlQLTua, "Quản lý tựa đề");
			});

			// =============Bao cao dia
			jmiTTCBDia.addActionListener(e -> {
				Map<String, Object> parameters = new HashMap<String, Object>();
				xuatBaoCao("BaoCao_ThongTinTuaDe.jrxml", parameters);
			});

			// Bao Cao KH
			jmiBCTatCaKH.addActionListener(e -> {
				Map<String, Object> parameters = new HashMap<String, Object>();
				xuatBaoCao("BaoCaoTTKH.jrxml", parameters);
			});
			jmiBCKhChuaTraDia.addActionListener(e -> {
				Map<String, Object> parameters = new HashMap<String, Object>();
				xuatBaoCao("BaoCaoDanhSachDiaDangQuaHanCuaKH.jrxml", parameters);
			});
			jmiKHDangNo.addActionListener(e -> {
				Map<String, Object> parameters = new HashMap<String, Object>();
				xuatBaoCao("BaoCaoKhoanPhiNo.jrxml", parameters);
			});
			jmiBCKhSoHuuDia.addActionListener(e -> {
				Map<String, Object> parameters = new HashMap<String, Object>();
				xuatBaoCao("SoLuongDiaDuocThue.jrxml", parameters);
			});
//			jmiBCTatCaKH jmiBCKhChuaTraDia jmiKHDangNo
			// Bao cao Dia
			jmiTTCBDia.addActionListener(e -> {
				Map<String, Object> parameters = new HashMap<String, Object>();
				xuatBaoCao("BaoCao_ThongTinTuaDe.jrxml", parameters);
			});
			jmiDiaDangGiu.addActionListener(e -> {
				Map<String, Object> parameters = new HashMap<String, Object>();
				xuatBaoCao("BaoCao_SoLuongDiaDatTruoc.jrxml", parameters);
			});
			jmiDiaDangThue.addActionListener(e -> {
				Map<String, Object> parameters = new HashMap<String, Object>();
				xuatBaoCao("BaoCao_SoLuongDiaDangThueTheoTuaDe.jrxml", parameters);
			});
			jmiDiaTrenKe.addActionListener(e -> {
				Map<String, Object> parameters = new HashMap<String, Object>();
				xuatBaoCao("BaoCao_SoLuongDiaTrenKe.jrxml", parameters);
			});
		} else {
			mnQuanLy.add(jmiQLTuaDe_Chitiet);
			menuBar.remove(mnBaoCao);
			menuBar.remove(mnKhac);
			mnQuanLy.remove(jmiQLDia);
			mnQuanLy.remove(jmiQLTuaDe);
			tabbedPane.removeAll();
			btnDangNhap.setText("Đăng nhập quản lý");
		}
	}

	protected void xuatBaoCao(String string, Map<String, Object> parameters) {
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport("src/main/java/com/xdpm/report/" + string);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					SQLServerConnUtils.getSQLServerConnection_SQLJDBC());
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
