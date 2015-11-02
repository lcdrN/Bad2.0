package Exception;

import javax.swing.JOptionPane;

public class ThrowError {

	
public static void doError(String message){
		JOptionPane.showMessageDialog(null, message ,"Informations", JOptionPane.ERROR_MESSAGE);
	}


}
