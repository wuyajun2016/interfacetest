package com.dfire.wechat.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dfire.testcase.shop.CartControllerTest;
import com.dfire.wechat.util.BeanProvider;

public class DataPrepared {
	
	private static final Logger logger = Logger.getLogger(CartControllerTest.class);
	
	// for menu data 
	public static String menuId = BeanProvider.getUUIDName();
	public static String kindMenuId = BeanProvider.getUUIDName();
	private static final int number = 14;
	
	public static List<String> orderDetailIdList =  BeanProvider.getUUIDNameList(number);
	public static List<String> waitingOrderDetailIdList = orderDetailIdList;
	public static List<String> waitingInstanceInfoIdList = BeanProvider.getUUIDNameList(number);
	public static List<Integer> orderMenuFormStatus = new ArrayList<Integer>();
	
	public static List<String> descriptionList = new ArrayList<String>();
		
	private static int pay = 10;
	private static String entityId = "99927792";
	public static List<String> payIdList =  BeanProvider.getUUIDNameList(number);
	public static List<String> totalPayIdList = BeanProvider.getUUIDNameListWithEntityIDPrefix(number, entityId);
	
	private static final double agio_amount = 10;
	private static final double agio_service_charge = 5;
	private static final double final_amount = 100;
	
	// for create own cart without child cartSuit
	private static String uid = "b379d59ed79c4991a05fb2eb2a7b1c2c";  // 与 使用的token 响对应
	private static String seatId = BeanProvider.getUUIDName();
	
	// Instance no operation status  0  ----->   menuForm status  101
	// Instance arrive shop status 1  ------->   menuForm status  102
	// Instance timeout status 2    --------->   menuForm status  103
	// Instance no work 3           --------->   menuForm status  104
	static {
		orderMenuFormStatus.add(101);
		orderMenuFormStatus.add(101);
		orderMenuFormStatus.add(104);
		
		orderMenuFormStatus.add(101);
		orderMenuFormStatus.add(102);
		orderMenuFormStatus.add(104);
		
		orderMenuFormStatus.add(101);
		orderMenuFormStatus.add(103);
		orderMenuFormStatus.add(104);
		
		orderMenuFormStatus.add(104);
		orderMenuFormStatus.add(104);
	}
	
