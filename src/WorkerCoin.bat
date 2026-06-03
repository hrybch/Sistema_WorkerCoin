@echo off
chcp 65001 > nul

title Sistema Folha de Pagamento - WorkerCoin

echo Compilando o codigo...

javac -encoding UTF-8 Colaborador.java Main.java

cls
java -Dfile.encoding=UTF-8 Main