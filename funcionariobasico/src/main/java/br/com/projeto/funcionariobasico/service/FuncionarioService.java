/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.funcionariobasico.service;

import br.com.projeto.funcionariobasico.model.Funcionario;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 *
 * @author kevinperera
 */
public class FuncionarioService {
    
    //Função que retorna lista de funcionários
    public List<Funcionario> geraFuncionarios(){
        
        //Array com nomes
        String[] nomes = {"Maria","João","Caio","Miguel","Alice","Heitor","Arthur","Laura","Heloísa","Helena"};
        
        //Array com datas de nascimento
        LocalDate[] dataNascimentos = {LocalDate.of(2000,10 ,18),LocalDate.of(1990,5,12),LocalDate.of(1961,5,2),LocalDate.of(1988,10,14),
                                        LocalDate.of(1995,1,5),LocalDate.of(1999,11,19),LocalDate.of(1993,3,31),LocalDate.of(1994,7,8),
                                        LocalDate.of(2003,5,24),LocalDate.of(1996,9,2)};
        
        //Array com salários
        BigDecimal[] salarios = {BigDecimal.valueOf(2009.44),BigDecimal.valueOf(2284.38),BigDecimal.valueOf(9836.14),BigDecimal.valueOf(19119.88),
                                    BigDecimal.valueOf(2234.68),BigDecimal.valueOf(1582.72),BigDecimal.valueOf(4017.84),BigDecimal.valueOf(3017.45),
                                    BigDecimal.valueOf(1606.85),BigDecimal.valueOf(2799.93)};
        
        //Array com Funções
        String[] funcoes = {"Operador","Operador","Coordenador","Diretor","Recepcionista","Operador",
                            "Contador","Gerente","Eletricista","Gerente"};
        
        //Lista de funcionários
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        
        //Roda a lista de arrays, gera os objetos de funcionarios e adiciona o objeto na lista
        for(int i = 0; i < 10; i++){
            Funcionario fun = new Funcionario(nomes[i],dataNascimentos[i],salarios[i],funcoes[i]);
            
            listaFuncionarios.add(fun);
        }
        
        //Exibe mensagem de usuários gerados
        System.out.println("\n\n Funcionários gerados!");
        
        //Retorna a lista preenchida com todos os funcionários
        return listaFuncionarios;
    }
    
