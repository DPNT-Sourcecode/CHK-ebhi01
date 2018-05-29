package befaster.solutions.CHK;

public class CheckoutSolution {
	public Integer checkout(String skus) {

		// assumes a basic string input.
		int value = 0;
		if (skus.length() > 0)
		{
			String[] tokens = skus.split("(?!^)");

			// sku prices
			int a = 50;
			int b = 30;
			int c = 20;
			int d = 15;
			int e = 40;

			int aCount = 0;
			int bCount = 0;
			int eCount = 0;
			int a3Discount = 20; // (3 * 50) - 130.
			int a5Discount = 50; // (5 * 50) - 200.
			int bDiscount = 15; // (2 * 30) - 45
			int eDiscount = 30; // one B.

			// check each string token
			for (String str : tokens) {
				if (str.equals("A")) {
					value += a;
					aCount++;
				} else if (str.equals("B")) {
					value += b;
					bCount++;
				} else if (str.equals("C")) {
					value += c;
				} else if (str.equals("D")) {
					value += d;
				} else if (str.equals("E")) {
					value += e;
					eCount++;
				} else {
					value = -1; // erroneous input, drop out of loop and method with -1.
					break;
				}
			}

			if (value >= 0) // check for non-negative value, applying discounts if so.
			{
				// work out how many deals have been claimed.
				int a5Deals = aCount / 5;
				int a3Deals = (aCount % 5) / 3;
				int eDeals = eCount / 2;
				int bDeals = bCount > 0 ? (bCount - eDeals) / 2 : 0;

				// update value by removing the discounts for the deals.
				value = value
						- (a5Deals * a5Discount) // better deal for customer.
						- (a3Deals * a3Discount) // pick up any remaining 3 piece deals.
						- (bDeals * bDiscount)   // apply b discount.
						- (eDeals * eDiscount);  // apply e discount.
			}
		}
		return value;
	}
}
