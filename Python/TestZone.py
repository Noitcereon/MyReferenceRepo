List1 = []
List2 = []
List3 = []

print("Everything divisible by 3 or 5 is not in the list.")
for x in range(30):
    if x % 3 == 0 or x % 5 == 0:
        continue
    List1.append(x)
print(List1)

print("")
print("Hvis x divideret med 3 eller 5 ikke giver 0 er det i listen")
for x in range(30):
    if x % 3 != 0 or x % 5 != 0:
        List2.append(x)
print(List2)
print("")
print("Hvis x divideret med 3 ikke = 0 OG x divideret med 5 ikke = 0 er det i listen")
for x in range(30):
    if x % 3 != 0 and x % 5 != 0:
        List3.append(x)
print(List3)

