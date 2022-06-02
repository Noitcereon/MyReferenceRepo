using System;

namespace JsonUDPReceiver
{
    class Program
    {
        static void Main(string[] args)
        {
            JsonUDPReceiver sender = new JsonUDPReceiver();
            sender.Start();
            Console.ReadLine();
        }
    }
}
