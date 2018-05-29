package befaster.solutions.CHK;

import java.util.HashMap;

public class CheckoutSolution {
	public Integer checkout(String skus) {

		// assumes a basic string input.
		int value = 0;
		if (skus.length() > 0) {
			String[] tokens = skus.split("(?!^)");

			final HashMap<String, Integer> products = new HashMap<String, Integer>(0);
			// sku prices
			products.put("A", new Integer(50));
			products.put("B", new Integer(30));
			products.put("C", new Integer(20));
			products.put("D", new Integer(15));
			products.put("E", new Integer(40));
			products.put("F", new Integer(10));
			products.put("G", new Integer(20));
			products.put("H", new Integer(10));
			products.put("I", new Integer(35));
			products.put("J", new Integer(60));
			products.put("K", new Integer(80));
			products.put("L", new Integer(90));
			products.put("M", new Integer(15));
			products.put("N", new Integer(40));
			products.put("O", new Integer(10));
			products.put("P", new Integer(50));
			products.put("Q", new Integer(30));
			products.put("R", new Integer(50));
			products.put("S", new Integer(30));
			products.put("T", new Integer(20));
			products.put("U", new Integer(40));
			products.put("V", new Integer(50));
			products.put("W", new Integer(20));
			products.put("X", new Integer(90));
			products.put("Y", new Integer(10));
			products.put("Z", new Integer(50));

			final HashMap<String, Integer> productCount = new HashMap<String, Integer>(0);
			int a3Discount = 20; // (3 * 50) - 130.
			int a5Discount = 50; // (5 * 50) - 200.
			int bDiscount = 15; // (2 * 30) - 45
			int eDiscount = 30; // one B.
			int fDiscount = 10; // one F.

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
				// work out how many deals have been claimed.
				int a5Deals = aCount / 5;
				int a3Deals = (aCount % 5) / 3;
				int eDealsPotential = eCount / 2;
				int eDeals = (bCount >= eDealsPotential) ? eDealsPotential : 0;

				int bDeals = (bCount - eDeals) / 2;
				int fDeals = (fCount) / 3;

				// update value by removing the discounts for the deals.
				value = value - (a5Deals * a5Discount) // better deal for customer.
						- (a3Deals * a3Discount) // pick up any remaining 3 piece deals.
						- (bDeals * bDiscount) // apply b discount.
						- (eDeals * eDiscount) // apply e discount.
						- (fDeals * fDiscount); // apply f discount.
			}
		}
		return value;
	}
}
