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
        
        for(int i = 0; i < capturas.size(); i++){ //Removendo capturas inválidas
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
    
}
