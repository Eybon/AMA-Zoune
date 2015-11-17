package provider;

import 

public class Product implements IProduct
{

	String m_name;
	String m_description;
	int m_price;

	public Product(String name,String desc,int price){
		m_name = name;
		m_description = desc;
		m_price = price;
	}


}