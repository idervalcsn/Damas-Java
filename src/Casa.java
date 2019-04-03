
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
    
    public int getX(){
        return x;
    }
    
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
    
    public Casa meio(Tabuleiro tabuleiro,int destinox, int destinoy){
        int deslocax = destinox - x;
        int deslocay = destinoy - y;
        if(!(Math.abs(deslocax) == 2)){
            return null;
        }
        else{
            Casa meio = tabuleiro.getCasa(x+(deslocax/2),y+(deslocay/2)); //Deslocax/2 = Caso deslocax for positivo, teremos x1+1, positivo. Caso conrário x1-1, negativo.
            return meio;
        }
        
        
    }
    /**
     * Identifica se a casa entre o destino (á esquerda) e a origem é capturável.
     * @param tabuleiro tabuleiro no qual o jogo está sendo jogado.
     * @param origemY linha em que a casa começa
     * @param destinoY linha em que a casa termina
     * @return Casa diagonal esquerda, caso possua uma peça inimiga, null em todos os outros casos.
     */
    public Casa inimigoDiagonalE(Tabuleiro tabuleiro, int origemY, int destinoY){
        int player = peca.getPlayer();
        if(x <= 1){ /** quando x é menor ou igual 1, é impossível uma peça comer na direção esquerda */
            return null;
        }

        if(origemY < destinoY){ 
            Casa diagonal = tabuleiro.getCasa(x - 1, y + 1);
            Casa aposdiagonal = tabuleiro.getCasa(x - 2, y + 2);
            Peca pecad = diagonal.getPeca();
            if(pecad != null){
                if(pecad.getPlayer() != player && !aposdiagonal.possuiPeca()){ /** Checando que as peças são inimigas e o destino está vazio */
                    return diagonal;
                }
                else{
                    return null;
                }
            }
            else{
                return null;

            }
        } 

        else if(origemY > destinoY){ /** */
            Casa diagonal = tabuleiro.getCasa(x - 1, y - 1);
            Casa aposdiagonal = tabuleiro.getCasa(x - 2, y - 2);
            Peca pecad = diagonal.getPeca();
            if(pecad != null){
                if(pecad.getPlayer() != player && !aposdiagonal.possuiPeca()){ 
                    return diagonal;
                }
                else{
                    return null;
                }
            }
            else{
                return null;

            }

        }
        else{
            return null;
        }

    }

    /**
     * Identifica se a casa entre o destino (à direita) e a origem é capturável.
     * @param tabuleiro tabuleiro no qual o jogo está sendo jogado.
     * @param origemY linha em que a casa começa
     * @param destinoY linha em que a casa termina
     * @return Casa diagonal direita, caso possua uma peça inimiga, null em todos os outros casos.
     */
    public Casa inimigoDiagonalD(Tabuleiro tabuleiro, int origemY, int destinoY){
        int player = peca.getPlayer();
        if(x >= 6){ /** quando x é maior ou igual a 6, é impossível comer na direção direita*/
            return null;
        }

        if(origemY < destinoY){
            Casa diagonal = tabuleiro.getCasa(x + 1, y + 1);
            Casa aposdiagonal = tabuleiro.getCasa(x + 2, y + 2);
            Peca pecad = diagonal.getPeca();
            if(pecad != null){
                if(pecad.getPlayer() != player && !aposdiagonal.possuiPeca()){ /** Checando que as peças são inimigas e o destino está vazio */
                    return diagonal;
                }
                else{
                    return null;
                }
            }
            else{
                return null;

            }
        } 

        else if(origemY > destinoY){ /** */
            Casa diagonal = tabuleiro.getCasa(x + 1, y - 1);
            Casa aposdiagonal = tabuleiro.getCasa(x + 2, y - 2);
            Peca pecad = diagonal.getPeca();
            if(pecad != null){
                if(pecad.getPlayer() != player && !aposdiagonal.possuiPeca()){ 
                    return diagonal;
                }
                else{
                    return null;
                }
            }
            else{
                return null;

            }
        }
        else{
            return null;
        }

    }

    /**Verifica se a peça em uma casa pode "comer" outra peça.
     * @param tabuleiro tabuleiro em que o jogo está sendo jogado
     * @return a casa em que a peça se encontra se a ela pode comer, null caso contrário
     */
    public Casa podeComer(Tabuleiro tabuleiro){
        int inferior = y - 2;
        int superior = y + 2;   
        int[] y_possiveis = {superior, inferior};
        if(!(y>=6 || y<=1)){
            for(int i : y_possiveis){
                if(this.inimigoDiagonalE(tabuleiro,y,i) != null || this.inimigoDiagonalD(tabuleiro,y,i) != null){
                    return this;
                }
                

            }
        }

        else if(y>=6) {

            if(this.inimigoDiagonalE(tabuleiro,y,inferior) != null || this.inimigoDiagonalD(tabuleiro,y,inferior) != null ){
                return this;
            }
            

        }

        else {

            if(this.inimigoDiagonalE(tabuleiro,y,superior) != null || this.inimigoDiagonalD(tabuleiro,y,superior) != null){
                return this;
            }
            

        }
        
        return null;
    }
}
