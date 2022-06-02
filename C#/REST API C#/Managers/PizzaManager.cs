using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using PracticeRestLib;

namespace PracticeRestService.Managers
{
    public class PizzaManager
    {
        private static readonly List<Pizza> Pizzas = new List<Pizza>
        {
            new Pizza(1, "First pizza", false, Convert.ToDecimal(50.95)),
            new Pizza(2, "Second pizza", true, Convert.ToDecimal(69.95)),
            new Pizza(3, "Third pizza", true, Convert.ToDecimal(90.95)),
            new Pizza(4, "Fourth pizza", true, Convert.ToDecimal(79.95)),
            new Pizza(5, "Fifth pizza", false, Convert.ToDecimal(49.95)),
            new Pizza(6, "Sixth pizza", false, Convert.ToDecimal(30.0)),
        };

        public IList<Pizza> GetAll()
        {
            return Pizzas;
        }

        public Pizza GetOne(int number)
        {
            return Pizzas.Find((pizza) => pizza.Number == number);
        }

        public IEnumerable<Pizza> GetFromSubstring(string substring)
        {
            return Pizzas.FindAll(x => x.Description.ToLower().Contains(substring));
        }

        public String Post(Pizza newPizza)
        {
            Pizzas.Add(newPizza);
            return $"{ newPizza } added.";
        }

        public String Put(Pizza updatedPizza, int number)
        {
            var oldPizza = Pizzas.First(x => x.Number == number);
            oldPizza.Description = updatedPizza.Description;
            oldPizza.FamilySize = updatedPizza.FamilySize;
            oldPizza.Price = updatedPizza.Price;

            return $"Updated pizza Nr. {updatedPizza.Number}";
        }

        public String Delete(int number)
        {
            Pizza pizzaToDelete = GetOne(number);
            Pizzas.Remove(pizzaToDelete);

            return $"Deleted pizza: {pizzaToDelete}";
        }
    }
}
