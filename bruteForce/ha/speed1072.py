line = input()
while (line != ""):
    xy= line.split()
    x=int(xy[0])
    y=int(xy[1])
    z=int(y/x*100)
    plus =0
    newz = z
    while (z==newz):
        plus+=1
        x+=plus
        y+=plus
        newz =int(y/x*100)
    print(plus)
    line =input()
