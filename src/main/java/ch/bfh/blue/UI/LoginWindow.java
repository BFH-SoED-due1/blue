package ch.bfh.blue.UI;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.HorizontalSplitPanel;;


public class LoginWindow {

		public Layout createLogin(){
			VerticalLayout view = new VerticalLayout();
			VerticalLayout vlogin = new VerticalLayout();
			VerticalLayout vregister = new VerticalLayout();
			HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
			
			//Login
			Button loginBtn = new Button("Login");
			TextField username = new TextField("Username");
			PasswordField pw = new PasswordField("Password");
			
			//Register
			Button registerBtn = new Button("Register new User");
			TextField registername = new TextField("Choose a Username");
			PasswordField newPw = new PasswordField("Password");
			PasswordField newPwAgain = new PasswordField("Reenter Password");
			
			vlogin.setMargin(true);
			vlogin.setSpacing(true);
			vregister.setMargin(true);
			vregister.setSpacing(true);
			
			vlogin.addComponents(username,pw,loginBtn);
			vregister.addComponents(registername, newPw, newPwAgain, registerBtn);
			
			hsplit.setFirstComponent(vlogin);
			hsplit.setSecondComponent(vregister);
			hsplit.setWidth("500");
			hsplit.setLocked(true);
			view.addComponent(hsplit);
			
			return view;
		}

}
