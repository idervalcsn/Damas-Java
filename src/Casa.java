
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
     * Identifica se a casa à diagonal esquerda é de um adversário.
     * @param tabuleiro tabuleiro no qual o jogo está sendo jogado.
     * @return Casa diagonal esquerda, caso possua uma peça inimiga, null em todos os outros casos.
     */
    public Casa inimigoDiagonalE(Tabuleiro tabuleiro){

        int player = peca.getPlayer();
        if(x == 0){/** quando x é igual a 0, estamos na borda esquerda do tabuleiro. Logo, não existe diagonal á esquerda */
            return null;
        }

        if((peca.getTipo() == 0) ||peca.getTipo() == 3){ /** Casos em que temos peça branca ou dama vermelha. Ambas possuem o mesmo movimento, então as diagonais a serem checadas são na mesma direção.  */
            Casa diagonal = tabuleiro.getCasa(x - 1, y + 1);
            Peca pecad = diagonal.getPeca();
            if(pecad != null){
                if(pecad.getPlayer() != player){ /** Checando que as peças são inimigas */
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
        else {  /** Casos em que temos peça vermelha ou dama branca. Ambas possuem o mesmo movimento, então as diagonais a serem checadas são na mesma direção.  */
            Casa diagonal = tabuleiro.getCasa(x - 1, y - 1);
            Peca pecad = diagonal.getPeca();
            if(pecad != null){
                if(pecad.getPlayer() != player){ /** Checando que as peças são inimigas */
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

    }

    /**
     * Identifica se a casa à diagonal direita é de um adversário.
     * @param tabuleiro tabuleiro no qual o jogo está sendo jogado.
     * @return Casa diagonal direita, caso possua uma peça inimiga, null em todos os outros casos.
     */
    public Casa inimigoDiagonalD(Tabuleiro tabuleiro){
        int player = peca.getPlayer();
        if(x == 7){ /** quando x é igual a 7, estamos na borda direita do tabuleiro. Logo, não existe diagonal á esquerda */
            return null;
        }

        if((peca.getTipo() == 0) ||peca.getTipo() == 3){ /** Casos em que temos peça branca ou dama vermelha. Ambas possuem o mesmo movimento, então as diagonais a serem checadas são na mesma direção.  */
            Casa diagonal = tabuleiro.getCasa(x + 1, y + 1);
            Peca pecad = diagonal.getPeca();
            if(pecad != null){
                if(pecad.getPlayer() != player){ /** Checando que as peças são inimigas */
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
        else { /** Casos em que temos peça vermelha ou dama branca. Ambas possuem o mesmo movimento, então as diagonais a serem checadas são na mesma direção.  */
            Casa diagonal = tabuleiro.getCasa(x + 1, y - 1);
            Peca pecad = diagonal.getPeca();
            if(pecad != null){
                if(pecad.getPlayer() != player){ /** Checando que as peças são inimigas */
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

    }

    /**Verifica se a peça em uma casa pode "comer" outra peça.
     * @param tabuleiro tabuleiro em que o jogo está sendo jogado
     * @return "true" se a peça pode comer, "false" caso contrário
     */
    public boolean podeComer(Tabuleiro tabuleiro){

        if((this.inimigoDiagonalD(tabuleiro) == null && (this.inimigoDiagonalE(tabuleiro) == null))){ /** Caso a peça não possua nenhum inimigo nas diagonais, obviamente não terá nada pra comer */
            return false;
        }
        if(peca.getTipo() == 0 || peca.getTipo() == 3){ 
            if(this.inimigoDiagonalD(tabuleiro) != null && !(y >= 6 || y <= 1))  { /** Checando se a diagonal direita pode ser comida E que não está na borda do tabuleiro */
                Casa fim = tabuleiro.getCasa(x+2,y+2);
                if(!fim.possuiPeca()){
                    return true;
                }
                
            }

             if(this.inimigoDiagonalE(tabuleiro) != null && !(y >= 6 || y <= 1))  {
                Casa fim = tabuleiro.getCasa(x-2,y+2);
                if(!fim.possuiPeca()){
                    return true;
                }
                else{
                    return false;
                }
            }
        }

        else if(peca.getTipo() == 1 || peca.getTipo() == 2){
            if(this.inimigoDiagonalD(tabuleiro) != null && !(y >= 6 || y <= 1))  {
                Casa fim = tabuleiro.getCasa(x+2,y-2);
                if(!fim.possuiPeca()){
                    return true;
                }
                
            }

            if(this.inimigoDiagonalE(tabuleiro) != null && !(y >= 6 || y <= 1))  {
                Casa fim = tabuleiro.getCasa(x-2,y-2);
                if(!fim.possuiPeca()){
                    return true;
                }
                else{
                    return false;
                }
            }
        }

        return false;

    }
}
