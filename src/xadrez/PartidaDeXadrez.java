package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		posicaoInicial();
	}

	public int obterTurno() {
		return turno;
	}
	
	public Cor obterJogadorAtual() {
		return jogadorAtual;
	}
	public PecaDeXadrez[][] obterPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}

	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.paraPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();

	}

	public PecaDeXadrez realizarMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.paraPosicao();
		Posicao destino = posicaoDestino.paraPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = realizarMovimento(origem, destino);
		return (PecaDeXadrez) pecaCapturada;
	}

	private Peca realizarMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicionarPeca(p, destino);
		proximoTurno();
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		return pecaCapturada;
	}

	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.posicaoPreenchida(posicao)) {
			throw new XadrezException("Não existe peça na posição de origem.");
		}
		if (jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peça escolhida pertence ao outro jogador!");
		}
		if (!tabuleiro.peca(posicao).existeAlgumMovimentoValido()) {
			throw new XadrezException("Não existem movimentos possíveis para a peça escolhida!");
		}
	}

	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new XadrezException("A peça escolhida não pode se mover para essa posição!");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private void posicionarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.posicionarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
		pecasNoTabuleiro.add(peca);
	}

	private void posicaoInicial() {
		posicionarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		posicionarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));

	}
}
