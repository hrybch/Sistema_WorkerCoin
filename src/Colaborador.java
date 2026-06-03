public class Colaborador {
    // Constante do Salário
    public static final double SALARIO_BASE = 2000.00;

    //  Declarando variáveis
    String nome;
    int numeroRegistro;
    int tipoVinculo;    // 1 - padrão, 2 - comissão, 3 - produção
    double vendas;
    double percentualComissao;
    double valorPeca;
    double quantidadeProduzida;

    // Struct básico
    public Colaborador(String nome, int numeroRegistro, int tipoVinculo) {
        this.nome = nome;
        this.numeroRegistro = numeroRegistro;
        this.tipoVinculo = tipoVinculo;
    }

    // Informações e cálculos
    public double calcularSalario() {
        double salarioFinal = SALARIO_BASE;

        // Estrutura condicional
        switch (tipoVinculo) {
            case 1: // Colaborador recebe só o salário
                break;
            case 2: // Colaborador recebe salário + comissão
                double comissao = (vendas * percentualComissao) / 100.0;
                salarioFinal = salarioFinal + comissao;
                break;
            case 3: // Colaborador recebe salário + bônus de produtividade
                double bonus = valorPeca * quantidadeProduzida;
                salarioFinal = salarioFinal + bonus;
                break;
            default: // Caso nenhum dos vínculos seja selecionado
                System.out.println("Tipo de vínculo não existente!");
        }
        return salarioFinal;
    }
}