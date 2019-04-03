import java.util.ArrayList;
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
    private int x;
    private int y;

    public Peca(Casa casa, int tipo) {
        this.casa = casa;
        this.tipo = tipo;
        this.x = casa.getX();
        this.y = casa.getY();
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
        this.x = casa.getX();
        this.y = casa.getY();
        if(destino.getY() == 7 || destino.getY() == 0){
            setTipo();
        }
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

    /**
     * Checa se o movimento que a peça está tentnado executar é para uma diagonal válida.
     * @param origemX linha da casa de origem
     * @param destinoX coluna da casa de origem
     * @param destinoX linha da casa de destino
     * @param destinoY coluna da casa de destino
     * @return true se é uma diagonal válida, false caso contrário.
     */
    public boolean ehDiagonalValida(Tabuleiro tabuleiro, Casa destino){
        int destinoX = destino.getX();
        int destinoY = destino.getY();           
        int deslocaX = destinoX - x;
        int deslocaY = destinoY - y;
        
        if((Math.abs(deslocaX) != Math.abs(deslocaY)) || Math.abs(deslocaX) > 2 || deslocaX == 0) { /** Testes básicos para verificar a validade do movimento */
            return false;
        }
        if(Math.abs(deslocaX) == 1){
            if(tipo == 0 ||tipo == 3){ /** Se a peça for uma pedra branca ou dama vermelha */

                if(!destino.possuiPeca() && (y < destinoY)){ /** Se o destino não possuir peça e a peça estiver "subindo" */
                    return true;
                }
            }

            else if(tipo == 2 || tipo == 1){ /** Se a peça for uma pedra vermelha ou dama branca */

                if(!destino.possuiPeca() && (y > destinoY)){ /** Se o destino não possuir peça e a peça estiver "descendo" */
                    return true;
                }
            }
        }
        else{
            if(!destino.possuiPeca()){ /** Caso em que a peca se move em dois e a casa de destino está vazia */
                return true;
            }
        }

        return false;

    }

    public ArrayList<Casa> capturasPossiveis(Tabuleiro tabuleiro){
        ArrayList<Casa> capturas = new ArrayList<Casa>();

        if((x >= 2 && x<=5)){ //Não está nas bordas
            if((y>=2 && y <=5)){
                capturas.add(tabuleiro.getCasa(x+2,y+2));
                capturas.add(tabuleiro.getCasa(x+2,y-2));
                capturas.add(tabuleiro.getCasa(x-2,y+2));
                capturas.add(tabuleiro.getCasa(x-2,y-2));
            }
            else if(y < 2){
                capturas.add(tabuleiro.getCasa(x+2,y+2));                
                capturas.add(tabuleiro.getCasa(x-2,y+2));

            }
            else{
                capturas.add(tabuleiro.getCasa(x+2,y-2));                
                capturas.add(tabuleiro.getCasa(x-2,y-2));
            }
        }
        else if(x < 2){
            if(y>=2 && y<=5){
                capturas.add(tabuleiro.getCasa(x+2,y+2));
                capturas.add(tabuleiro.getCasa(x+2,y-2));
            }
            else if(y < 2){
                capturas.add(tabuleiro.getCasa(x+2,y+2));
            }
            else {
                capturas.add(tabuleiro.getCasa(x+2,y-2));
            }
        }
        else{ //Borda direita
            if(y>=2 && y<=5){
                capturas.add(tabuleiro.getCasa(x-2,y+2));
                capturas.add(tabuleiro.getCasa(x-2,y-2));
            }
            else if(y < 2){
                capturas.add(tabuleiro.getCasa(x-2,y+2));
            }
            else {
                capturas.add(tabuleiro.getCasa(x-2,y-2));
            }
        }

        for(int i = capturas.size()-1; i >= 0; i--){ //Removendo capturas inválidas
            Casa fim = capturas.get(i);
            int fimx = fim.getX();
            int fimy = fim.getY();            
            Casa meio = casa.meio(tabuleiro,fimx,fimy);
            Peca central = meio.getPeca();

            if(fim.possuiPeca() || central == null){
                capturas.remove(i);                
            }
            else if(central.getPlayer() == this.getPlayer()){
                capturas.remove(i);
            }

        }

        return capturas;
    }

    public boolean podeComer(Tabuleiro tabuleiro){
        if(this.capturasPossiveis(tabuleiro).isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

}
