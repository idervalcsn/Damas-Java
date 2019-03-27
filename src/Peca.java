
/**
 * Representa uma Pe�a do jogo.
 * Possui uma casa e um tipo associado.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 * @author Iderval Neto &lt;idervalneto@cc.ci.ufpb.br&gt;
 */
public class Peca {

    public static final int PEDRA_BRANCA = 0;
    public static final int DAMA_BRANCA = 1;
    public static final int PEDRA_VERMELHA = 2;
    public static final int DAMA_VERMELHA = 3;

    private Casa casa;
    private int tipo;

    public Peca(Casa casa, int tipo) {
        this.casa = casa;
        this.tipo = tipo;
        casa.colocarPeca(this);
    }

    /**
     * Movimenta a peca para uma nova casa.
     * @param destino nova casa que ira conter esta peca.
     */
    public void mover(Casa destino) {
        casa.removerPeca();
        destino.colocarPeca(this);
        casa = destino;
    }

    /**
     * Valor    Tipo
     *   0   Branca (Pedra)
     *   1   Branca (Dama)
     *   2   Vermelha (Pedra)
     *   3   Vermelha (Dama)
     * @return o tipo da peca.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Muda o tipo da peça.  
     */

    public void setTipo() {
        if(tipo == 0) {
            this.tipo = 1;
        }
        else if(tipo == 1) {
            this.tipo = 0;
        }
        else if(tipo == 2) {
            this.tipo = 3;
        }
        else if(tipo == 3) {
            this.tipo = 2;
        }
    }

    /**
     * Identifica a quem pertence a peça. '1' se refere ao jogador das peças brancas e '-1' se refere ao jogador das peças vermelhas.
     * @return o jogador (representados por '1' e '-1')
     */
    public int getPlayer(){
        if (tipo == 0 || tipo == 1) {
            return 1; 
        }        
        else if(tipo == 2 || tipo == 3) {
            return -1; 
        }
        else{
            return 0;
        }
    }

    
    
}
