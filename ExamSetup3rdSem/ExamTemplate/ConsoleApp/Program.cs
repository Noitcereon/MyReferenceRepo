using System;

namespace ConsoleApp
{
    internal class Program
    {
        private static void Main(string[] args)
        {
            Console.WriteLine("Starting Console App!");
            Worker worker = new Worker();
            worker.Start();
            Console.ReadLine();
        }
    }
}
