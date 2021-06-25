package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> capturadas = new ArrayList<>();
		
		while (true) {
			try {
				UI.limparTela();
				UI.imprimirPartida(partidaDeXadrez, capturadas);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
				
				boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(origem);
				UI.limparTela();
				UI.imprimirTabuleiro(partidaDeXadrez.obterPecas(), movimentosPossiveis);
				
				
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);
				
				PecaDeXadrez pecaCapturada = partidaDeXadrez.realizarMovimentoXadrez(origem, destino);
				
				if (pecaCapturada != null) {
					capturadas.add(pecaCapturada);
				}
			}
			catch (XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
	}

}
