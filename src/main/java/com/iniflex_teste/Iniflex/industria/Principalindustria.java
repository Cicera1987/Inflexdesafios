package com.iniflex_teste.Iniflex.industria;

import com.iniflex_teste.Iniflex.industria.model.Funcionario;
import com.iniflex_teste.Iniflex.industria.service.FuncionarioService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Principalindustria {
  public static void main(String[] args) {
    FuncionarioService service = new FuncionarioService();

    // 3.1 – Inserir todos os funcionários (exemplo de dados fictícios)
    service.adicionar(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
    service.adicionar(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
    service.adicionar(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
    service.adicionar(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
    service.adicionar(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
    service.adicionar(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
    service.adicionar(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
    service.adicionar(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
    service.adicionar(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
    service.adicionar(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

    // 3.2 – Remover o funcionário “João”
    service.removerPorNome("João");

    // 3.3 – Imprimir todos os funcionários (formatado)
    System.out.println("\nTodos Funcionários");
    service.imprimirTodos();

    // 3.4 – Aumentar salários em 10%
    service.aplicarAumento(new BigDecimal("0.10"));

    // 3.5 e 3.6 – Agrupar por função e imprimir
    System.out.println("\nFuncionários agrupados por função");
    System.out.println("-------------------------------------------------------------");
    service.imprimirAgrupadoPorFuncao();

    // 3.8 – Funcionários com aniversário nos meses 10 e 12
    System.out.println("\nAniversariantes em Outubro e Dezembro");
    service.imprimirAniversariantes(10, 12);

    // 3.9 – Funcionário mais velho
    System.out.println("\nFuncionário mais velho");
    var maisVelho = service.getFuncionarioMaisVelho();
    if (maisVelho != null) {
      System.out.printf("Nome: %s | Idade: %d anos%n",
            maisVelho.getNome(), maisVelho.getIdade());
    }

    // 3.10 – Lista de funcionários por ordem alfabética
    System.out.println("\nFuncionários em ordem alfabética");
    service.imprimirOrdemAlfabetica();

    // 3.11 – Total dos salários
    System.out.println("\nTotal de salários");
    System.out.println("R$ " + service.getTotalSalarios());

    // 3.12 – Quantos salários mínimos cada um ganha
    System.out.println("\nSalários mínimos por funcionário");
    service.imprimirSalariosMinimos(new BigDecimal("1212.00"));
  }
}
