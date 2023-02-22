/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atividade02;

/**
 *
 * @author NOTE
 */
import java.util.*;
public class JogoDaVelha {
    private Tabuleiro vetor[] =  new Tabuleiro[3];
    boolean ehBot = false;
    char simboloJogador1, simboloJogador2;
    String jogador1, jogador2;
    String ganhador = " ";
    int casasPreenchidas = 0;
    
    public JogoDaVelha(){
        for(int i = 0; i < 3; i ++)
            this.vetor[i] = new Tabuleiro();
    }
    
    private void inicializarJogo(String modo){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do jogador 1: ");
        jogador1 = sc.nextLine();
        System.out.println("Digite o simbolo que deseja utilizar durante o jogo:");
        simboloJogador1 = sc.next().charAt(0);
        if(modo.charAt(2) == 'B'){
            ehBot = true;
            simboloJogador2 = 'B';
            jogador2 = "Boot";
        }
        else{
            Scanner sc2 = new Scanner(System.in);
            System.out.println("Digite o nome do jogador 2:");
            jogador2 = sc2.nextLine();
            System.out.println("Digite o simbolo que deseja utilizar durante o jogo:");
            simboloJogador2 = sc.next().charAt(0);
        }
    }
   
    public void jogar(String modo){
        inicializarJogo(modo);
        String jogada;
        Scanner sc = new Scanner(System.in);
        imprime();
        while(ganhador.equals(" ") && casasPreenchidas != 27){
            casasPreenchidas += 2;
            System.out.println("====> Turno do" + jogador1);
            System.out.println("Digite a posicao que deseja jogar no formato (linha, coluna, camada):");
            jogada = sc.nextLine();
            if(jogada.charAt(5) ==  '0')
                vetor[0].alteraValor(Character.getNumericValue(jogada.charAt(1)),Character.getNumericValue(jogada.charAt(3)),simboloJogador1);
            else if(jogada.charAt(5)== '1')
                vetor[1].alteraValor(Character.getNumericValue(jogada.charAt(1)),Character.getNumericValue(jogada.charAt(3)),simboloJogador1);
            else 
                vetor[2].alteraValor(Character.getNumericValue(jogada.charAt(1)),Character.getNumericValue(jogada.charAt(3)),simboloJogador1);
            imprime();
            verSeGanhou();
            if(!ehBot){
                System.out.println("====> Turno do" + jogador2);
                System.out.println("Digite a posicao que deseja jogar no formato (linha, coluna, camada):");
                jogada = sc.nextLine();
                if (jogada.charAt(5) == '0') 
                    vetor[0].alteraValor(Character.getNumericValue(jogada.charAt(1)), Character.getNumericValue(jogada.charAt(3)), simboloJogador2);
                else if (jogada.charAt(5) == '1')
                    vetor[1].alteraValor(Character.getNumericValue(jogada.charAt(1)), Character.getNumericValue(jogada.charAt(3)), simboloJogador2);
                else
                    vetor[2].alteraValor(Character.getNumericValue(jogada.charAt(1)), Character.getNumericValue(jogada.charAt(3)), simboloJogador2);
            }
            else
                jogadaAleatoria();
            verSeGanhou();
            imprime();
        }
        if(ganhador.equals(jogador1))
            System.out.println("Parabens " + jogador1);
        else if(ganhador.equals(jogador2))
            System.out.println("Parabens " + jogador2);
        if(casasPreenchidas == 27)
            System.out.println("Empate");
    }
    
