package com.dfire.wechat.util;

import java.io.File;
import java.net.Inet4Address;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.testng.Assert;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CommonUtil {
	
	private static final Logger logger = Logger.getLogger(CommonUtil.class);
	
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', 
		'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	public String encodeWithAlgorithm(String input, String algorithm){
		
		try{
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			byte[] inputByteArray = input.getBytes();
			messageDigest.update(inputByteArray);
			byte[] resultByteArray = messageDigest.digest();
			return getFormattedText(resultByteArray);
		}catch(Exception e){
			logger.info(e.toString());
			return null;
		}
		
	}
	
	private  String getFormattedText(byte[] bytes) {
		
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
		
	}

	/**
	 * 构造 NOS 回调认证
	 * encryptText = "NOS:" + base64_encode(Request.URL + "\n" + Request.Body)
	 * secretKey
	 * @return
	 */
	public String getNOSAuthHeader(String encryptText, String secretKey){
		String authorization = null;
		try{
			authorization = HmacSHA1Encrypt(encryptText, secretKey);
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return authorization;
	}
	
	/**
     * 使用 HMAC-SHA256 签名方法对对encryptText进行签名
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return 经过Base64编码的HMAC-SHA256签名串
     * @throws Exception
     */
	
	private static final String MAC_NAME = "HmacSHA256";	 
    private static final String ENCODING = "UTF-8";
    
    public static String HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception
    {
        byte[] data=encryptKey.getBytes(ENCODING);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);
 
        byte[] text = encryptText.getBytes(ENCODING);
        //完成 Mac 操作
 
        return new String(Base64.encodeBase64(mac.doFinal(text)));
    }
    
	/**
	 * return a Map which consists of fileSize and modifyTime
	 * @param path
	 * @param name
	 * @return
	 */
	public Map<String, Long> getFileInfo(String path, String name){
		
		File file = new File(path + "/" + name);
		Long fileSize = file.length();
		Long modifyTime = file.lastModified();
		
		Map<String, Long> fileInfo = new HashMap<String, Long>();
		fileInfo.put("fileSize", fileSize);
		fileInfo.put("modifyTime", modifyTime);
		
		return fileInfo;
	}
	
	/**
	 * return a Map which consists of fileSize and modifyTime
	 * @param path
	 * @param name
	 * @return
	 */
	public Map<String, Long> getFileInfo(String path){
		
		File file = new File(path);
		Long fileSize = file.length();
		Long modifyTime = file.lastModified();
		
		Map<String, Long> fileInfo = new HashMap<String, Long>();
		fileInfo.put("fileSize", fileSize);
		fileInfo.put("modifyTime", modifyTime);
		
		return fileInfo;
	}
	
	
	
	
	/**
	 * 验证菜单列表数据, hotMenuOnSelf 为 'true' 表明热菜中的 ‘东坡肉’ 处于上架的状态, 不用跟改热菜数字, 否则就需要跟改大菜数目
	 * @param response
	 * @param hotMenuOnSelf
	 * @return
	 */
	public Boolean compareMenuList(Response response, boolean hotMenuOnSelf){
		
		logger.info("开始进行菜单验证");
		Boolean flag = false;
		
		// 待验证数据
		int totalArrays = 6;
		int hotDish = 3;		
		int coldDish = 2;
		
		int dishSuit = 3;
		int drinks = 2;
		int alcohol = 2;
		
		int bigDish = 2;
		
		if(!hotMenuOnSelf){			
			bigDish--;
		}
		
		String idOfColdDish = CommonConstants.fruitSaladMenuId; // 水果沙拉 ID 
		String cabbageDish = CommonConstants.cabbageMenuId; // 干锅包心菜
		Map<String, Integer> menuItemMap = new HashMap<String, Integer>();
		
		try{
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();	
			JsonArray menuDataArray = resp.get("data").getAsJsonObject().get("kindMenusVos").getAsJsonArray();
			
			for(JsonElement element: menuDataArray){
				
				JsonObject kindMenu = element.getAsJsonObject();
				String name = kindMenu.get("name").getAsString();
				
				JsonArray menuArray = kindMenu.get("menus").getAsJsonArray();
				int count = 0;
				for(JsonElement menuElement : menuArray){
					count++;
					
					// 验证 水果沙拉 这道菜的基本信息
					String idString = menuElement.getAsJsonObject().get("id").getAsString();
					if( idOfColdDish.equalsIgnoreCase(idString)){
						
						Assert.assertEquals(menuElement.getAsJsonObject().get("isSoldOut").getAsString(), "false", "水果沙拉  沽清  字段验证错误");
						Assert.assertEquals(menuElement.getAsJsonObject().get("isSelf").getAsString(), "0", "水果沙拉  已上架   字段验证错误");
						Assert.assertEquals(menuElement.getAsJsonObject().get("price").getAsInt(), 10, "水果沙拉  价格  字段验证错误");
					}
					
					// 验证 干锅包心菜 这道菜的基本信息
					String idString2 = menuElement.getAsJsonObject().get("id").getAsString();
					if( cabbageDish.equalsIgnoreCase(idString2)){
						
						Assert.assertEquals(menuElement.getAsJsonObject().get("isSoldOut").getAsString(), "false", "干锅包心菜   沽清  字段验证错误");
						Assert.assertEquals(menuElement.getAsJsonObject().get("isSelf").getAsString(), "0", "干锅包心菜   已上架   字段验证错误");
						Assert.assertEquals(menuElement.getAsJsonObject().get("price").getAsInt(), 8, "干锅包心菜   价格  字段验证错误");
					}
				}
				
				// 将 热菜 等分组内的信息加入到 Map 中
				menuItemMap.put(name, count);
			}
			
			// 总的菜分组数验证
			Assert.assertEquals(menuItemMap.size(), totalArrays, "菜品总数目验证失败");
			
			// 各个菜分组数据验证
			Assert.assertEquals(menuItemMap.get("热菜").intValue(), hotDish, "热菜菜品数目验证失败");
			Assert.assertEquals(menuItemMap.get("冷菜").intValue(), coldDish, "冷菜菜品数目验证失败");
			Assert.assertEquals(menuItemMap.get("店家推荐").intValue(), dishSuit, "店家推荐菜品数目验证失败");
			
			Assert.assertEquals(menuItemMap.get("饮料").intValue(), drinks, "饮料菜品数目验证失败");
			Assert.assertEquals(menuItemMap.get("啤酒").intValue(), alcohol, "啤酒菜品数目验证失败");
			Assert.assertEquals(menuItemMap.get("大菜").intValue(), bigDish, "大菜菜品数目验证失败");
			
			flag = true;
			
		}catch(Exception e){
			
			logger.info("菜单验证失败, 原因是：" + e.toString());
			Assert.assertEquals(true, false, "菜单验证失败, 原因是: " + e.toString());
		}
		
		logger.info("菜单验证成功");
		return flag;
	}
	
	

	
}
