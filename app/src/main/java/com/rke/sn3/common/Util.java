package com.rke.sn3.common;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



/**
 * Created by tgc on 20-Feb-18.
 */

public class Util {

	public static String TAG = "UTIL";

//			0x0600 to 0x06FF
//
//			0xFB50 to 0xFDFF
//
//			0xFE70 to 0xFEFF

	public static boolean isProbablyArabic(String s) {
		for (int i = 0; i < s.length();) {
			int c = s.codePointAt(i);
			if (c >= 0x0600 && c <= 0x06E0)
				return true;
			i += Character.charCount(c);
		}
		return false;
	}

	public static void sentryLog(String s){
		try {
			throw new Exception(s);
		} catch (Exception e) {
		//	Sentry.captureException(e);
		}
	}


	public static long getLong(long defaultValue, String param)
	{
		if (param == null) return defaultValue;
		long l = defaultValue;
		try {
			l = Long.parseLong(param);
		} catch (NumberFormatException e) { }
		return l;
	}

	public static boolean getBool(boolean defaultValue, String param)
	{
		if (param == null) return defaultValue;
		boolean b = defaultValue;
		try {
			b = Boolean.parseBoolean(param);
		} catch (NumberFormatException e) { }
		return b;
	}

	public static int getInt(int defaultValue, String param)
	{
		if (param == null) return defaultValue;
		int i = defaultValue;
		try {
			i = Integer.parseInt(param);
		} catch (NumberFormatException e) { }
		return i;
	}









	public static String getCurrentWeekStartDate(int week) {
		Locale locale = new Locale( SN3Application.Me.getLocale()) ;
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		c.add(Calendar.WEEK_OF_MONTH, week);
		DateFormat df = new SimpleDateFormat("dd MMM, yyyy", locale);
		String startDate;
		startDate = df.format(c.getTime());
		return  startDate;
	}
	public static String getCurrentWeekEndDate(int week) {
		Locale locale = new Locale( SN3Application.Me.getLocale()) ;
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		c.add(Calendar.WEEK_OF_MONTH, week);
		DateFormat df = new SimpleDateFormat("dd MMM, yyyy", locale);
		String startDate;
		String endDate;
		startDate = df.format(c.getTime());
		c.add(Calendar.DAY_OF_MONTH, 6);
		endDate = df.format(c.getTime());
		return endDate;
	}
	public static String getCurrentDateTime(){
		Locale locale = new Locale( SN3Application.Me.getLocale()) ;
		Date c = Calendar.getInstance().getTime();
		SimpleDateFormat df = new SimpleDateFormat("dd MMM, yyyy | HH:mm:ss", locale);
		return df.format(c);
	}
	public static String getMonthDate(int month){
		Locale locale = new Locale( SN3Application.Me.getLocale()) ;
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		SimpleDateFormat month_date = new SimpleDateFormat("MMMM yyyy", locale);
		c.add(Calendar.MONTH, month);
	 	return  month_date.format(c.getTime());
	}
	public static long getTodayDateInMilis(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		cal.clear(Calendar.MINUTE);
		cal.set(Calendar.SECOND, 1);
		cal.clear(Calendar.MILLISECOND);
		return cal.getTimeInMillis();
	}
	public static boolean ValidEmail(String email){

		return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
				+ "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
				+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
				+ "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
				+ "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
				+ "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
	}
	public static boolean isNetworkConnected(Context context) {
		final ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] networkInfo = conMan.getAllNetworkInfo();
		for (NetworkInfo netInfo : networkInfo) {

			if (netInfo.getTypeName().equalsIgnoreCase("WIFI") || netInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
				if (netInfo.isConnected()) {
					return true;
				}
			}
		}
		return  false;
	}

	public static boolean isGPSEnabled(Context context){
		LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		boolean gps_enabled = false;
		boolean network_enabled = false;

		try {
			gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch(Exception ex) {

		}

		try {
			network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch(Exception ex) {

		}

		if(!gps_enabled && !network_enabled) {
			// notify user
			return false;
		}
		return  true;
	}
	public static String conv_MinToHour(Integer min) {
		int wo_hr = min/ 60;
		int wo_min = min % 60;
		String wo = String.format(Locale.ENGLISH,"%02d",wo_hr)  + ":"  + String.format(Locale.ENGLISH,"%02d",wo_min) ;
		return wo;
	}



	private static final String AES_MODE = "AES/CBC/PKCS7Padding";
	private static final String CHARSET = "UTF-8";
	private static final String HASH_ALGORITHM = "SHA-256";
	private static final byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

	private static SecretKeySpec generateKey(final String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		final MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
		byte[] bytes = password.getBytes("UTF-8");
		digest.update(bytes, 0, bytes.length);
		byte[] key = digest.digest();
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
		return secretKeySpec;
	}

	public static String encrypt(final String password, String message)
			throws GeneralSecurityException {

		try {
			final SecretKeySpec key = generateKey(password);
			byte[] cipherText = encrypt(key, ivBytes, message.getBytes(CHARSET));
			String encoded = Base64.encodeToString(cipherText, Base64.NO_WRAP);

			return encoded;
		} catch (UnsupportedEncodingException e) {
			throw new GeneralSecurityException(e);
		}
	}


	public static byte[] encrypt(final SecretKeySpec key, final byte[] iv, final byte[] message)
			throws GeneralSecurityException {
		final Cipher cipher = Cipher.getInstance(AES_MODE);
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		byte[] cipherText = cipher.doFinal(message);
		return cipherText;
	}


	public static String decrypt(final String password, String base64EncodedCipherText)
			throws GeneralSecurityException {

		try {
			final SecretKeySpec key = generateKey(password);
			byte[] decodedCipherText = Base64.decode(base64EncodedCipherText, Base64.NO_WRAP);
			byte[] decryptedBytes = decrypt(key, ivBytes, decodedCipherText);
			String message = new String(decryptedBytes, CHARSET);
			return message;
		} catch (UnsupportedEncodingException e) {
			throw new GeneralSecurityException(e);
		}
	}



	public static byte[] decrypt(final SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText)
			throws GeneralSecurityException {
		final Cipher cipher = Cipher.getInstance(AES_MODE);
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
		byte[] decryptedBytes = cipher.doFinal(decodedCipherText);
		return decryptedBytes;
	}

}
