# Atribuir valores de escolha do usuário, encontrar o número máximo
# E contar quantas vezes o mesmo aparece
num = []
numSoma = 0
numMax = 0
qtdNumero = int(input("Quantidade: "))
for i in range(1, qtdNumero+1):
    a = int(input(f"Valor de número {i}: "))
    num.append(a)
print(f'Lista {num}')
numMax = max(num)
for i in num:
    if i == numMax:
        numSoma = numSoma + 1
print(f'o Número {max(num)}, aparece {numSoma} vezes')
