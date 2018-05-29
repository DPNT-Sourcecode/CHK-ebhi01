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
			int f = 10;
			int g = 20;
			int h = 10;
			int i = 35;
			int j = 60;
			int k = 80;
			int l = 90;
			int m = 15;
			int n = 40;
			int o = 10;
			int p = 50;
			int q = 30;
			int r = 50;
			int s = 30;
			int t = 20;
			int u = 40;
			int v = 50;
			int w = 20;
			int x = 90;
			int y = 10;
			int z = 50;
			
			int aCount = 0;
			int bCount = 0;
			int eCount = 0;
			int fCount = 0;
			int a3Discount = 20; // (3 * 50) - 130.
			int a5Discount = 50; // (5 * 50) - 200.
			int bDiscount = 15; // (2 * 30) - 45
			int eDiscount = 30; // one B.
			int fDiscount = 10; // one F.

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
				} else if (str.equals("F")) {
					value += f;
					fCount++;
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
				int eDealsPotential = eCount / 2;
				int eDeals = (bCount >= eDealsPotential) ? eDealsPotential : 0;
	
				int bDeals = (bCount - eDeals) / 2;
				int fDeals = (fCount) / 3;
				
				// update value by removing the discounts for the deals.
				value = value
						- (a5Deals * a5Discount) // better deal for customer.
						- (a3Deals * a3Discount) // pick up any remaining 3 piece deals.
						- (bDeals * bDiscount)   // apply b discount.
						- (eDeals * eDiscount)   // apply e discount.
						- (fDeals * fDiscount);  // apply f discount.
			}
		}
		return value;
	}
}
