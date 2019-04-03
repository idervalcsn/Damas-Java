
/**
 * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 * @author Iderval Neto &lt;idervalneto@cc.ci.ufpb.br&gt;
 */
public class Jogo {

    private Tabuleiro tabuleiro;
    private int turno = 1; /** Em que 1 se refere ao jogador das peças brancas, e -1 ao das vermelhas. Todo jogo começa com as peças brancas.*/
    Peca comendo = null; /** Verifica se alguma peça pode continuar comendo. */
    public Jogo() {
        tabuleiro = new Tabuleiro(); /** cria um tabuleiro de casas vazias */
        criarPecas(); /** chama o método privado criarPecas() para popular o tabuleiro. */
    }

    /**
     * Posiciona pe�as no tabuleiro.
     * Utilizado na inicializa�ao do jogo.
     */
    private void criarPecas() {
        for(int y = 0; y < 3; y++ ){ 
            if(y !=1){ //Quando y é diferente de 1, x as casas pretas do jogador branco são pares em x.
                for(int x = 0; x < 8; x++){ 
                    if(x%2 == 0 ){ //Casas pretas apenas
                        Casa casa = tabuleiro.getCasa(x,y);
                        Peca peca = new Peca(casa, Peca.PEDRA_BRANCA);
                    }
                }
            }
            else{
                for(int x = 0; x < 8; x++){ //Quando y é igual a 1, as casas pretas do jogador branco são ímpares em x.
                    if(x%2 != 0 ){ //Casas pretas apenas
                        Casa casa = tabuleiro.getCasa(x,y);
                        Peca peca = new Peca(casa, Peca.PEDRA_BRANCA);
                    }
                }
            }

        }
        
        for(int y = 7; y > 4; y-- ){ 
            if(y !=6){ //Quando y é diferente de 6, as casas pretas do jogador vermelho são ímpares em x.
                for(int x = 1; x < 8; x++){ 
                    if(x%2 != 0 ){ //Casas pretas apenas
                        Casa casa = tabuleiro.getCasa(x,y);
                        Peca peca = new Peca(casa, Peca.PEDRA_VERMELHA);
                    }
                }
            }
            else{
                for(int x = 0; x < 8; x++){ //Quando y é igual 6, as casas pretas do jogador vermelho são pares em x.
                    if(x%2 == 0 ){ //Casas pretas apenas
                        Casa casa = tabuleiro.getCasa(x,y);
                        Peca peca = new Peca(casa, Peca.PEDRA_VERMELHA);
                    }
                }
            }

        }
    }

    /**
     * Comanda uma Pe�a na posicao (origemX, origemY) fazer um movimento 
     * para (destinoX, destinoY).
     * 
     * @param origemX linha da Casa de origem.
     * @param origemY coluna da Casa de origem.
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino.
     */
    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);           
        Peca peca = origem.getPeca();        
        int player = peca.getPlayer();        

        if (player == turno  && (comendo == null || comendo == peca)){
            if(peca.ehDiagonalValida(tabuleiro,destino)){

                if(Math.abs(destinoX - origemX) == 1 && comendo == null){ /** Movimento de uma casa */                
                    peca.mover(destino);                    
                    turno = turno*(-1);

                }

                else{
                    Casa meio = origem.meio(tabuleiro, destinoX, destinoY);
                    if(peca.capturasPossiveis(tabuleiro).contains(destino)){
                        peca.mover(destino);
                        meio.removerPeca();
                        if(peca.podeComer(tabuleiro)){
                            comendo = peca;
                        }
                        else{
                            turno = turno*(-1);
                            comendo = null;
                        }
                    }

                }
            }
        }

    }

    /**
     * @return o Tabuleiro em jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

   

}