	public static void preparedDataForCheckOrderChange(String entityId){

		try{
			String name = "test-seat-AB";
			String seatCode = "AB";  // for waitingorderdetail 
			String batchMsg  = "14:12|wenjing|15158112345|b379d59ed79c4991a05fb2eb2a7b1c2c";  // for waitinginstanceinfo
			
			// insert data into menu table
			WeChatUtils.insertMenuIntoDB(menuId, entityId, kindMenuId); 
			WeChatUtils.insertSeatIntoDB(seatId, entityId, seatCode, name);
			
			descriptionList.add("in table waitingOrderDetail data setting: kind-3, pay_status-0, seat_code-null   "
					+ "in table waitingInstanceInfo data setting: status-9");	
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(0), orderDetailIdList.get(0), entityId, 
					WaitingOrderKind.KIND_SCAN_CODE.getIndex(), PayStatus.PAY_STATUS_NO_USE.getIndex(), seatCode, 
					WaitingOrderDetailStatus.TIME_OUT.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(0), waitingOrderDetailIdList.get(0), 
					menuId, entityId, WaitingInstanceStatus.STATUS_WORK.getIndex(), WaitingInstanceKind.KIND_NORMAL.getIndex(), batchMsg);  

			
			
			descriptionList.add("in table waitingOrderDetail data setting: kind-3, pay_status-1, seat_code-null    "
					+ "in table waitingInstanceInfo data setting: status-0");	
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(1), orderDetailIdList.get(1), entityId, 
					WaitingOrderKind.KIND_SCAN_CODE.getIndex(), PayStatus.PAY_STATUS_NO.getIndex(), seatCode, 
					WaitingOrderDetailStatus.PENDING_AUDIT.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(1), waitingOrderDetailIdList.get(1), 
					menuId, entityId, WaitingInstanceStatus.STATUS_NO_OPERATION.getIndex(), WaitingInstanceKind.KIND_SUIT.getIndex(), batchMsg);

			
			//*******************************************************************************************************************************
			descriptionList.add("in table waitingOrderDetail data setting: kind-3, pay_status-2, seat_code-AB   "
					+ "in table waitingInstanceInfo data setting: status-3");
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(2), orderDetailIdList.get(2), entityId, 
					WaitingOrderKind.KIND_SCAN_CODE.getIndex(), PayStatus.PAY_STATUS_ALREADY.getIndex(), seatCode,  
					WaitingOrderDetailStatus.DEFAULT.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(2), waitingOrderDetailIdList.get(2), 
					menuId, entityId, WaitingInstanceStatus.STATUS_NO_WORK.getIndex(), WaitingInstanceKind.KIND_NORMAL.getIndex(), batchMsg); 

			//********************************************************************************************************************************
			
			descriptionList.add("in table waitingOrderDetail data setting: kind-4, pay_status-0, seat_code-null    "
					+ "in table waitingInstanceInfo data setting: status-9");		
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(3), orderDetailIdList.get(3), entityId, 
					WaitingOrderKind.KIND_ORDER_SETA_CODE.getIndex(), PayStatus.PAY_STATUS_NO_USE.getIndex(), seatCode, 
					WaitingOrderDetailStatus.CANCELED.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(3), waitingOrderDetailIdList.get(3), 
					menuId, entityId, WaitingInstanceStatus.STATUS_WORK.getIndex(), WaitingInstanceKind.KIND_SUIT.getIndex(), batchMsg);  

			
			
			descriptionList.add("in table waitingOrderDetail data setting: kind-4, pay_status-1, seat_code-null    "
					+ "in table waitingInstanceInfo data setting: status-1");	
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(4), orderDetailIdList.get(4), entityId, 
					WaitingOrderKind.KIND_ORDER_SETA_CODE.getIndex(), PayStatus.PAY_STATUS_NO.getIndex(), seatCode, 
					WaitingOrderDetailStatus.CONFIREMED.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(4), waitingOrderDetailIdList.get(4), 
					menuId, entityId, WaitingInstanceStatus.STATUS_ARRIVE_SHOP.getIndex(), WaitingInstanceKind.KIND_NORMAL.getIndex(), batchMsg);  

			
		
			descriptionList.add("in table waitingOrderDetail data setting: kind-4, pay_status-2, seat_code-orderId    "
					+ "in table waitingInstanceInfo data setting: status-3");	
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(5), orderDetailIdList.get(5), entityId, 
					WaitingOrderKind.KIND_ORDER_SETA_CODE.getIndex(), PayStatus.PAY_STATUS_ALREADY.getIndex(), orderDetailIdList.get(5), 
					WaitingOrderDetailStatus.ARRIVED.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(5), waitingOrderDetailIdList.get(5), 
					menuId, entityId, WaitingInstanceStatus.STATUS_NO_WORK.getIndex(), WaitingInstanceKind.KIND_SUIT.getIndex(), batchMsg); 

			
			
			descriptionList.add("in table waitingOrderDetail data setting: kind-5, pay_status-0, seat_code-null    "
					+ "in table waitingInstanceInfo data setting: status-9");	
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(6), orderDetailIdList.get(6), entityId, 
					WaitingOrderKind.KIND_ORDER_PRE_ORDER.getIndex(), PayStatus.PAY_STATUS_NO_USE.getIndex(), seatCode, 
					WaitingOrderDetailStatus.TAKE_OUT_IN_OPERATION.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(6), waitingOrderDetailIdList.get(6), 
					menuId, entityId, WaitingInstanceStatus.STATUS_WORK.getIndex(), WaitingInstanceKind.KIND_NORMAL.getIndex(), batchMsg);  
			
			
			descriptionList.add("in table waitingOrderDetail data setting: kind-5, pay_status-1, seat_code-null    "
					+ "in table waitingInstanceInfo data setting: status-2");	
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(7), orderDetailIdList.get(7), entityId, 
					WaitingOrderKind.KIND_ORDER_PRE_ORDER.getIndex(), PayStatus.PAY_STATUS_NO_USE.getIndex(), seatCode, 
					WaitingOrderDetailStatus.TAKE_OUT_ARRIVED.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(7), waitingOrderDetailIdList.get(7), 
					menuId, entityId, WaitingInstanceStatus.STATUS_TIMEOUT.getIndex(), WaitingInstanceKind.KIND_SUIT.getIndex(), batchMsg); 

			
			
			
			descriptionList.add("in table waitingOrderDetail data setting: kind-5, pay_status-2, seat_code-orderId    "
					+ "in table waitingInstanceInfo data setting: status-3");		
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(8), orderDetailIdList.get(8), entityId, 
					WaitingOrderKind.KIND_ORDER_PRE_ORDER.getIndex(), PayStatus.PAY_STATUS_ALREADY.getIndex(), orderDetailIdList.get(8), 
					WaitingOrderDetailStatus.TAKE_OUT_FINISH.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(8), waitingOrderDetailIdList.get(8), 
					menuId, entityId, WaitingInstanceStatus.STATUS_NO_WORK.getIndex(), WaitingInstanceKind.KIND_NORMAL.getIndex(), batchMsg);  

			
			
			//*******************************************************************************************************************************
			descriptionList.add("in table waitingOrderDetail data setting: kind-3, pay_status-2, seat_code-AB    "
					+ "in table waitingInstanceInfo data setting: status-3");	
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(9), orderDetailIdList.get(9), entityId, 
					WaitingOrderKind.KIND_SCAN_CODE.getIndex(), PayStatus.PAY_STATUS_ALREADY.getIndex(), seatCode, 
					WaitingOrderDetailStatus.TIME_OUT.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(9), waitingOrderDetailIdList.get(9), 
					menuId, entityId, WaitingInstanceStatus.STATUS_NO_WORK.getIndex(), WaitingInstanceKind.KIND_NORMAL.getIndex(), batchMsg); 

			//*********************************************************************************************************************************
			
			//******************************************************* for test ****************************************************************
			descriptionList.add("in table waitingOrderDetail data setting: kind-5, pay_status-2, seat_code-AB    "
					+ "in table waitingInstanceInfo data setting: status-2");		
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(10), orderDetailIdList.get(10), entityId, 
					WaitingOrderKind.KIND_ORDER_PRE_ORDER.getIndex(), PayStatus.PAY_STATUS_ALREADY.getIndex(), seatCode, 0, uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(10), waitingOrderDetailIdList.get(10), 
					menuId, entityId, WaitingInstanceStatus.STATUS_TIMEOUT.getIndex(), WaitingInstanceKind.KIND_NORMAL.getIndex(), batchMsg); 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
			
			
			descriptionList.add("in table waitingOrderDetail data setting: kind-5, pay_status-2, seat_code-AB, status- -1(time out)    "
					+ "in table waitingInstanceInfo data setting: status-2");		
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(11), orderDetailIdList.get(11), entityId, 
					WaitingOrderKind.KIND_ORDER_PRE_ORDER.getIndex(), PayStatus.PAY_STATUS_ALREADY.getIndex(), seatCode, 
					WaitingOrderDetailStatus.TIME_OUT.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(11), waitingOrderDetailIdList.get(11), 
					menuId, entityId, WaitingInstanceStatus.STATUS_TIMEOUT.getIndex(), WaitingInstanceKind.KIND_NORMAL.getIndex(), batchMsg); 
			
			
			descriptionList.add("in table waitingOrderDetail data setting: kind-5, pay_status-2, seat_code-AB, status-2(pending to check order)    "
					+ "in table waitingInstanceInfo data setting: status-2");		
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(12), orderDetailIdList.get(12), entityId, 
					WaitingOrderKind.KIND_ORDER_PRE_ORDER.getIndex(), PayStatus.PAY_STATUS_ALREADY.getIndex(), seatCode, 
					WaitingOrderDetailStatus.PENDING_AUDIT.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(12), waitingOrderDetailIdList.get(12), 
					menuId, entityId, WaitingInstanceStatus.STATUS_TIMEOUT.getIndex(), WaitingInstanceKind.KIND_NORMAL.getIndex(), batchMsg); 
			
			
			descriptionList.add("in table waitingOrderDetail data setting: kind-3, pay_status-2, seat_code-AB, status- -1(time out)    "
					+ "in table waitingInstanceInfo data setting: status-2");		
			WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIdList.get(13), orderDetailIdList.get(13), entityId, 
					WaitingOrderKind.KIND_SCAN_CODE.getIndex(), PayStatus.PAY_STATUS_ALREADY.getIndex(), seatCode, 
					WaitingOrderDetailStatus.TIME_OUT.getIndex(), uid); 
			WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIdList.get(13), waitingOrderDetailIdList.get(13), 
					menuId, entityId, WaitingInstanceStatus.STATUS_TIMEOUT.getIndex(), WaitingInstanceKind.KIND_NORMAL.getIndex(), batchMsg);

			
			
			for(int i=0; i<payIdList.size(); i++){
				WeChatUtils.insertOrderDetailIntoDB(orderDetailIdList.get(i), waitingOrderDetailIdList.get(i), entityId, 
						(int)(System.currentTimeMillis()/1000), seatCode, uid, totalPayIdList.get(i)); 
				WeChatUtils.insertPayInfoIntoDB(payIdList.get(i), totalPayIdList.get(i), entityId, pay);
				WeChatUtils.insertServiceBillInfoIntoDB(totalPayIdList.get(i), entityId, agio_amount, agio_service_charge, final_amount);				
			}
			
		}catch(Exception e){
			logger.info("fail to insert data into order table ");
			logger.info(e.toString());
		}
	}
	
	
	public static void deleteDataForCheckOrderChange(){
		
		try{
			
			WeChatUtils.deleteMenuFromDB(menuId);
			WeChatUtils.deleteSeatFromDB(seatId, entityId);
			logger.info("ddelete menu and seat data successfully");
			
			for(int i=0; i<orderDetailIdList.size(); ++i){
				WeChatUtils.deleteOrderDetailFromDB(orderDetailIdList.get(i));
				WeChatUtils.deleteWaitingOrderDetailFromDB(waitingOrderDetailIdList.get(i));
				WeChatUtils.deleteWaitingInstanceFromDB(waitingInstanceInfoIdList.get(i));
				WeChatUtils.deletePayInfoFromDB(payIdList.get(i), totalPayIdList.get(i));
				WeChatUtils.deleteServiceBillInfoFromDB(totalPayIdList.get(i), entityId);
			}
			
			logger.info("delete orderDatail data, waitingOrderDetail data, waitingInstanceInfo data successfully");
			logger.info("delete pay data, total pay data successfully");
			
		}catch(Exception e){
			logger.info("fail to delete data from order table");
			logger.info(e.toString());
		}
	}

}
