package application;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			UI.imprimirTabuleiro(partidaDeXadrez.obterPecas());
			System.out.println();
			System.out.println("Origem: ");
			PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
			
			System.out.println();
			System.out.println("Destino: ");
			PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);
			
			PecaDeXadrez pecaCapturada = partidaDeXadrez.realizarMovimentoXadrez(origem, destino);
		}
		
	}

}
