package com.pcwk.hamburger;

import java.util.Scanner;

public class PaymentWindow {

	Scanner sc = new Scanner(System.in);

	public String getPaymentType() {
		while (true) {
			System.out.print("카드(1) / 현금(2) > ");
			String input = sc.nextLine().trim();
			if (input.equals("1")) {
				return "카드";
			}
			if (input.equals("2")) {
				return "현금";
			}
			System.out.println("1 또는 2를 입력하세요.");
		}
	}
}
