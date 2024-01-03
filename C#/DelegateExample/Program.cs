using System;
using System.Collections.Generic;
using System.Linq;

namespace CommentorTrial.Console
{
    public class Program
    {
        public static IEnumerable<string> AllPrefixes(int prefixLength, IEnumerable<string> words)
        {
            IEnumerable<string> prefixes = new List<string>();
            try
            {
                prefixes = words.Where(word => word.Length >= prefixLength).Select(word => word.Substring(0,3)).Distinct();
            }
            catch (Exception ex)
            {
                System.Console.WriteLine(ex.Message);
            }
           
            return prefixes;
        }

        public static void Main(string[] args)
        {
            // Should print "flo", "fle", and "fla" since those are the distinct, length 3 prefixes.
            foreach (var p in AllPrefixes(3, new string[] { "flow", "flowers", "flew", "flag", "fm" }))
                System.Console.WriteLine(p);


            decimal price = PriceCalculator.Calculate(StandardTax, new List<decimal> { 25, 50, 25 });
            decimal priceWithLowerTaxes = PriceCalculator.Calculate(LowerTax, new List<decimal> { 25, 50, 25 });
            System.Console.WriteLine(price);
            System.Console.WriteLine(priceWithLowerTaxes);
        }

        public static decimal StandardTax(decimal priceBeforeTax)
        {
            decimal stateTax = .20M;
            decimal workerMarketTax = .05M;
            decimal someOtherTax = .10M;
            decimal totalTaxMultiplier = 1M + stateTax + workerMarketTax + someOtherTax;

            decimal priceAfterTax = priceBeforeTax * totalTaxMultiplier;
            return priceAfterTax;
        }
        public static decimal LowerTax(decimal priceBeforeTax)
        {
            decimal stateTax = 0M;
            decimal workerMarketTax = .05M;
            decimal someOtherTax = 0M;
            decimal totalTaxMultiplier = 1M + stateTax + workerMarketTax + someOtherTax;

            decimal priceAfterTax = priceBeforeTax * totalTaxMultiplier;
            return priceAfterTax;
        }
    }
}
