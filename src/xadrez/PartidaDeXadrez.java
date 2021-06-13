package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		posicaoInicial();
	}
	
	public PecaDeXadrez[][] obterPecas(){
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas();i++) {
			for (int j=0; j<tabuleiro.getColunas();j++) {
				mat[i][j] = (PecaDeXadrez)tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	public PecaDeXadrez realizarMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.paraPosicao();
		Posicao destino = posicaoDestino.paraPosicao();
		validarPosicaoOrigem(origem);
		Peca pecaCapturada = realizarMovimento(origem, destino);
		return (PecaDeXadrez)pecaCapturada;
	}
	
	private Peca realizarMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicionarPeca(p, destino);
		return pecaCapturada;
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.posicaoPreenchida(posicao)) {
			throw new XadrezException("Não existe peça na posição de origem.");
		}
	}
	
	private void posicionarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.posicionarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
	}
	
	private void posicaoInicial() {
		posicionarNovaPeca('c', 1, new Torre(tabuleiro, Cor.WHITE));
		posicionarNovaPeca('c', 2, new Torre(tabuleiro, Cor.WHITE));
		posicionarNovaPeca('d', 2, new Torre(tabuleiro, Cor.WHITE));
		posicionarNovaPeca('e', 2, new Torre(tabuleiro, Cor.WHITE));
		posicionarNovaPeca('e', 1, new Torre(tabuleiro, Cor.WHITE));
		posicionarNovaPeca('d', 1, new Rei(tabuleiro, Cor.WHITE));

		posicionarNovaPeca('c', 7, new Torre(tabuleiro, Cor.BLACK));
		posicionarNovaPeca('c', 8, new Torre(tabuleiro, Cor.BLACK));
		posicionarNovaPeca('d', 7, new Torre(tabuleiro, Cor.BLACK));
		posicionarNovaPeca('e', 7, new Torre(tabuleiro, Cor.BLACK));
		posicionarNovaPeca('e', 8, new Torre(tabuleiro, Cor.BLACK));
		posicionarNovaPeca('d', 8, new Rei(tabuleiro, Cor.BLACK));

	}
}
