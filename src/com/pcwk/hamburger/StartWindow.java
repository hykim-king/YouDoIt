package com.pcwk.hamburger;

import java.util.Scanner;

/**
 * View (출력 담당)
 */
public class StartWindow {

	private Scanner sc;

	public StartWindow(Scanner sc) {
		this.sc = sc;
	}

	public void show() {
		System.out.println("""
				──────────────────────────────────────────────────────────────

				        ██╗   ██╗     ███████╗████████╗ █████╗ ██████╗ ████████╗
				        ╚██╗ ██╔╝     ██╔════╝╚══██╔══╝██╔══██╗██╔══██╗╚══██╔══╝
				         ╚████╔╝█████╗███████╗   ██║   ███████║██████╔╝   ██║
				          ╚██╔╝ ╚════╝╚════██║   ██║   ██╔══██║██╔══██╗   ██║
				           ██║        ███████║   ██║   ██║  ██║██║  ██║   ██║
				           ╚═╝        ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝

				                    🍔  BURGER KIOSK  🍔

				──────────────────────────────────────────────────────────────

				                   ▶  Press Random Key to Start  ◀

				──────────────────────────────────────────────────────────────
				""");
		System.out.print(">>");
	}
	public String waitForStart() {	
		return sc.nextLine().trim();
	}

}
