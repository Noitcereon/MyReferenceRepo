using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace JsonBroadcastAndReceive
{
    class Program
    {
        static void Main(string[] args)
        {
            Task.Run(BroadcastName);

            Task.Run(PickupBroadcast);

            Console.ReadLine();
        }

        private static void BroadcastName()
        {
            while (true)
            {
                UdpClient client = new UdpClient();
                byte[] bytesToSend = Encoding.UTF8.GetBytes("Thomas");

                IPEndPoint remoteEndPoint = new IPEndPoint(IPAddress.Broadcast, 11001);
                client.Send(bytesToSend, bytesToSend.Length, remoteEndPoint);
                Thread.Sleep(10000);
            }
        }

        private static void PickupBroadcast()
        {
            UdpClient client = new UdpClient(11001);
            List<string> receivedNames = new List<string>();

            while (true)
            {
                IPEndPoint remoteEndPoint = new IPEndPoint(IPAddress.Any, 0);
                var receivedBytes = client.Receive(ref remoteEndPoint);

                string receivedMessage = Encoding.UTF8.GetString(receivedBytes);
                if (!receivedNames.Contains(receivedMessage))
                {
                    receivedNames.Add(receivedMessage);
                    Console.WriteLine($"Added {receivedMessage} to list");
                }
                else
                {
                    Console.WriteLine($"Duplicate received, but ignored.");
                }
            }
        }
    }
}
