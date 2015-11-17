package provider;

import common.IProduct;


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Provider
{

	private static List<IProduct> m_refProducts;

	static {
		m_refProducts = Collections.synchronizedList(new ArrayList<IProduct>());		
		m_refProducts.add("#11");
		m_refProducts.add("#22");
		m_refProducts.add("#33");

	}

	public List<IProduct> getListProduct(){

	}

	public int availabilityProduct(IProduct ref){

	}

	public boolean orderProduct(IProduct ref, int amount){

	}




}