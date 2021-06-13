package tabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Peca[][] pecas;

	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroException(
					"Erro ao criar o tabuleiro: É necessário que haja no mínimo 1 linha e 1 coluna!");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public Peca peca(int linha, int coluna) {
		if (!posicaoExistente(linha, coluna)) {
			throw new TabuleiroException("Posição não existente no tabuleiro");
		}
		return pecas[linha][coluna];
	}

	public Peca peca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("Posição não existente no tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	public void posicionarPeca(Peca peca, Posicao posicao) {
		if (posicaoPreenchida(posicao)) {
			throw new TabuleiroException("A posição " + posicao + " já está preenchida!");
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}

	private boolean posicaoExistente(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}

	public Peca removerPeca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("Posição não existente no tabuleiro");
		}
		if (peca(posicao) == null){
			return null;
		}
		Peca aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
	public boolean posicaoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());
	}

	public boolean posicaoPreenchida(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("Posição não existente no tabuleiro");
		}
		return peca(posicao) != null;
	}
}
