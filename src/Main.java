import java.util.ArrayList;
import java.util.Scanner;

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
                        // Estrutura de repetição para varrer a lista e imprimir
                        for (Colaborador c : listaColaboradores) {
                            System.out.println("Nome: " + c.nome + " | Registro: " + c.numeroRegistro);
                            System.out.println("Salário Base: R$ " + Colaborador.SALARIO_BASE);

                            if (c.tipoVinculo == 2) {
                                System.out.println("Vendas: R$ " + c.vendas + " | Comissão: " + c.percentualComissao + "%");
                            } else if (c.tipoVinculo == 3) {
                                System.out.println("Peças Produzidas: " + c.quantidadeProduzida + " | Valor por Peça: R$ " + c.valorPeca);
                            }

                            // Chamamos o método de cálculo matemático lá da classe Colaborador
                            System.out.println("Salário Final: R$ " + c.calcularSalario());
                            System.out.println("-----------------------------------");
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n--- EXPORTAÇÃO XML ---");
                    if (listaColaboradores.isEmpty()) {
                        System.out.println("<folhaPagamento>");
                        System.out.println("  <erro>Nenhum dado encontrado</erro>");
                        System.out.println("</folhaPagamento>");
                    } else {
                        // Gerando a visualização no formato de tags XML
                        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                        System.out.println("<folhaPagamento>");
                        for (Colaborador c : listaColaboradores) {
                            System.out.println("  <colaborador>");
                            System.out.println("    <nome>" + c.nome + "</nome>");
                            System.out.println("    <registro>" + c.numeroRegistro + "</registro>");
                            System.out.println("    <tipoVinculo>" + c.tipoVinculo + "</tipoVinculo>");
                            System.out.println("    <salarioFinal>" + c.calcularSalario() + "</salarioFinal>");
                            System.out.println("  </colaborador>");
                        }
                        System.out.println("</folhaPagamento>");
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