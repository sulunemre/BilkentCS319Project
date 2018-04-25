import com.badlogic.gdx.audio.Music;

public class GameManager {
    private static GameManager gameManager = new GameManager();

    private String playerName;
    private Music currentMusic;
    private double score;

    public static GameManager getInstance() {
        return gameManager;
    }

    private GameManager() {
    }

    public void saveHighScore(){
        //TODO: implement
    }

    public void loadHighScoreTable(){
        //TODO: implement
    }

    /**
     * Mutes the music of the game.
     */
    public void muteMusic(){
        currentMusic.setVolume(0);
    }

    /**
     * Reactivate the music.
     */
    public void unmuteMusic(){
        currentMusic.setVolume(1);
    }
}
