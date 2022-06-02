using System;

namespace JsonUDPSender
{
    class Program
    {
        static void Main(string[] args)
        {
            JsonUDPSender sender = new JsonUDPSender();
            sender.Start();
            Console.ReadLine();
        }
    }
}
