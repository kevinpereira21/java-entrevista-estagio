/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package br.com.projeto.funcionariobasico;

import br.com.projeto.funcionariobasico.model.Funcionario;
import br.com.projeto.funcionariobasico.service.FuncionarioService;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author kevinperera
 */
public class Funcionariobasico {

    public static void main(String[] args) throws IOException, InterruptedException {
        //Cria o objeto do scanner
        Scanner sc = new Scanner(System.in);
        
        //Instancia classe de serviço
        FuncionarioService funcionarioService = new FuncionarioService();
        
        //Lista de funcionários
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        
        //Array contendo os cargos dos funcionários
        String[] funcoes = {"Operador","Recepcionista","Eletricista","Contador","Coordenador","Gerente","Diretor"};
        
        int opcao;
        do {            
            
            System.out.println("\n\n\n-===== Selecione uma opção =====-");
            System.out.println("1. Inserir todos os funcionários na lista");
            System.out.println("2. Ver lista de funcionários");
            System.out.println("3. Remover o João");
            System.out.println("4. Aumentar 10% do salário de cada funcionário");
            System.out.println("5. Agrupar funcionários por função");
            System.out.println("6. Exibir funcionários agrupados por funções");
            System.out.println("7. Exibir funcionários que fazem aniversário no mês 10 e 12");
            System.out.println("8. Exibir funcionário mais velho");
            System.out.println("9. Ordenar lista por ordem alfabética");
            System.out.println("10. Exibir o total de salário dos funcionários");
            System.out.println("11. Informar quantos salários mínimos cada funcionário ganha");
            System.out.println("12. Fechar Programa");
            System.out.println("Digite uma opção: ");
            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    listaFuncionarios = funcionarioService.geraFuncionarios();
                    break;
                //======================================================================//    
                case 2:
                    if(listaFuncionarios.isEmpty()){
                        System.out.println("\n\nA lista de funcionários esta vazia!");
                    }else{
                        funcionarioService.exibirTodosFuncionarios(listaFuncionarios);
                    }
                    break;
                //======================================================================//     
                case 3:
                    if(listaFuncionarios.isEmpty()){
                        System.out.println("\n\nA lista de funcionários esta vazia!");
                    }else{
                        listaFuncionarios = funcionarioService.removerPorNome(listaFuncionarios, "João");
                    }
                    break;
                //======================================================================// 
                case 4:
                    if(listaFuncionarios.isEmpty()){
                        System.out.println("\n\nA lista de funcionários esta vazia!");
                    }else{
                        listaFuncionarios = funcionarioService.aumentaSalario(listaFuncionarios);   
                    }
                    break;
                //======================================================================//     
                case 5:
                    
                        int opcaoFunc;
                        System.out.println("\n\n\n-===== Selecione a função para ver os funcionários =====-");
                        System.out.println("1. Operador");
                        System.out.println("2. Recepcionista");
                        System.out.println("3. Eletricista");
                        System.out.println("4. Contador");
                        System.out.println("5. Coordenador");
                        System.out.println("6. Gerente");
                        System.out.println("7. Diretor");
                        System.out.println("9. Voltar ao menu principal");
                        opcaoFunc = sc.nextInt();
                        
                        
                        Map<String,List<Funcionario>> funcaoResultado = funcionarioService.exibePorFuncao(funcoes[opcaoFunc-1],listaFuncionarios);
                        
                        for (Map.Entry<String, List<Funcionario>> map : funcaoResultado.entrySet()) {
                            funcionarioService.exibirTodosFuncionarios(map.getValue());
                        }
                    break;
                //======================================================================//     
                case 6:
                    
                    if (listaFuncionarios.isEmpty()) {
                        System.out.println("\n\nA lista de funcionários está vazia!");
                    }else{
                        listaFuncionarios = funcionarioService.agruparPorFuncoes(funcoes, listaFuncionarios);
                        funcionarioService.exibirTodosFuncionarios(listaFuncionarios);
                    }
                    
                    break;
                //======================================================================//     
                case 7:
                    if(listaFuncionarios.isEmpty()){
                        System.out.println("\n\nA lista de funcionários está vazia!");
                    }else{
                        funcionarioService.funcionarioPorMes(listaFuncionarios);
                    }
                    break;
                //======================================================================//
                case 8:
                    if(listaFuncionarios.isEmpty()){
                        System.out.println("\n\nA lista de funcionários está vazia!");
                    }else{
                        funcionarioService.funcionarioMaisVelho(listaFuncionarios);
                    }
                    break;
                //======================================================================//  
                case 9:
                    if(listaFuncionarios.isEmpty()){
                        System.out.println("\n\nA lista de funcionários está vazia!");
                    }else{
                        listaFuncionarios = funcionarioService.ordenarAlfabetico(listaFuncionarios);
                        funcionarioService.exibirTodosFuncionarios(listaFuncionarios);
                    }
                    break;
                //======================================================================//   
                case 10:
                    if(listaFuncionarios.isEmpty()){
                        System.out.println("\n\nA lista de funcionários está vazia!");
                    }else{
                        funcionarioService.exibeTotalSalarios(listaFuncionarios);
                    }
                    break;
                //======================================================================//     
                case 11:
                    if(listaFuncionarios.isEmpty()){
                        System.out.println("\n\nA lista de funcionários está vazia!");
                    }else{
                        funcionarioService.salariosMinimosPorFuncionario(listaFuncionarios);
                    }
                    break;
                //======================================================================//     
                case 12:
                    System.out.println("Fechando o programa...");
                    break;
                //======================================================================//     
                default:
                    System.out.println("Está opção não é valida!");
                    break;
            }
        } while (opcao != 12);
    }
}
