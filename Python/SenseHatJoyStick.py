from sense_hat import SenseHat
import time
s = SenseHat()
s.low_light = False

red = (255, 0, 0)
blue = (0, 0, 255)
nothing = (0, 0, 0)
s.clear()

x = 3
y = 4
# the if statements setting y=y or x=x makes it stop at the sides.
def move_in_direction(event):
    global x
    global y
    
    if event.direction == "up":
        s.set_pixel(x, y, nothing)
        if y == 0:
            y = 7
            #y=y
        else:
            y -= 1
    if event.direction == "right":
        s.set_pixel(x, y, nothing)
        if x == 7:
            x = 0
            #x=x
        else:
            x += 1
    if event.direction == "down":
        s.set_pixel(x, y, nothing)
        if y == 7:
            y = 0
            #y=y
        else:
            y += 1
    if event.direction == "left":
        s.set_pixel(x, y, nothing)
        if x == 0:
            x = 7
            #x=x
        else:
            x -= 1
    if(event.action == "held"):
      s.set_pixel(x,y,blue)
    else:
      s.set_pixel(x, y, red)

while True:
    for event in s.stick.get_events():
        s.stick_direction_any = move_in_direction(event)
        break
