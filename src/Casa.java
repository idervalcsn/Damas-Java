
/**
 * Representa uma Casa do tabuleiro.
 * Possui uma posi�ao (i,j) e pode conter uma Pe�a.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 * @author Iderval Neto &lt;idervalneto@cc.ci.ufpb.br&gt;
 */
public class Casa {

    private int x;
    private int y;
    private Peca peca;

    public Casa(int x, int y) {
        this.x = x;
        this.y = y;
        this.peca = null;
    }

    /**      
     * @return Valor x dessa casa.
     */    
    public int getX(){
        return x;
    }

    /**      
     * @return Valor y dessa casa.
     */    
    public int getY(){
        return y;
    }

    /**
     * Coloca uma peca nessa casa.
     * @param peca a Pe�a a ser posicionada nesta Casa.
     */
    public void colocarPeca(Peca peca) {
        this.peca = peca;
    }

    /**
     * Remove a peca posicionada nesta casa, se houver.
     */
    public void removerPeca() {
        peca = null;
    }

    /**
     * @return a Peca posicionada nesta Casa, ou Null se a casa estiver livre.
     */
    public Peca getPeca() {
        return peca;
    }

    /**
     * @return true se existe uma pe�a nesta casa, caso contrario false.
     */
    public boolean possuiPeca() {
        return peca != null;
    }

    /**
     * Identifica e retorna a casa presente entre duas casas.
     * @param tabuleiro tabuleiro em que o jogo está sendo jogado.
     * @param destinox valor x da segunda casa.
     * @param destinox valor y da segunda casa.
     * @return null caso a distância entre as duas casas não seja dois e retorna a casa quando a distância é dois.
     */    
    public Casa meio(Tabuleiro tabuleiro,int destinox, int destinoy){
        int deslocax = destinox - x; 
        int deslocay = destinoy - y;
        if(!(Math.abs(deslocax) == 2)) {
            return null;
        }
        else{
            Casa meio = tabuleiro.getCasa(x+(deslocax/2),y+(deslocay/2)); //Deslocax/2 = Caso deslocax for positivo, teremos x1+1, positivo. Caso conrário x1-1, negativo. Da mesma forma para deslocay/2.
            return meio;
        }

    }

    
    
}
