# Script para manipulação de arquivos TXT
# Leitura, escrita, adição de escrita e reset de arquivo.
import os

while True:
    
    print(" 1 - Leitura\n 2 - Escrita\n 3 - Adicionar Texto\n 4 - Resetar Texto")
    opcao = int(input())

    def escrita():

        arquivo = open(os.path.join('Teste001.txt'), 'w')
        texto=input("Digite o texto\n")

        for i in texto.split():
            arquivo.write(i+ ' ')

        arquivo.close()
    os.system('cls') or None

    def leitura():
    # Estudar tratamento para arquivos inexistentes!!
        arquivo = open("Teste001.txt", 'r')
        conteudo = arquivo.read()
        print('\n\n'+conteudo)
        if len(conteudo) < 1:
            print("Arquivo sem conteúdo escrito")
        arquivo.close()
    os.system('cls') or None
    
    def adicionar():
        arquivo = open("Teste001.txt", 'a')
        print("-- Escrita Atual --")
        leitura()

        print("-- Adicionando texto --")
        texto = input()
        for i in texto.split():
            arquivo.write(i+ ' ')
        arquivo.close()
    os.system('cls') or None

    def resetar():

        arquivo = open("Teste001.txt", 'w')
        arquivo.write('')
        arquivo.close()
    os.system('cls') or None

    if opcao == 1:
        print("-- Leitura de Arquivo --")
        leitura()
    elif opcao == 2:
        print("-- Escrita de Arquivo --")
        escrita()
    elif opcao == 3:
        print("-- Adicionar conteúdo ao final do arquivo --")
        adicionar()
    elif opcao == 4:
        print("-- Resetar conteúdo do arquivo")
        resetar()
    else:
        print("Ocorreu um erro em sua escolha!!\n Teste novamente")
    
    
    
    opcao = input("Dejesa sair? [S \ N]: ")
    if opcao.upper() == 'S':
        break
    else:
        continue

print("\n\nPrograma encerrado!!")
os.system('cls') or None
    
