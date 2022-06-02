using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;
using ModelLib;

namespace JsonUDPReceiver
{
    public class JsonUDPReceiver
    {
        public void Start()
        {
            UdpClient client = new UdpClient(7765);
            Console.WriteLine("Started");

            while (true)
            {
                string responseMessage;
                IPEndPoint remoteEndPoint = new IPEndPoint(IPAddress.Any, 0); // values are overriden when it receives data.
                byte[] buffer = client.Receive(ref remoteEndPoint);
                string receivedMessage = Encoding.UTF8.GetString(buffer);
                try
                {
                    Car receivedCar = JsonSerializer.Deserialize<Car>(receivedMessage);
                    Console.WriteLine($"This is the converted car: {receivedCar}");
                    responseMessage = $"\"{receivedMessage}\" received";
                }
                catch (Exception ex)
                {
                    responseMessage = "Couldn't convert message to Car";
                    Console.WriteLine(responseMessage);
                    Console.WriteLine(ex.Message);
                }

                byte[] response = Encoding.UTF8.GetBytes(responseMessage);
                client.Send(response, response.Length, remoteEndPoint);
            }
        }
    }
}
