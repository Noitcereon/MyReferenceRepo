using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CommentorTrial.Console
{
    public class PriceCalculator
    {
        public delegate decimal TaxCalculation(decimal priceBeforeTax);

        public static decimal Calculate(TaxCalculation taxCalculation, List<decimal> itemPrices)
        {
            decimal priceBeforeTax = itemPrices.Sum();

            decimal totalPrice = taxCalculation(priceBeforeTax);

            return totalPrice;
        }
    }
}