    public void exibirTodosFuncionarios(List<Funcionario>listaFuncionarios){
        System.out.println("\n\n\n+-----------------------------------------------------+");
        System.out.println("| NOME    | NASCIMENTO | SALARIO      | FUNÇÃO        |");
        System.out.println("+-----------------------------------------------------+");

        for (Funcionario funcionario : listaFuncionarios) {
            //Verificação para alinhar a quantidade de espaços
            if (funcionario.getFuncao().trim().length() < 13) {
                int qntLetras = funcionario.getFuncao().length();
                for (int i = 0; i < (13 - qntLetras); i++) {
                    String funcaoEspaco = funcionario.getFuncao() + " ";
                    funcionario.setFuncao(funcaoEspaco);
                }
            }

            if (funcionario.getNome().trim().length() < 7) {
                int qntLetras = funcionario.getNome().length();
                for (int i = 0; i < (7 - qntLetras); i++) {
                    String funcaoEspaco = funcionario.getNome() + " ";
                    funcionario.setNome(funcaoEspaco);
                }
            }

            //Classe para converter decimais 
            Locale localeBR = new Locale("pt", "BR");
            NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

            //Converte decimal para exibir em Reais(R$)
            String salario = dinheiroBR.format(funcionario.getSalario());

            //Condição para verificar se vai ser necessário adicionar um espaço para alinhar a tabela
            if (funcionario.getSalario().doubleValue() < 9999.99) {
                salario += " ";
            }

            //Exibe e monta a tabela
            System.out.println("| " + funcionario.getNome() + " | " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | " + salario + " | " + funcionario.getFuncao() + " |");
            System.out.println("+---------+------------+--------------+---------------+");
        }
    }
    
    public List<Funcionario> removerPorNome(List<Funcionario> lista,String nome){
        
        
        Funcionario funcRemove = null;
        for(Funcionario funcionario: lista){
            //Utilizei a função .trim() para remover os espaços de alinhamento
            if(funcionario.getNome().trim().equals("João")){
                funcRemove = funcionario;
            }
        }
        if(funcRemove == null){
            System.out.println("\n\nUsuário não Encontrado!");
            return lista;
        }else{
            lista.remove(funcRemove);
            System.out.println("\n\nUsuário "+funcRemove.getNome().trim()+" removido com sucesso!");
            return lista;
        }
    }
    
    public List<Funcionario> aumentaSalario(List<Funcionario>lista){
        
        //Roda a lista adicionando os 10% de aumento para cada funcionário
        for(Funcionario funcionario:lista){
            Double aumento = funcionario.getSalario().doubleValue() *0.1;
            funcionario.setSalario(BigDecimal.valueOf(funcionario.getSalario().doubleValue()+aumento));
        }
            System.out.println("\n\nSalários atualizados com sucesso!");
        
        return lista;
    }
    
    public Map<String,List<Funcionario>> exibePorFuncao(String funcao,List<Funcionario> lista){
        
        //Inicia a map 
        Map<String,List<Funcionario>> mapFuncaoFuncionario = new HashMap<>();
        
        //Inicia a lista de funcionários
        List<Funcionario> listaFuncionario = new ArrayList<>();
        
        //Roda a lista criando uma nova lista somente com funcionarios com cargo escolhido
        for(Funcionario funcionario:lista){
            if(funcionario.getFuncao().trim().equals(funcao)){
                listaFuncionario.add(funcionario);
            }
        }
        
        mapFuncaoFuncionario.put(funcao, listaFuncionario);
        
        return mapFuncaoFuncionario;
    }
    
    public List<Funcionario> agruparPorFuncoes(String[] funcoes,List<Funcionario> lista){
        
        List<Funcionario> novaLista = new ArrayList<>();
        
        //Roda a lista de cargos
        for(int i= 0;i<funcoes.length;i++){
            //Roda a lista de funcionário e verifica os que estão no cargo selecionado a adiciona a uma nova lista ordenando por cargos
            for(Funcionario funcionario:lista){
                if(funcionario.getFuncao().trim().equals(funcoes[i])){
                    novaLista.add(funcionario);
                }
            }
        }
        
        System.out.println("\n\nLista de funcionários ordenada por funções!");
        return novaLista;
    }
    
    public void funcionarioPorMes(List<Funcionario> lista){
        
        List<Funcionario> novaLista = new ArrayList<>();
        
        for(Funcionario funcionario : lista){
            //A função getMonthValue retorna um int com o número do mês
            //Ele verifica quais nasceram no mês 10 e 12 e adiciona a uma nova lista
            if (funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12){
                novaLista.add(funcionario);
            }
        }
        
        System.out.println("\n\nFiltro do mês 10 e 12 realizado com sucesso!");
        
        //Ele já exibe a lista somente com os funcionários que nasceram no mes 10 e 12
        exibirTodosFuncionarios(novaLista);
    }
    
    public void funcionarioMaisVelho(List<Funcionario> lista){
        
        //Inicia a variavel de idade
        int idadeMaisVelho = 0;
        //Inicia a variavel de nome
        String nomeMaisVelho = "";
        
        //Pega a data atual
        int anoAtual = LocalDate.now().getYear();
        
        for(Funcionario funcionario:lista){
            //Idade do funcionario
            int idadeFuncionario = anoAtual - funcionario.getDataNascimento().getYear();
            
            //Verifica se a idade do funcionário é maior do que a que está salva como maior idade
            if (idadeFuncionario > idadeMaisVelho) {
                //Caso ele for mais velho do que o que está salvo, ele atualiza a idade e o nome do mais velho
                idadeMaisVelho = idadeFuncionario;
                nomeMaisVelho = funcionario.getNome();
            }else{
                
            }
        }
        
        System.out.println("\n\n-======= Funcionário mais velho =======-");
        System.out.println("NOME: "+nomeMaisVelho+" | IDADE: "+idadeMaisVelho);
    }
    
    
    public List<Funcionario> ordenarAlfabetico(List<Funcionario> lista){
        
        /*
        
        Usa a classe Collections e o metodo "sort" para ordenar, porém por ser uma lista de objeto
        é necessário passar um parametro que informa qual atributo ele irá comparar
        
        Fiz dois exemplos de ordenação o primeiro e mais curto usa o reference do java 8 para indicar 
        o objeto e o atributo de comparação junto ao método "comparing" da classe Comparator.
        Já no outro exemplo eu reescrevo o método compare do Comparator e indico qual o atributo do
        objeto eu quero comparar.
        
        */
        
        Collections.sort(lista, Comparator.comparing(Funcionario::getNome));
        
        /*
        
        Não utilizaria este método por ser mais longo e ter que instanciar um novo objeto de Comparator e
        reescrever a function de comparação dele
        
        Collections.sort(lista, new Comparator<Funcionario>() {
            @Override
            public int compare(Funcionario  func1, Funcionario  func2)
            {

                return  func1.getNome().compareTo(func2.getNome());
            }
        });
        
        */
        
        System.out.println("\n\nLista ordenada com sucesso!");
        return lista;
    }
    
    public void exibeTotalSalarios(List<Funcionario> lista){
        
        //Inicia a variável de valor total dos salários
        double totalSalario = 0;
        
        //Roda a lista e vai encrementando o valor dos salários
        for(Funcionario funcionario:lista){
            totalSalario += funcionario.getSalario().doubleValue();
        }
        
        //Classe para converter decimais 
        Locale localeBR = new Locale("pt", "BR");
        NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

        //Converte decimal para exibir em Reais(R$)
        String salarioConvertido = dinheiroBR.format(totalSalario);
        //Exibe o valor dos salários somados
        System.out.println("\n\nOs salários somados dos funcionários gera o valor de: "+salarioConvertido);
    }
    
    public void salariosMinimosPorFuncionario(List<Funcionario> lista){
        
        //Inicio a lista de Map que contém o funcionário e a quantidade de salários mínimos que ele recebe
        List<Map<String,Integer>> novaLista = new ArrayList<>();
        
        for(Funcionario funcionario:lista){
            //Inicia a map para inserção do valor e funcionario
            Map<String,Integer> map = new HashMap<>();
            
            int salariosMinimos = 0;
            
            //Retorna valor da quantidade de salários mínimos arredondado
            salariosMinimos = (int) (funcionario.getSalario().doubleValue() / 1212);
            map.put(funcionario.getNome(), salariosMinimos);
            novaLista.add(map);
        }
        
        exibeSalariosMinimos(novaLista);
        
    }
    
    public void exibeSalariosMinimos(List<Map<String,Integer>> listaFuncionarios){
        System.out.println("\n\n\n+------------------------------+");
        System.out.println("| NOME    | QTD SALARIO MINIMO |");
        System.out.println("+---------+--------------------+");

        for(Map<String,Integer> map:listaFuncionarios){
            
            for (Map.Entry<String, Integer> item: map.entrySet()) {
                String nome = item.getKey().trim();
                int qtdLetras = nome.length();
                if(nome.length() < 7){
                    for(int i = 0; i < (7 - qtdLetras);i++){
                        nome += " ";
                    }
                }
                
                String salario = "";
                if(item.getValue() > 9){
                    salario = item.getValue().toString() + "                ";
                }else{
                    salario = item.getValue().toString() + "                 ";
                }
                
                System.out.println("| "+nome+" | "+salario+ " |");
                System.out.println("+---------+--------------------+");
            }
        }

            //Exibe e monta a tabela
            //System.out.println("| " + funcionario.getNome() + " | " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | " + salario + " | " + funcionario.getFuncao() + " |");
            //System.out.println("+---------+------------+--------------+---------------+");
        
    }
}
