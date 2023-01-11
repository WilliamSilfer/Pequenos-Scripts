# Gerar 50 números aleatórios sem repetição
from random import randint
a = set()
while len(a) < 50:
    a.add(randint(0, 10000))
print(a)