
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
    Casa comendo = null; /** Verifica se alguma peça pode continuar comendo. */
    public Jogo() {
        tabuleiro = new Tabuleiro(); /** cria um tabuleiro de casas vazias */
        criarPecas(); /** chama o método privado criarPecas() para popular o tabuleiro. */
    }

    /**
     * Posiciona pe�as no tabuleiro.
     * Utilizado na inicializa�ao do jogo.
     */
    private void criarPecas() {
        /**PEÇAS
        BRANCAS*/
        Casa casa1 = tabuleiro.getCasa(0, 0); /**"pega" a casa (x,y) e guarda ela no objeto casa1 do tipo casa */
        Peca peca1 = new Peca(casa1, Peca.PEDRA_BRANCA); /** Cria uma peça na casa definida (o que é feito na linha acima) e do tipo 
        (nesse caso) PEDRA_BRANCA.Os tipos estão definidos na classe peca */      

        Casa casa2 = tabuleiro.getCasa(2, 0);
        Peca peca2 = new Peca(casa2, Peca.PEDRA_BRANCA);

        Casa casa3 = tabuleiro.getCasa(4, 0);
        Peca peca3 = new Peca(casa3, Peca.PEDRA_BRANCA);

        Casa casa4 = tabuleiro.getCasa(6, 0);
        Peca peca4 = new Peca(casa4, Peca.PEDRA_BRANCA);  

        Casa casa5 = tabuleiro.getCasa(1, 1);
        Peca peca5 = new Peca(casa5, Peca.PEDRA_BRANCA);

        Casa casa6 = tabuleiro.getCasa(3, 1);
        Peca peca6 = new Peca(casa6, Peca.PEDRA_BRANCA);

        Casa casa7 = tabuleiro.getCasa(5, 1);
        Peca peca7 = new Peca(casa7, Peca.PEDRA_BRANCA);

        Casa casa8 = tabuleiro.getCasa(7, 1);
        Peca peca8 = new Peca(casa8, Peca.PEDRA_BRANCA);

        Casa casa9 = tabuleiro.getCasa(0, 2);
        Peca peca9 = new Peca(casa9, Peca.PEDRA_BRANCA);

        Casa casa10 = tabuleiro.getCasa(2, 2);
        Peca peca10 = new Peca(casa10, Peca.PEDRA_BRANCA);

        Casa casa11 = tabuleiro.getCasa(4, 2);
        Peca peca11 = new Peca(casa11, Peca.PEDRA_BRANCA);

        Casa casa12 = tabuleiro.getCasa(6, 2);
        Peca peca12 = new Peca(casa12, Peca.PEDRA_BRANCA);

        /**PEÇAS
        VERMELHAS*/

        Casa casa13 = tabuleiro.getCasa(7, 7);
        Peca peca13 = new Peca(casa13, Peca.PEDRA_VERMELHA);

        Casa casa14 = tabuleiro.getCasa(5, 7);
        Peca peca14 = new Peca(casa14, Peca.PEDRA_VERMELHA);

        Casa casa15 = tabuleiro.getCasa(3, 7);
        Peca peca15 = new Peca(casa15, Peca.PEDRA_VERMELHA);

        Casa casa16 = tabuleiro.getCasa(1, 7);
        Peca peca16 = new Peca(casa16, Peca.PEDRA_VERMELHA);

        Casa casa17 = tabuleiro.getCasa(6, 6);
        Peca peca17 = new Peca(casa17, Peca.PEDRA_VERMELHA);

        Casa casa18 = tabuleiro.getCasa(4, 6);
        Peca peca18 = new Peca(casa18, Peca.PEDRA_VERMELHA);

        Casa casa19 = tabuleiro.getCasa(2, 6);
        Peca peca19 = new Peca(casa19, Peca.PEDRA_VERMELHA);

        Casa casa20 = tabuleiro.getCasa(0, 6);
        Peca peca20 = new Peca(casa20, Peca.PEDRA_VERMELHA);

        Casa casa21 = tabuleiro.getCasa(7, 5);
        Peca peca21 = new Peca(casa21, Peca.PEDRA_VERMELHA);

        Casa casa22 = tabuleiro.getCasa(5, 5);
        Peca peca22 = new Peca(casa22, Peca.PEDRA_VERMELHA);

        Casa casa23 = tabuleiro.getCasa(3, 5);
        Peca peca23 = new Peca(casa23, Peca.PEDRA_VERMELHA);

        Casa casa24 = tabuleiro.getCasa(1, 5);
        Peca peca24 = new Peca(casa24, Peca.PEDRA_VERMELHA);
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

        if (player == turno  && (comendo == null || comendo == origem)){
            if(ehDiagonalValida(origemX,origemY,destinoX,destinoY)){

                if(Math.abs(destinoX - origemX) == 1 && comendo == null){ /** Movimento de uma casa */                
                    peca.mover(destino);
                    if (destinoY == 7 || destinoY == 0) { /** Condições para virar dama */
                        peca.setTipo();
                    }
                    turno = turno*(-1);

                }

                else{
                    if(origemX < destinoX){
                        if(origem.inimigoDiagonalD(tabuleiro) != null){  
                            Casa diagonal = origem.inimigoDiagonalD(tabuleiro);
                            if(!destino.possuiPeca()){
                                peca.mover(destino);
                                diagonal.removerPeca();

                            }
                            if (destinoY == 7 || destinoY == 0) {
                                peca.setTipo();
                            }
                            if(destino.podeComer(tabuleiro)){                                    
                                comendo = destino;
                            }
                            else{
                                comendo = null;
                                turno = turno*(-1);
                            }
                        }
                    }
                    else if(origemX > destinoX){

                        if(origem.inimigoDiagonalE(tabuleiro) != null){  
                            Casa diagonal = origem.inimigoDiagonalE(tabuleiro);
                            if(!destino.possuiPeca()){
                                peca.mover(destino);
                                diagonal.removerPeca();                                
                            }
                            if (destinoY == 7 || destinoY == 0) {
                                peca.setTipo();
                            }
                            if(destino.podeComer(tabuleiro)){                                    
                                comendo = destino;
                            }
                            else{                                    
                                comendo = null;
                                turno = turno*(-1);
                            }
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

    /**
     * Checa se o movimento que a peça está tentnado executar é para uma diagonal válida.
     * @param origemX linha da casa de origem
     * @param destinoX coluna da casa de origem
     * @param destinoX linha da casa de destino
     * @param destinoY coluna da casa de destino
     * @return true se é uma diagonal válida, false caso contrário.
     */
    public boolean ehDiagonalValida(int origemX, int origemY, int destinoX, int destinoY){
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);           
        Peca peca = origem.getPeca();

        int deslocaX = destinoX - origemX;
        int deslocaY = destinoY - origemY;
        if((Math.abs(deslocaX) != Math.abs(deslocaY)) || Math.abs(deslocaX) > 2 || deslocaX == 0) { /** Testes básicos para verificar a validade do movimento */
            return false;
        }

        if((peca.getTipo() == 0) ||(peca.getTipo() == 3)){ /** Se a peça for uma pedra branca ou dama vermelha */

            if(!(destino.possuiPeca()) && (origemY < destinoY)){ /** Se o destino não possuir peça e a peça estiver "subindo" */
                return true;
            }
        }

        else if((peca.getTipo() == 2) || (peca.getTipo() == 1)){ /** Se a peça for uma pedra vermelha ou dama branca */

            if(!destino.possuiPeca() && (origemY > destinoY)){ /** Se o destino não possuir peça e a peça estiver "descendo" */
                return true;
            }
        }

        return false;

    }

}
