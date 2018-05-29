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
				if (this.products.containsKey(str))
				{
					value += this.products.get(str).intValue();
					int currentCount = 0;
					if (this.productCount.containsKey(str))
					{
						currentCount = this.productCount.get(str).intValue();
					}
					this.productCount.put(str, new Integer(++currentCount));
				} 
				else
				{
					value = -1; // erroneous input, drop out of loop and method with -1.
					break;
				}
			}

			if (value >= 0) // check for non-negative value, applying discounts if so.
			{
				int aReduction = this.multiDeal("A", 3, 130, 5, 200);
				int hReduction = this.multiDeal("H", 5, 45, 10, 80);
				int vReduction = this.multiDeal("V", 2, 90, 3, 130);
				
				int kReduction = this.singleDeal("K", 2, 150);
				int pReduction = this.singleDeal("P", 5, 200);
				int qReduction = this.singleDeal("Q", 3, 80);
				
				int fReduction = this.bogofTypeDeal("F", 2);
				int nReduction = this.bogofTypeDeal("N", 3);
				int uReduction = this.bogofTypeDeal("U", 3);
				
				int bCount = this.productCount.get("B").intValue();
				int eCount = this.productCount.get("E").intValue();
				int fCount = this.productCount.get("F").intValue();
				
				int bDiscount = 15; // (2 * 30) - 45
				int eDiscount = 30; // one B.
				int fDiscount = 10; // one F.
				
				// work out how many deals have been claimed.
				int eDealsPotential = eCount / 2;
				int eDeals = (bCount >= eDealsPotential) ? eDealsPotential : 0;

				int bDeals = (bCount - eDeals) / 2;
				int fDeals = (fCount) / 3;

				// update value by removing the discounts for the deals.
				value = value
						- aReduction				// A multi deal
						- hReduction				// H multi deal
						- vReduction				// V multi deal
						- kReduction				// K single deal
						- pReduction				// P single deal
						- qReduction				// Q single deal
						- fReduction				// F bogof type deal
						- nReduction				// N bogof type deal
						- uReduction				// U bogof type deal
						- (bDeals * bDiscount)   	// apply b discount.
						- (eDeals * eDiscount)   	// apply e discount.
						- (fDeals * fDiscount);   	// apply f discount.
			}
		}
		return value;
	}
	
	/**
	 * Gets the BOGOF type deal price reduction.
	 * 
	 * @param product - the product key, "A", "B", etc ...
	 * @param unitCount - the unit count for the deal.
	 * @return the price reduction.
	 */
	final int bogofTypeDeal(final String product, final int unitCount)
	{
		int priceReduction = 0;
		
		if (this.products.containsKey(product) && this.productCount.containsKey(product))
		{
			int unitPrice = this.products.get(product).intValue();
			int discount = unitPrice; // calculate the discount

			int count = this.productCount.get(product).intValue();
			int deals = count / (unitCount + 1);
			
			priceReduction = (deals * discount);
		}
		
		return priceReduction;
	}
	
	/**
	 * Gets the single deal price reduction.
	 * 
	 * @param product - the product key, "A", "B", etc ...
	 * @param unitCount - the unit count for the deal.
	 * @param dealPrice - the unit count price for the deal.
	 * @return the price reduction.
	 */
	final int singleDeal(final String product, final int unitCount, final int dealPrice)
	{
		int priceReduction = 0;
		
		if (this.products.containsKey(product) && this.productCount.containsKey(product))
		{
			int unitPrice = this.products.get(product).intValue();
			int discount = (unitCount * unitPrice) - dealPrice; // calculate the discount
			
			int count = this.productCount.get(product).intValue();
		
			int deals = count / unitCount;
			
			priceReduction = (deals * discount);
		}
		
		return priceReduction;
	}
	
	/**
	 * Gets the multi deal price reduction.
	 * 
	 * @param product - the product key, "A", "B", etc ...
	 * @param loUnitCount - the low unit count for the multi deal.
	 * @param loPrice - the low unit count price for the multi deal.
	 * @param hiUnitCount - the hi unit count for the multi deal.
	 * @param hiPrice - the hi unit count price for the multi deal.
	 * @return the price reduction.
	 */
	final int multiDeal(final String product, final int loUnitCount, final int loPrice, final int hiUnitCount, final int hiPrice)
	{
		int priceReduction = 0;
		
		if (this.products.containsKey(product) && this.productCount.containsKey(product))
		{
			int unitPrice = this.products.get(product).intValue();
			int loDiscount = (loUnitCount * unitPrice) - loPrice; // calculate the lo discount
			int hiDiscount = (hiUnitCount * unitPrice) - hiPrice; // calculate the hi discount
			
			int count = this.productCount.get(product).intValue();
		
			// work out how many deals have been claimed.
			int hiDeals = count / hiUnitCount;
			int loDeals = (count % hiUnitCount) / loUnitCount;
			
			priceReduction = (hiDeals * hiDiscount) 	// hi deal better deal for customer.
					+ (loDeals * loDiscount); 		    // pick up any remaining low deals.
		
		}
		
		return priceReduction;
		
	}
	
}
