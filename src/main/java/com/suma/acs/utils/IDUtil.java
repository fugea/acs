/**
 * 
 */
package com.suma.acs.utils;

import java.util.UUID;

public class IDUtil {

	public IDUtil() {
		// TODO Auto-generated constructor stub
	}

	public String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String temp = str.substring(0, 8) + str.substring(9, 13)
				+ str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		return temp;
	}

	public String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}

	private static Long BKDRHash(String key) {
		
		if (key == null || key.equals("")) {
			return null;
		}
		int seed = 31;// 31 131 1313 13131 1313131 etc....
		int hash = 0;
		for (int i = 0; i < key.length(); i++) {
			hash = hash * seed + key.charAt(i);
		}
		return Long.valueOf((hash & 0x0FFFFFFF)|0x40000000);
	}
	
	public static long generateID(String key) {
		return BKDRHash(key);
	}

	public long getId(String rawId, String prefix) {
		long id = BKDRHash(prefix + rawId);
		return id;
	}
	
	public long getItemId(String assetId) {
		String prefix = "GUANGDONGVOD";
		long itemId = getId(assetId, prefix);
		return itemId;
	}
	
   public static Integer FolderId_BKDRHash(String key) {
		
		if (key == null || key.equals("")) {
			return null;
		}
		
		int seed = 31;// 31 131 1313 13131 1313131 etc....
		int hash = 0;

		for (int i = 0; i < key.length(); i++) {
			hash = hash * seed + key.charAt(i);
		}		
		return Integer.valueOf((hash & 0x0FFFFFFF));
	}
	
	public long getParentId(String folderId) {
		String prefix = "GUANGDONGVOD";
		long parentId = getId(folderId, prefix);
		return parentId;
	}
	
	public static void main(String[] args) {
		System.out.println(new IDUtil().generateID("10000100000000010000000000139799"));
		System.out.println(new IDUtil().generateID("00000001000000010000000025427555"));
	}
}
