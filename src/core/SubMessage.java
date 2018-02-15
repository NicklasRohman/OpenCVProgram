package core;


import java.io.IOException;

import blur.BlurMenu;
import filter.FilterMenu;
import orginal.OrginalMenu;
import shape.DrawingMenu;
import sobel.SobelMenu;

public class SubMessage {
	
	public void menuOptions(String menuAnswer) {
		String answer;
		if (menuAnswer.equalsIgnoreCase("Origonal Image")) {
			OrginalMenu orginalMenu = new OrginalMenu();
			answer = orginalMenu.menuOptionBuilder();
			try {
				orginalMenu.printImage(answer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		} else if (menuAnswer.equalsIgnoreCase("Blur Image")) {
			BlurMenu blurMenu = new BlurMenu();
			answer = blurMenu.menuOptionBuilder();
			try {
				blurMenu.printImage(answer);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (menuAnswer.equalsIgnoreCase("Drawing Image")) {
			DrawingMenu drawingMenu = new DrawingMenu();
			answer = drawingMenu.menuOptionBuilder();
			try {
				drawingMenu.printImage(answer);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (menuAnswer.equalsIgnoreCase("Filter Image")) {
			FilterMenu filtermenu = new FilterMenu();
			answer = filtermenu.menuOptionBuilder();
			try {
				filtermenu.printImage(answer);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (menuAnswer.equalsIgnoreCase("Sobel Image")) {
			
			SobelMenu sobelmenu = new SobelMenu();
			answer = sobelmenu.menuOptionBuilder();
			try {
				sobelmenu.printImage(answer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		} 
	}
	
}
