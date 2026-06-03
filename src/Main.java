import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Colaborador> listaColaboradores = new ArrayList<>();
        int opcaoMenu;

        // Menu Principal
        do {
            System.out.println("\n--- WORKERCOIN ---");
            System.out.println("Escolha uma opção:\n");
            System.out.println("1. Cadastro");
            System.out.println("2. Gerar Folha de Pagamento");
            System.out.println("3. Gerar XML da Folha de Pagamento");
            System.out.println("4. Sair");

            opcaoMenu = scanner.nextInt();
            scanner.nextLine(); // Limpando o scanner

            switch (opcaoMenu) {
                case 1:
                    int opcaoCadastro;
                    // Submenu Cadastro de Funcionários
                    do {
                        System.out.println("\n--- MENU DE CADASTRO ---");
                        System.out.println("Escolha o tipo de vínculo:\n");
                        System.out.println("1. Funcionário Padrão");
                        System.out.println("2. Funcionário Comissionado");
                        System.out.println("3. Funcionário de Produção");
                        System.out.println("4. Menu Anterior");

                        opcaoCadastro = scanner.nextInt();
                        scanner.nextLine();

                        // Verificando o input
                        if (opcaoCadastro >= 1 && opcaoCadastro <= 3) {
                            System.out.print("Nome do colaborador: ");
                            String nome = scanner.nextLine();
                            System.out.print("Número do registro: ");
                            int registro = scanner.nextInt();

                            Colaborador novoColab = new Colaborador(nome, registro, opcaoCadastro);

                            if (opcaoCadastro == 2) {
                                System.out.print("Valor total das vendas: R$ ");
                                novoColab.vendas = scanner.nextDouble();
                                System.out.print("Percentual da comissão (%): ");
                                novoColab.percentualComissao = scanner.nextDouble();
                            } else if (opcaoCadastro == 3) {
                                System.out.print("Quantidade de peças produzidas: ");
                                novoColab.quantidadeProduzida = scanner.nextInt();
                                System.out.print("Valor por peça: R$ ");
                                novoColab.valorPeca = scanner.nextDouble();
                            }

                            // Envia pra arraylist
                            listaColaboradores.add(novoColab);
                            System.out.println("Cadastro realizado com sucesso!");

                        } else if (opcaoCadastro != 4) {
                            System.out.println("Opção inválida! Tente novamente!");
                        }
                    } while (opcaoCadastro != 4);
                    break;

                case 2:
                    System.out.println("\n--- FOLHA DE PAGAMENTO ---");
                    if (listaColaboradores.isEmpty()) {
                        System.out.println("Nenhum colaborador cadastrado!");
                    } else {
                        // Sweep & print
                        for (Colaborador c : listaColaboradores) {
                            System.out.println("Nome: " + c.nome + " | Registro: " + c.numeroRegistro);
                            System.out.println("Salário Base: R$ " + Colaborador.SALARIO_BASE);

                            if (c.tipoVinculo == 2) {
                                System.out.println("Vendas: R$ " + c.vendas + " | Comissão: " + c.percentualComissao + "%");
                            } else if (c.tipoVinculo == 3) {
                                System.out.println("Peças Produzidas: " + c.quantidadeProduzida + " | Valor por Peça: R$ " + c.valorPeca);
                            }

                            // Método de Cálculo da classe Colaborador
                            System.out.println("Salário Final: R$ " + c.calcularSalario());
                            System.out.println("-----------------------------------");
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n--- GERANDO ARQUIVO XML ---");
                    if (listaColaboradores.isEmpty()) {
                        System.out.println("Nenhum colaborador cadastrado!");
                    } else {
                        // Nome do arquivo que será criado
                        String nomeArquivo = "folha_pagamento.xml";

                        // O 'try-with-resources' abre o arquivo e garante que ele será FECHADO automaticamente no final
                        try (PrintWriter escritor = new PrintWriter(new FileWriter(nomeArquivo))) {

                            // Começamos a gravar as tags dentro do arquivo usando o 'escritor.println'
                            escritor.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                            escritor.println("<folhaPagamento>");

                            for (Colaborador c : listaColaboradores) {
                                escritor.println("  <colaborador>");
                                escritor.println("    <nome>" + c.nome + "</nome>");
                                escritor.println("    <registro>" + c.numeroRegistro + "</registro>");
                                escritor.println("    <tipoVinculo>" + c.tipoVinculo + "</tipoVinculo>");
                                escritor.println("    <salarioFinal>" + c.calcularSalario() + "</salarioFinal>");
                                escritor.println("  </colaborador>");
                            }

                            escritor.println("</folhaPagamento>");

                            // Se chegou até aqui, deu tudo certo!
                            System.out.println("O arquivo '" + nomeArquivo + "' foi gerado com sucesso!");
                            System.out.println("Procure por ele na mesma pasta onde estão os arquivos do seu sistema.");

                        } catch (IOException e) {
                            // Se o Windows bloquear a criação do arquivo ou der ruim no disco, cai aqui
                            System.out.println("Não foi possível gerar o arquivo XML.");
                            System.out.println("Erro técnico: " + e.getMessage());
                        }
                    }
                    break;

                case 4:
                    System.out.println("Encerrando sistema.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente!");
            }
        } while (opcaoMenu != 4);
    }
}