    public void imprime(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j ++){
                vetor[j].imprimeMatriz(i);
                System.out.print("   ");
            }
            System.out.println();
        }
    }
    
    private void jogadaAleatoria(){
        int linha,coluna,camada;
        Random gerador = new Random();
        linha = gerador.nextInt(3);
        coluna = gerador.nextInt(3);
        camada = gerador.nextInt(3);
        vetor[camada].alteraValor(linha,coluna,'B');        
    }
    private int verSeGanhou(){
        for(int a = 0; a < 3; a++){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(vetor[a].getValor(i, j)!= '-'){
                        olharColunas(a,i,j,vetor[a].getValor(i, j));
                        if(ganhador.equals(" "))
                            olharLinhas(a,i,j,vetor[a].getValor(i, j));
                        if(ganhador.equals(" "))
                            olharDiagonais(a,i,j,vetor[a].getValor(i, j));
                    }
                }
            }
        }
         return 0;
    }
    
    private void olharColunas(int camada,int linha, int coluna, char valor){
        if(linha == 0){
            if(vetor[camada].getValor(linha+1, coluna)== valor && vetor[camada].getValor(linha+2, coluna) == valor){
                if(valor == simboloJogador1)
                    ganhador = jogador1;
                else 
                    ganhador = jogador2;
            }
            else if(camada == 0){
                if(vetor[camada+1].getValor(linha+1, coluna)== valor && vetor[camada+2].getValor(linha+2, coluna) == valor||vetor[camada+1].getValor(linha+2, coluna)== valor && vetor[camada+2].getValor(linha+1, coluna) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 1){
                if(vetor[camada-1].getValor(linha+1, coluna)== valor && vetor[camada+1].getValor(linha+2, coluna) == valor||vetor[camada-1].getValor(linha+2, coluna)== valor && vetor[camada+1].getValor(linha+1, coluna) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else 
                        ganhador = jogador2;
                }
            }else if(camada == 2){
                if(vetor[camada-2].getValor(linha+1, coluna)== valor && vetor[camada-1].getValor(linha+2, coluna) == valor||vetor[camada-2].getValor(linha+2, coluna)== valor && vetor[camada-1].getValor(linha+1, coluna) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }
        }else if(linha == 1){
            if(vetor[camada].getValor(linha-1, coluna)== valor && vetor[camada].getValor(linha+1, coluna) == valor){
                if(valor == simboloJogador1)
                    ganhador = jogador1;
                else
                    ganhador = jogador2;
            }
            else if(camada == 0){
                if(vetor[camada+1].getValor(linha-1, coluna)== valor && vetor[camada+2].getValor(linha+1, coluna) == valor||vetor[camada+1].getValor(linha+1, coluna)== valor && vetor[camada+2].getValor(linha-1, coluna) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 1){
                if(vetor[camada-1].getValor(linha-1, coluna)== valor && vetor[camada+1].getValor(linha+1, coluna) == valor||vetor[camada-1].getValor(linha+1, coluna)== valor && vetor[camada+1].getValor(linha-1, coluna) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 2){
                if(vetor[camada-2].getValor(linha-1, coluna)== valor && vetor[camada-1].getValor(linha+1, coluna) == valor||vetor[camada-2].getValor(linha+1, coluna)== valor && vetor[camada-1].getValor(linha-1, coluna) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else 
                        ganhador = jogador2;
                }
            }
        }
        if(linha == 2){
            if(vetor[camada].getValor(linha-2, coluna)== valor && vetor[camada].getValor(linha-1, coluna) == valor){
                if(valor == simboloJogador1)
                    ganhador = jogador1;
                else 
                    ganhador = jogador2;
            }
            else if(camada == 0){
                if(vetor[camada+1].getValor(linha-2, coluna)== valor && vetor[camada+2].getValor(linha-1, coluna) == valor||vetor[camada+1].getValor(linha-1, coluna)== valor && vetor[camada+2].getValor(linha-2, coluna) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 1){
                if(vetor[camada-1].getValor(linha-2, coluna)== valor && vetor[camada+1].getValor(linha+1, coluna) == valor||vetor[camada-1].getValor(linha-1, coluna)== valor && vetor[camada+1].getValor(linha-2, coluna) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 2){
                if(vetor[camada-2].getValor(linha-2, coluna)== valor && vetor[camada-1].getValor(linha-1, coluna) == valor||vetor[camada-2].getValor(linha-1, coluna)== valor && vetor[camada-1].getValor(linha-2, coluna) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }
        }
    }
    private void olharLinhas(int camada,int linha, int coluna, char valor){
        if(coluna == 0){
            if(vetor[camada].getValor(linha, coluna+1)== valor && vetor[camada].getValor(linha, coluna+2) == valor){
                if(valor == simboloJogador1)
                    ganhador = jogador1;
                else 
                    ganhador = jogador2;
            }
            else if(camada == 0){
                if(vetor[camada+1].getValor(linha, coluna+1)== valor && vetor[camada+2].getValor(linha, coluna+2) == valor||vetor[camada+1].getValor(linha, coluna+2)== valor && vetor[camada+2].getValor(linha, coluna+1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 1){
                if(vetor[camada-1].getValor(linha, coluna+1)== valor && vetor[camada+1].getValor(linha, coluna+2) == valor||vetor[camada-1].getValor(linha, coluna+2)== valor && vetor[camada+1].getValor(linha, coluna+1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else 
                        ganhador = jogador2;
                }
            }else if(camada == 2){
                if(vetor[camada-2].getValor(linha, coluna+1)== valor && vetor[camada-1].getValor(linha, coluna+2) == valor||vetor[camada-2].getValor(linha, coluna+2)== valor && vetor[camada-1].getValor(linha, coluna+1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }
        }else if(coluna == 1){
            if(vetor[camada].getValor(linha, coluna-1)== valor && vetor[camada].getValor(linha, coluna+1) == valor){
                if(valor == simboloJogador1)
                    ganhador = jogador1;
                else
                    ganhador = jogador2;
            }
            else if(camada == 0){
                if(vetor[camada+1].getValor(linha, coluna-1)== valor && vetor[camada+2].getValor(linha, coluna+1) == valor||vetor[camada+1].getValor(linha, coluna+1)== valor && vetor[camada+2].getValor(linha, coluna-1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 1){
                if(vetor[camada-1].getValor(linha, coluna-1)== valor && vetor[camada+1].getValor(linha, coluna+1) == valor||vetor[camada-1].getValor(linha, coluna+1)== valor && vetor[camada+1].getValor(linha, coluna-1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 2){
                if(vetor[camada-2].getValor(linha, coluna-1)== valor && vetor[camada-1].getValor(linha, coluna+1) == valor||vetor[camada-2].getValor(linha, coluna+1)== valor && vetor[camada-1].getValor(linha, coluna-1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else 
                        ganhador = jogador2;
                }
            }
        }
        if(coluna == 2){
            if(vetor[camada].getValor(linha, coluna-2)== valor && vetor[camada].getValor(linha, coluna-1) == valor){
                if(valor == simboloJogador1)
                    ganhador = jogador1;
                else 
                    ganhador = jogador2;
            }
            else if(camada == 0){
                if(vetor[camada+1].getValor(linha, coluna-2)== valor && vetor[camada+2].getValor(linha, coluna-1) == valor||vetor[camada+1].getValor(linha, coluna-1)== valor && vetor[camada+2].getValor(linha, coluna-2) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    ganhador = jogador2;
                }
            }else if(camada == 1){
                if(vetor[camada-1].getValor(linha, coluna-2)== valor && vetor[camada+1].getValor(linha, coluna+1) == valor||vetor[camada-1].getValor(linha, coluna-1)== valor && vetor[camada+1].getValor(linha, coluna-2) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    ganhador = jogador2;
                }
            }else if(camada == 2){
                if(vetor[camada-2].getValor(linha, coluna-2)== valor && vetor[camada-1].getValor(linha, coluna-1) == valor||vetor[camada-2].getValor(linha, coluna-1)== valor && vetor[camada-1].getValor(linha, coluna-2) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    ganhador = jogador2;
                }
            }
        }
    }
    
    private void olharDiagonais(int camada,int linha, int coluna, char valor){
        if(coluna == 0 && linha == 0){
            if(vetor[camada].getValor(linha+1, coluna+1)== valor && vetor[camada].getValor(linha+2, coluna+2) == valor){
                if(valor == simboloJogador1)
                    ganhador = jogador1;
                else 
                    ganhador = jogador2;
            }
            else if(camada == 0){
                if(vetor[camada+1].getValor(linha+1, coluna+1)== valor && vetor[camada+2].getValor(linha+2, coluna+2) == valor||vetor[camada+1].getValor(linha+2, coluna+2)== valor && vetor[camada+2].getValor(linha+1, coluna+1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 1){
                if(vetor[camada-1].getValor(linha, coluna+1)== valor && vetor[camada+1].getValor(linha, coluna+2) == valor||vetor[camada-1].getValor(linha, coluna+2)== valor && vetor[camada+1].getValor(linha, coluna+1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else 
                        ganhador = jogador2;
                }
            }else if(camada == 2){
                if(vetor[camada-2].getValor(linha, coluna+1)== valor && vetor[camada-1].getValor(linha, coluna+2) == valor||vetor[camada-2].getValor(linha, coluna+2)== valor && vetor[camada-1].getValor(linha, coluna+1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }
        }else if(coluna == 0 && linha == 2){
            if(vetor[camada].getValor(linha-1, coluna+1) == valor && vetor[camada].getValor(linha-2, coluna+2) == valor){
                if(valor == simboloJogador1)
                    ganhador = jogador1;
                else
                    ganhador = jogador2;
            }
            else if(camada == 0){
                if(vetor[camada+1].getValor(linha-1, coluna+1)== valor && vetor[camada+2].getValor(linha-2, coluna+2) == valor||vetor[camada+1].getValor(linha-2, coluna+2)== valor && vetor[camada+2].getValor(linha-1, coluna+1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 1){
                if(vetor[camada-1].getValor(linha-1, coluna+1)== valor && vetor[camada+1].getValor(linha-2, coluna+2) == valor||vetor[camada-1].getValor(linha-2, coluna+2)== valor && vetor[camada+1].getValor(linha-1, coluna+1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 2){
                if(vetor[camada-2].getValor(linha-1, coluna+2)== valor && vetor[camada-1].getValor(linha-2, coluna+2) == valor||vetor[camada-2].getValor(linha-2, coluna+2)== valor && vetor[camada-1].getValor(linha-1, coluna+1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else 
                        ganhador = jogador2;
                }
            }
        }
        if(coluna == 1 && linha == 1){
            if(vetor[camada].getValor(linha-1, coluna-1)== valor && vetor[camada].getValor(linha+1, coluna+1) == valor){
                if(valor == simboloJogador1)
                    ganhador = jogador1;
                else 
                    ganhador = jogador2;
            }
            else if(camada == 0){
                if(vetor[camada+1].getValor(linha-1, coluna-1)== valor && vetor[camada+2].getValor(linha+1, coluna+1) == valor||vetor[camada+1].getValor(linha+1, coluna+1)== valor && vetor[camada+2].getValor(linha-1, coluna-1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 1){
                if(vetor[camada-1].getValor(linha-1, coluna-1)== valor && vetor[camada+1].getValor(linha+1, coluna+1) == valor||vetor[camada-1].getValor(linha+1, coluna+1)== valor && vetor[camada+1].getValor(linha-1, coluna-1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 2){
                if(vetor[camada-2].getValor(linha-1, coluna-1)== valor && vetor[camada-1].getValor(linha+1, coluna+1) == valor||vetor[camada-2].getValor(linha+1, coluna+1)== valor && vetor[camada-1].getValor(linha-1, coluna-1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }
        }else if(coluna == 2 && linha == 0){
            if(vetor[camada].getValor(linha+1, coluna-1)== valor && vetor[camada].getValor(linha+2, coluna-2) == valor){
                if(valor == simboloJogador1)
                    ganhador = jogador1;
                else 
                    ganhador = jogador2;
            }
            else if(camada == 0){
                if(vetor[camada+1].getValor(linha+1, coluna-1)== valor && vetor[camada+2].getValor(linha+2, coluna-2) == valor||vetor[camada+1].getValor(linha+2, coluna-2)== valor && vetor[camada+2].getValor(linha+1, coluna-1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 1){
                if(vetor[camada-1].getValor(linha+1, coluna-1)== valor && vetor[camada+1].getValor(linha+2, coluna-2) == valor||vetor[camada-1].getValor(linha+2, coluna-2)== valor && vetor[camada+1].getValor(linha+1, coluna-1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 2){
                if(vetor[camada-2].getValor(linha+1, coluna-1)== valor && vetor[camada-1].getValor(linha+2, coluna-2) == valor||vetor[camada-2].getValor(linha+2, coluna-2)== valor && vetor[camada-1].getValor(linha+1, coluna-1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }
        }else if(linha == 2 && coluna == 2){
             if(vetor[camada].getValor(linha-1, coluna-1)== valor && vetor[camada].getValor(linha-2, coluna-2) == valor){
                if(valor == simboloJogador1)
                    ganhador = jogador1;
                else 
                    ganhador = jogador2;
            }
            else if(camada == 0){
                if(vetor[camada+1].getValor(linha-1, coluna-1)== valor && vetor[camada+2].getValor(linha-2, coluna-2) == valor||vetor[camada+1].getValor(linha-2, coluna-2)== valor && vetor[camada+2].getValor(linha-1, coluna-1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 1){
                if(vetor[camada-1].getValor(linha-1, coluna-1)== valor && vetor[camada+1].getValor(linha-2, coluna-2) == valor||vetor[camada-1].getValor(linha-2, coluna-2)== valor && vetor[camada+1].getValor(linha-1, coluna-1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }else if(camada == 2){
                if(vetor[camada-2].getValor(linha-1, coluna-1)== valor && vetor[camada-1].getValor(linha-2, coluna-2) == valor||vetor[camada-2].getValor(linha-2, coluna-2)== valor && vetor[camada-1].getValor(linha-1, coluna-1) == valor){
                    if(valor == simboloJogador1)
                        ganhador = jogador1;
                    else
                        ganhador = jogador2;
                }
            }
        }
    }
}
