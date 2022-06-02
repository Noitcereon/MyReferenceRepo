using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using ModelLib;

namespace PlainUDPSender
{
    public class UDPSender
    {
        public void Start()
        {
            UdpClient client = new UdpClient();
            byte[] bytesToSend; 

            Car car = new Car("Tesla", "Car", "UZ323H");
            string carString = car.ToString();
            bytesToSend = Encoding.UTF8.GetBytes(carString.ToCharArray());

            IPEndPoint remoteEndPoint = new IPEndPoint(IPAddress.Loopback, 7765);
            client.Send(bytesToSend, bytesToSend.Length, remoteEndPoint);
            Console.WriteLine("Message sent.");
        }

    }
}
