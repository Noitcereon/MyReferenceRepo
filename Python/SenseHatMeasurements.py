from sense_hat import SenseHat
import time

# The imports are for the SenseHat Emulator (trinket.io)
s = SenseHat()
s.low_light = False

red = (255, 0, 0)
green = (0, 255, 0)
blue = (0, 0, 255)
yellow = (0, 255, 255)
pink = (180, 80, 80)
white = (255, 255, 255)

# Opgave 1
"""
while(True):
  temperature = s.get_temperature()
  if(temperature > 25):
    s.clear(red)
  elif(temperature < 25 and temperature > 20):
    s.clear(green)
  elif(temperature < 20):
    s.clear(blue)
"""


# Opgave 2
"""
def show_temperature(color):
    s.show_message(temperatureStr, 0.1, color)


while True:
    temperature = s.get_temperature()
    temperatureStr = temperature[:-8]
    if temperature > 25:
        show_temperature(red)
    elif temperature > 20 and temperature < 25:
        show_temperature(green)
    elif temperature < 20:
        show_temperature(blue)
"""

# Opgave 3
def set_color_in_columns(startX, width, color):
    for y in range(8):
        for x in range(startX, startX + width):
            s.set_pixel(x, y, color)


while True:
    humidity = s.get_humidity()
    humidityStr = "Humidity: " + str(humidity)[:-9]
    pressure = s.get_pressure()
    pressureStr = str(pressure)[:-7]
    temperature = s.get_temperature()
    temperatureStr = str(temperature)[:-9]

    if humidity > 50:
        set_color_in_columns(0, 3, blue)
    else:
        set_color_in_columns(0, 3, yellow)
    if pressure > 800:
        set_color_in_columns(3, 2, pink)
    else:
        set_color_in_columns(3, 2, white)
    if temperature > 25:
        set_color_in_columns(5, 3, red)
    elif 20 < temperature < 25:
        set_color_in_columns(5, 3, green)
    elif temperature < 20:
        set_color_in_columns(5, 3, blue)
    time.sleep(1)

