package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidades.Acionista;
import entidades.Funcionario;

public class Principal {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Acionista> acionistas = new ArrayList<Acionista>();
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        boolean sair = false;
        String CPF = null;
        boolean cpfCadastrado = false;

        while (!sair) {
            System.out.println("Escolha uma opção");
            System.out.println("1- Cadastrar Acionista");
            System.out.println("2- Editar Acionista");
            System.out.println("3- Cadastrar Funcionario");
            System.out.println("4- Editar Funcionario");
            System.out.println("5- Fechar Programa");
            System.out.print("Digite o número da opção: ");
            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    System.out.print("Nome: ");
                    String nomeAcionista = sc.nextLine();
                    System.out.print("Endereço: ");
                    String enderecoAcionista = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpfAcionista = sc.nextLine();
                    if(cpfAcionista.length() != 11) {
                        System.out.println("Acionista não cadastrado! CPF Inválido");
                        break;
                    }
                    System.out.println("Data de nascimento");
                    System.out.print("Dia: ");
                    int diaAcionista = sc.nextInt();
                    System.out.print("Mês: ");
                    int mesAcionista = sc.nextInt();
                    System.out.print("Ano: ");
                    int anoAcionista = sc.nextInt();
                    sc.nextLine();
                    boolean dataValida = verificarDataNascimento(diaAcionista, mesAcionista, anoAcionista);
                    String dataNascimentoAcionista = "Não Setada";
                    if (!dataValida) {
                        dataNascimentoAcionista = diaAcionista + "/" + mesAcionista + "/" + anoAcionista;
                        System.out.println("Acionista não cadastrado! Data de nascimento inválida");
                    } else {
                        dataNascimentoAcionista = diaAcionista + "/" + mesAcionista + "/" + anoAcionista;
                    }
                    System.out.print("Tipo de Ação (Familiar/Individual): ");
                    String tipoAcao = sc.nextLine();
                    int quantidadeDependentes = 0;
                    if (tipoAcao.equals("Familiar") || tipoAcao.equals("Individual")) {
                        if (tipoAcao.equals("Familiar")) {
                            System.out.print("Quantidade de Dependentes: ");
                            quantidadeDependentes = sc.nextInt();
                            sc.nextLine();
                        } else {
                            quantidadeDependentes = 0;
                        }
                        Acionista acionista = new Acionista(nomeAcionista, enderecoAcionista, cpfAcionista, dataNascimentoAcionista, tipoAcao, quantidadeDependentes);
                        acionistas.add(acionista);
                    System.out.println("Acionista cadastrado com sucesso!");
                    } else {
                        System.out.println("Acionista não cadastrado! Tipo de ação Inválido");
                    }
                    break;
                case 2:
                    System.out.print("Digite o CPF do acionista: ");
                    CPF = sc.nextLine();
                    cpfCadastrado = false;

                    for (Acionista acionistaItem : acionistas) {
                        if (acionistaItem.getCpf().equals(CPF)) {
                            cpfCadastrado = true;
                            System.out.println(acionistaItem);
                            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
                            System.out.println("Menu de Opções");
                            System.out.println("1- Fazer Pagamento");
                            System.out.println("2- Aumentar parcelas em atraso");
                            System.out.println("3- Cancelar ação");
                            System.out.println("Escolha uma opção:");
                            int opcao = sc.nextInt();
                            sc.nextLine();
                            switch (opcao) {
                                case 1:
                                    System.out.printf("%s possui %d parcelas à pagar\n", acionistaItem.getNome(), acionistaItem.getTempo_atraso());
                                    System.out.print("Digite a quantidade de parcelas a serem pagas: ");
                                    int quantidadeParcelas = sc.nextInt();
                                    sc.nextLine();
                                    acionistaItem.fazer_pagamento(quantidadeParcelas);
                                    break;
                                case 2:
                                    System.out.print("Digite a quantidade de parcelas em atraso a serem adicionadas: ");
                                    int parcelasAtraso = sc.nextInt();
                                    sc.nextLine();
                                    acionistaItem.setTempo_atraso(acionistaItem.getTempo_atraso() + parcelasAtraso);
                                    acionistaItem.setEm_atraso(true);
                                    break;
                                case 3:
                                    acionistas.remove(acionistaItem);
                                    System.out.println("Ação cancelada com sucesso!");
                                    break;
                                default:
                                    System.out.println("Opção inválida!");
                                    break;
                            }
                            break;
                        }
                    }

                    if (!cpfCadastrado) {
                        System.out.println("CPF não encontrado!");
                    }
                    break;
                case 3:
                    System.out.print("Nome: ");
                    String nomeFuncionario = sc.nextLine();
                    System.out.print("Endereço: ");
                    String enderecoFuncionario = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpfFuncionario = sc.nextLine();
                    if(cpfFuncionario.length() != 11) {
                        System.out.println("Funcionário não cadastrado! CPF Inválido");
                        break;
                    }
                    System.out.println("Data de nascimento");
                    System.out.print("Dia: ");
                    int diaFuncionario = sc.nextInt();
                    System.out.print("Mês: ");
                    int mesFuncionario = sc.nextInt();
                    System.out.print("Ano: ");
                    int anoFuncionario = sc.nextInt();
                    sc.nextLine();
                    String dataNascimentoFuncionario = "Não Setada";
                    boolean dataValidaFuncionario = verificarDataNascimento(diaFuncionario, mesFuncionario, anoFuncionario);
                    if (!dataValidaFuncionario) {
                        dataNascimentoFuncionario = diaFuncionario + "/" + mesFuncionario + "/" + anoFuncionario;
                        System.out.println("Funcionário não cadastrado! Data de nascimento inválida");
                    } else {
                        dataNascimentoFuncionario = diaFuncionario + "/" + mesFuncionario + "/" + anoFuncionario;
                    }
                    System.out.print("Salário: ");
                    double salario = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Função: ");
                    String funcao = sc.nextLine();

                    Funcionario funcionario = new Funcionario(nomeFuncionario, enderecoFuncionario, cpfFuncionario, dataNascimentoFuncionario, salario, funcao);
                    funcionarios.add(funcionario);

                    System.out.println("Funcionário cadastrado com sucesso!");
                    break;
                case 4:
                    System.out.println("Editar Funcionario");
                    System.out.println("CPF do Funcionario: ");
                    CPF = sc.nextLine();
                    cpfCadastrado = false;
                    for (Funcionario funcionarioItem : funcionarios) {
                        if (funcionarioItem.getCpf().equals(CPF)) {

                            cpfCadastrado = true;
                            System.out.println(funcionarioItem);
                            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
                            System.out.println("Escolha uma opção");
                            System.out.println("1- Mudar função");
                            System.out.println("2- Aumentar Salário");
                            System.out.println("3- Demitir Funcionário");
                            System.out.print("Digite o número da opção: ");
                            int opcaoFuncionario = sc.nextInt();
                            sc.nextLine();

                            switch (opcaoFuncionario) {
                                case 1:
                                    System.out.println("Nova função: ");
                                    String novaFuncao = sc.nextLine();
                                    funcionarioItem.setFuncao(novaFuncao);
                                    System.out.println("Função alterada com sucesso!");
                                    break;
                                case 2:
                                    System.out.println("Aumento salarial (em porcentagem): ");
                                    double aumentoPercentual = sc.nextDouble();
                                    sc.nextLine();
                                    double novoSalario = funcionarioItem.getSalario() * (1 + (aumentoPercentual / 100));
                                    funcionarioItem.setSalario(novoSalario);
                                    System.out.println("Salário atualizado com sucesso!");
                                    break;
                                case 3:
                                    funcionarios.remove(funcionarioItem);
                                    System.out.println("Funcionário demitido com sucesso!");
                                    break;
                                default:
                                    System.out.println("Opção inválida!");
                                    break;
                            }
                            break;
                        }
                    }
                    if (!cpfCadastrado) {
                        System.out.println("Funcionário não encontrado!");
                    }
                    break;

                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
    public static boolean verificarDataNascimento(int dia, int mes, int ano) {
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false); 
        cal.set(Calendar.YEAR, ano);
        cal.set(Calendar.MONTH, mes - 1); 
        cal.set(Calendar.DAY_OF_MONTH, dia);

        try {
            cal.getTime(); 
            return true; 
        } catch (Exception e) {
            return false;
        }
    }
}
