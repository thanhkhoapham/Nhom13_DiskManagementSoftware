package com.xdpm;

import javax.swing.SwingUtilities;

import com.xdpm.dao.MyEntityManager;
import com.xdpm.ui.UI_Main;

public class App {
	public static void main(String[] args) {
		MyEntityManager.getInstance();
		SwingUtilities.invokeLater(() -> new UI_Main());
	}
}
