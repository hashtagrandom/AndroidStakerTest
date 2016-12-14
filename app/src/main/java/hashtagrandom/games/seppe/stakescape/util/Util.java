package hashtagrandom.games.seppe.stakescape.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Util {
	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
	
	public static String hashString(String data) throws NoSuchAlgorithmException {
		StringBuffer result = new StringBuffer();
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		md.update(data.getBytes());
		
		for (byte byt : md.digest()){
			result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
		}

		return result.toString();
	}

	public static void sleepTime(int sleep){
		try {
			Thread.sleep(sleep);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
