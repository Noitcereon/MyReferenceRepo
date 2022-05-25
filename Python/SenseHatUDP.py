from socket import *
import time
from datetime import datetime
from sense_hat import SenseHat


BROADCAST_TO_PORT = 9001

socket = socket(AF_INET, SOCK_DGRAM)

socket.setsockopt(SOL_SOCKET, SO_BROADCAST, 1)
sense = SenseHat()
while True:
	data = str(sense.get_temperature())[:-8]
	socket.sendto(bytes(data, "UTF-8"), ('<broadcast>', BROADCAST_TO_PORT))

	datetimeString = str(datetime.now().hour) + ":" + str(datetime.now().minute) + ":" + str(datetime.now().second)
	print(datetimeString + ": " + data)
	time.sleep(5)
