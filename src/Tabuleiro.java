
/**
 * O Tabuleiro do jogo. 
 * Respons�vel por armazenar as 64 casas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Tabuleiro {

    private Casa[][] casas;

    public Tabuleiro() {
        casas = new Casa[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Casa casa = new Casa(x, y);
                casas[x][y] = casa;
            }
        }
    }

    /**
     * @param x linha
     * @param y coluna
     * @return Casa na posicao (x,y)
     */
    public Casa getCasa(int x, int y) {
        return casas[x][y];
    }

    public boolean algmPodeComer(int player){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Casa casa = getCasa(i, j);
                if(casa.possuiPeca() && casa.getPeca().getPlayer() == player){
                    if(casa.getPeca().podeComer(this)) return true;
                }
            }
        }

        return false;
    }
}
