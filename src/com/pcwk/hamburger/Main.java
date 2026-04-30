package com.pcwk.hamburger;

import java.util.Scanner;

import com.pcwk.menu.Adminpage;
import com.pcwk.menu.dao.MenuDao;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		MenuDao menuDao = new MenuDao(); // MenuDao 공유
		MenuService menuService = new MenuService(menuDao);
		StartWindow startWindow = new StartWindow(sc);
		MenuWindow menuWindow = new MenuWindow(sc);
		PaymentWindow paymentWindow = new PaymentWindow(sc);
		Cart cart = new Cart();
		Adminpage admin = new Adminpage(sc, menuDao);
		
		Kiosk kiosk = new Kiosk(menuService, startWindow, menuWindow, paymentWindow, cart, admin);
		
		kiosk.run();
		
		sc.close();
		
	}
}
