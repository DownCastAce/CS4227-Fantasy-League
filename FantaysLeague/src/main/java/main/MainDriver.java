package main;

import controllers.InitialMenuController;
import views.InitialMenuView;

public class MainDriver {
	public static void main(String [] args){

	InitialMenuView v = new InitialMenuView();
	InitialMenuController con = new InitialMenuController(v);

	}
}
