package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

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

			int aCount = 0;
			int bCount = 0;
			int aDiscount = 20; // (3 * 50) - 130.
			int bDiscount = 15; // (2 * 30) - 45

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
				} else {
					value = -1; // erroneous input, drop out of loop and method with -1.
					break;
				}
			}

			if (value >= 0) // check for non-negative value, applying discounts if so.
			{
				// work out how many deals have been claimed.
				int aDeals = aCount / 3;
				int bDeals = bCount / 2;

				// update value by removing the discounts for the deals.
				value = value - (aDeals * aDiscount) - (bDeals * bDiscount);
			}
		}
		return value;
	}
}
