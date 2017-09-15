package com.dfire.testcase.qjc.membersave;

public class runit {
	
		   public static void main(String args[]) {
			   runableTest R1 = new runableTest( "线程1");
		      R1.start();
		      
		      runableTest R2 = new runableTest( "线程2");
		      R2.start();
		   }   

}
