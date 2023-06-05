package controleur;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import modele.Modele;
import vue.VueConnexion;
import vue.VueGenerale;


public class Annexe {
	//add
	private static VueConnexion uneConnexion ;
	
	private static VueGenerale uneVueGenerale;
	
	public static void main(String[] args) {
		uneConnexion = new VueConnexion();

	}

	/*****************Gestion des users ********************/
	public static User selectWhereUser(String email, String mdp) 
	{
		User unUser = Modele.selectWhereUser(email, mdp);
		return unUser;
	}
	 public static byte[] getSHA(String mdp) 
	    { 
		 byte[] tab = null;
		 try {
	        MessageDigest md = MessageDigest.getInstance("SHA1"); 
	        tab = md.digest(mdp.getBytes(StandardCharsets.UTF_8));
		 	}
		 catch (NoSuchAlgorithmException exp) {
			 exp.printStackTrace();
		 	}
	        return  tab;
	    }
	 
	 public static String toHexString(byte[] tab)
	    {
	        // Convert byte array into signum representation 
	        BigInteger number = new BigInteger(1, tab); 
	  
	        // Convert message digest into hex value 
	        StringBuilder hexString = new StringBuilder(number.toString(16)); 
	  
	        // Pad with leading zeros
	        while (hexString.length() < 32) 
	        { 
	            hexString.insert(0, '0'); 
	        } 
	  
	        return hexString.toString(); 
	    }

	
	public static String crypterMdp(String mdp)
	{
		
		return toHexString(getSHA(mdp)); 
	}

	public static void instancierVueGenerale(User unUser) {
		uneVueGenerale = new VueGenerale(unUser);
		
	}

	public static void rendreVisibleVueConnexion(boolean action) {
		uneConnexion.setVisible(action);
		
	}

	public static void fermerVueGenerale() 
	{
		uneVueGenerale.dispose();
		
	}
}
