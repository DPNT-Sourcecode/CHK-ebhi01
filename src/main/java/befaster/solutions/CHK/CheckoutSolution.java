package befaster.solutions.CHK;

import java.util.HashMap;

public class CheckoutSolution {
	
	final HashMap<String, Integer> products = new HashMap<String, Integer>(0);
	final HashMap<String, Integer> productCount = new HashMap<String, Integer>(0);
	
	public Integer checkout(String skus)
	{
		// clear down maps.  Actual population of the maps would normally
		// be done elsewhere, not in the checkout() method.
		this.products.clear();
		this.productCount.clear();
		
		// sku prices
		this.products.put("A", new Integer(50));
		this.products.put("B", new Integer(30));
		this.products.put("C", new Integer(20));
		this.products.put("D", new Integer(15));
		this.products.put("E", new Integer(40));
		this.products.put("F", new Integer(10));
		this.products.put("G", new Integer(20));
		this.products.put("H", new Integer(10));
		this.products.put("I", new Integer(35));
		this.products.put("J", new Integer(60));
		this.products.put("K", new Integer(80));
		this.products.put("L", new Integer(90));
		this.products.put("M", new Integer(15));
		this.products.put("N", new Integer(40));
		this.products.put("O", new Integer(10));
		this.products.put("P", new Integer(50));
		this.products.put("Q", new Integer(30));
		this.products.put("R", new Integer(50));
		this.products.put("S", new Integer(30));
		this.products.put("T", new Integer(20));
		this.products.put("U", new Integer(40));
		this.products.put("V", new Integer(50));
		this.products.put("W", new Integer(20));
		this.products.put("X", new Integer(90));
		this.products.put("Y", new Integer(10));
		this.products.put("Z", new Integer(50));
		
		// assumes a basic string input.
		int value = 0;
		if (skus.length() > 0)
		{
			String[] tokens = skus.split("(?!^)"); // split into individual characters.

			// check each string token
			for (String str : tokens)
			{
				if (products.containsKey(str))
				{
					value += products.get(str).intValue();
					int currentCount = 0;
					if (productCount.containsKey(str))
					{
						currentCount = productCount.get(str).intValue();
					}
					productCount.put(str, new Integer(++currentCount));
				} 
				else
				{
					value = -1; // erroneous input, drop out of loop and method with -1.
					break;
				}
			}

			if (value >= 0) // check for non-negative value, applying discounts if so.
			{
				int aCount = productCount.get("A").intValue();
				int bCount = productCount.get("B").intValue();
				int eCount = productCount.get("E").intValue();
				int fCount = productCount.get("F").intValue();
				int hCount = productCount.get("H").intValue();
				
				int a3Discount = 20; // (3 * 50) - 130.
				int a5Discount = 50; // (5 * 50) - 200.
				int bDiscount = 15; // (2 * 30) - 45
				int eDiscount = 30; // one B.
				int fDiscount = 10; // one F.
				int h5Discount = 5; // (5 * 10) - 45.
				int h10Discount = 20; // (10 * 10) - 80.
				
				// work out how many deals have been claimed.
				int a5Deals = aCount / 5;
				int a3Deals = (aCount % 5) / 3;
				int eDealsPotential = eCount / 2;
				int eDeals = (bCount >= eDealsPotential) ? eDealsPotential : 0;
				int h10Deals = hCount / 10;
				int h5Deals = (hCount % 10) / 5;

				int bDeals = (bCount - eDeals) / 2;
				int fDeals = (fCount) / 3;

				// update value by removing the discounts for the deals.
				value = value
						- (a5Deals * a5Discount) 	// better A deal for customer.
						- (a3Deals * a3Discount) 	// pick up any remaining 3 piece deals.
						- (bDeals * bDiscount)   	// apply b discount.
						- (eDeals * eDiscount)   	// apply e discount.
						- (fDeals * fDiscount)   	// apply f discount.
						- (h10Deals * h10Discount) 	// better H deal for customer.
						- (h5Deals * h5Discount)   	// pick up any remaining 5 piece deals.
			}
		}
		return value;
	}
	
	final int calculatePrice(String product, int unitPrice, int unitCount, int discount)
	{
		int aCount = productCount.get(product).intValue();
		
	}
	
}
