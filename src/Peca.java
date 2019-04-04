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
     * Movimenta a peca para uma nova casa e muda o tipo dela, caso necessário.
     * @param destino nova casa que ira conter esta peca.
     */
    public void mover(Casa destino) {
        casa.removerPeca();
        destino.colocarPeca(this);
        casa = destino;
        this.x = casa.getX();
        this.y = casa.getY();
        if(destino.getY() == 7 || destino.getY() == 0){ //Casas em que ela muda de tipo
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
     * 
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

        return -1;

    }

    /**
     * Checa se o movimento que a peça está tentnado executar é para uma diagonal válida.
     * @param tabuleiro tabuleiro em que o jogo está sendo jogado
     * @param destino casa para a qual a peça está tentando se mover   
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
        else if(Math.abs(deslocaX) == 2){
            if(!destino.possuiPeca() && this.capturasPossiveis(tabuleiro).contains(destino)){ /** Caso em que a quer capturar e a casa de destino está vazia */
                return true;
            }
            else{
                return false;
            }
        }

        return false;

    }

    /** Identifica o tipo de movimento que uma peça está tentando fazer segundo a seguinte tabela:
     * Valor    Tipo
     *   0   Movimento inválido
     *   1   Movimento de uma casa
     *   2   Movimento de captura     *   
     * @param destino casa para a qual a peça está tentando se mover 
     * @return o tipo do movimento.
     */

    public int tipoDeMovimento(Casa destino){       
        int normal = 1;
        int captura = 2;
        int deslocax = destino.getX() - x;
        if(Math.abs(deslocax) == 1){
            return normal;
        }
        else if(Math.abs(deslocax) == 2){
            return captura;
        }
        return 0;
    }

    /**
     * Realiza o movimento de captura de uma peça.
     * @param tabuleiro tabuleiro em que o jogo está sendo jogado
     * @param destino casa para a qual a peça está tentando se mover        * 
     */

    public void capturar(Tabuleiro tabuleiro,Casa destino){        
        int fimx = destino.getX();
        int fimy = destino.getY();
        Casa meio = casa.meio(tabuleiro, fimx,fimy); //Casa entre o destino e a peça.
        meio.removerPeca();
        this.mover(destino);
    }

    /**
     * Identifica e retorna todos os movimentos de captura possíveis de uma peça.
     * @param tabuleiro tabuleiro em que o jogo está sendo jogado
     * @return uma lista de todos os movimentos de captura possíveis de uma peça.         
     */

    public ArrayList<Casa> capturasPossiveis(Tabuleiro tabuleiro){
        ArrayList<Casa> capturas = new ArrayList<Casa>();

        if((x >= 2 && x<=5)){ //Não está nas bordas laterais
            if((y>=2 && y <=5)){  //Não está nas bordas superiores
                capturas.add(tabuleiro.getCasa(x+2,y+2));
                capturas.add(tabuleiro.getCasa(x+2,y-2));
                capturas.add(tabuleiro.getCasa(x-2,y+2));
                capturas.add(tabuleiro.getCasa(x-2,y-2));
            }
            else if(y < 2){ //Na borda inferior
                capturas.add(tabuleiro.getCasa(x+2,y+2));                
                capturas.add(tabuleiro.getCasa(x-2,y+2));

            }
            else{ //Na borda superior
                capturas.add(tabuleiro.getCasa(x+2,y-2));                
                capturas.add(tabuleiro.getCasa(x-2,y-2));
            }
        }
        else if(x < 2){ //Na borda esquerda
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

        for(int i = capturas.size()-1; i >= 0; i--){ //Removendo capturas inválidas, seja por possuir uma peça no destino, seja por não possuir uma peça entre o destino e a origem.
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

    /**
     * Identifica se uma peça tem algum movimento de captura válido.
     * @param tabuleiro tabuleiro em que o jogo está sendo jogado
     * @return true se ela pode capturar, false caso contrário.         
     */

    public boolean podeComer(Tabuleiro tabuleiro){
        if(this.capturasPossiveis(tabuleiro).isEmpty()){ //Caso a lista de capturas esteja vazia.
            return false;
        }
        else{
            return true;
        }
    }

}
