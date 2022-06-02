using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;

namespace PlainTextReceiverUDP
{
    public class UDPReceiver
    {
        public void Start()
        {
            UdpClient client = new UdpClient(7765);
            byte[] byteReceiver; // aka. buffer

            while (true)
            {
                IPEndPoint remoteEndPoint = new IPEndPoint(IPAddress.Any, 0);
                byteReceiver = client.Receive(ref remoteEndPoint);
                string convertedBytes = Encoding.ASCII.GetString(byteReceiver);
                
                Console.WriteLine($"{convertedBytes} from {remoteEndPoint.Address}:{remoteEndPoint.Port}");

                string returnMessage = convertedBytes + " received.";
                byte[] byteReply = Encoding.UTF8.GetBytes(returnMessage.ToCharArray());
                client.Send(byteReply, byteReply.Length, remoteEndPoint);
            }
        }
    }
}
