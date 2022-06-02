using System;

namespace PlainTextReceiverUDP
{
    class Program
    {
        static void Main(string[] args)
        {
            UDPReceiver udpClient = new UDPReceiver();
            udpClient.Start();
        }
    }
}
