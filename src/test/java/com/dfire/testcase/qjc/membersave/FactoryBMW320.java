package com.dfire.testcase.qjc.membersave;

public class FactoryBMW320 implements FactoryBMW{

	@Override
	public carProduct createBMW() {
		// TODO Auto-generated method stub
		 return new BMW320(); 
	}

	public static void main(String args[]){
		FactoryBMW320 b320= new FactoryBMW320();
		b320.createBMW();
	}
}
