/*Class used for creating bullets
 * Usman Asad
 * Talha Awan
 */
package de.swagner.paxbritannica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class Resources {

    public static Resources instance;
    public Sprite title = new Sprite(new Texture(Gdx.files.internal("data/spritepack/title.png")));


    //public Texture mineTex = new Texture(Gdx.files.internal("data/spritepack/mine.png"));
    //public TextureRegion mineReg; // = new TextureRegion(Gdx.files.internal("data/spritepack/mine.png"));
    // when giving texture region values,   do (texture,x loaction,y location, width, height)
    public Sprite credits = new Sprite(new Texture(Gdx.files.internal("data/spritepack/credits.png")));
    // public AtlasRegion region = atlas.findRegion("imagename");
    // Sprite sprite = atlas.createSprite("otherimagename");
    public TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/spritepack/packhigh.pack"));
    public Music music = Gdx.audio.newMusic(Gdx.files.internal("data/audio/music.mp3"));
    public Sprite factoryP1 = atlas.createSprite("factoryp3");
    public Sprite factoryP2 = atlas.createSprite("factoryp4");
    public Sprite factoryP3 = atlas.createSprite("factoryp2");
    public Sprite factoryP1Small = atlas.createSprite("factoryp3");
    public Sprite factoryP2Small = atlas.createSprite("factoryp4");
    public Sprite factoryP3Small = atlas.createSprite("factoryp2");
    public Sprite debrisSmall = atlas.createSprite("debrissmall");
    public Sprite debrisMed = atlas.createSprite("debrismed");
    public Sprite debrisLarge = atlas.createSprite("debrislarge");
    public Sprite fish1 = atlas.createSprite("fish1");
    public Sprite fish2 = atlas.createSprite("fish2");
    public Sprite fish3 = atlas.createSprite("fish3");
    public Sprite fish4 = atlas.createSprite("fish4");
    public Sprite fish5 = atlas.createSprite("fish5");
    public Sprite fish6 = atlas.createSprite("fish6");
    public Sprite fish7 = atlas.createSprite("fish7");
    public Sprite fish8 = atlas.createSprite("fish8");
    public Sprite fighter = atlas.createSprite("arrows");
    public Sprite background = atlas.createSprite("background");
    public Sprite blackFade = atlas.createSprite("blackfade");
    public Texture bullet = new Texture(Gdx.files.internal("data/spritepack/mine.png"));
    public Texture jellyFish = new Texture(Gdx.files.internal("data/spritepack/jellyfish.png"));
    public Texture shark = new Texture(Gdx.files.internal("data/spritepack/shark.png"));
    public Texture octopus = new Texture(Gdx.files.internal("data/spritepack/octopus.png"));
    public Texture powerUp = new Texture(Gdx.files.internal("data/spritepack/powerUp.png"));
    public Texture coin = new Texture(Gdx.files.internal("data/spritepack/coin.png"));
    public Texture pearl = new Texture(Gdx.files.internal("data/spritepack/pearl.png"));
    public Sprite upgradeOutline = atlas.createSprite("upgradeoutline");
    public Sprite healthNone = atlas.createSprite("healthnone");
    public Sprite healthSome = atlas.createSprite("healthsome");
    public Sprite healthFull = atlas.createSprite("healthfull");
    public Sprite aButton = atlas.createSprite("abutton");
    public Sprite aCpuButton = atlas.createSprite("acpubutton");
    public Sprite aPlayerButton = atlas.createSprite("aplayerbutton");
    public Sprite cpuButton = atlas.createSprite("cpubutton");
    public Sprite playerButton = atlas.createSprite("playerbutton");
    public Sprite cnt1 = atlas.createSprite("1");
    public Sprite cnt2 = atlas.createSprite("2");
    public Sprite cnt3 = atlas.createSprite("3");
    public Sprite spark = atlas.createSprite("spark");
    public Sprite bubble = atlas.createSprite("bubble");
    public Sprite bigbubble = atlas.createSprite("bigbubble");
    public Sprite explosion = atlas.createSprite("explosion");
    public Sprite help = atlas.createSprite("help");
    public Sprite musicOnOff = atlas.createSprite("music");
    public Sprite back = atlas.createSprite("back");
    public Sprite settings = atlas.createSprite("settings");
    public Sprite checkboxOn = atlas.createSprite("checkboxon");
    public Sprite checkboxOff = atlas.createSprite("checkboxoff");

    public Resources() {
        reInit();
    }

    public static Resources getInstance() {
        if (instance == null) {
            instance = new Resources();
        }
        return instance;
    }

    public void reInit() {
        dispose();

        Preferences prefs = Gdx.app.getPreferences("paxbritannica");
        if (prefs.getInteger("antiAliasConfig", 1) == 0) {
            atlas = new TextureAtlas(Gdx.files.internal("data/spritepack/pack.pack"));
        } else {
            atlas = new TextureAtlas(Gdx.files.internal("data/spritepack/packhigh.pack"));
        }

        try {
            if (music != null) {
                music.stop();
                music.dispose();
            }
            music = Gdx.audio.newMusic(Gdx.files.internal("data/audio/music.mp3"));
        } catch (Exception e) {
            music = Gdx.audio.newMusic(Gdx.files.internal("data/audio/music.mp3"));
        }

        bullet = new Texture(Gdx.files.internal("data/spritepack/mine.png"));


        factoryP1 = atlas.createSprite("factoryp3");
        factoryP2 = atlas.createSprite("factoryp4");
        factoryP3 = atlas.createSprite("factoryp2");


        factoryP1Small = atlas.createSprite("factoryp3");
        factoryP2Small = atlas.createSprite("factoryp4");
        factoryP3Small = atlas.createSprite("factoryp2");


        debrisSmall = atlas.createSprite("debrissmall");
        debrisMed = atlas.createSprite("debrismed");
        debrisLarge = atlas.createSprite("debrislarge");

        fish1 = atlas.createSprite("fish1");
        fish2 = atlas.createSprite("fish2");
        fish3 = atlas.createSprite("fish3");
        fish4 = atlas.createSprite("fish4");
        fish5 = atlas.createSprite("fish5");
        fish6 = atlas.createSprite("fish6");
        fish7 = atlas.createSprite("fish7");
        fish8 = atlas.createSprite("fish8");


        background = atlas.createSprite("background");

        blackFade = atlas.createSprite("blackfade");


        healthNone = atlas.createSprite("healthnone");
        healthSome = atlas.createSprite("healthsome");
        healthFull = atlas.createSprite("healthfull");

        aButton = atlas.createSprite("abutton");
        aCpuButton = atlas.createSprite("acpubutton");
        aPlayerButton = atlas.createSprite("aplayerbutton");

        cpuButton = atlas.createSprite("cpubutton");
        playerButton = atlas.createSprite("playerbutton");

        cnt1 = atlas.createSprite("1");
        cnt2 = atlas.createSprite("2");
        cnt3 = atlas.createSprite("3");


        spark = atlas.createSprite("spark");
        bubble = atlas.createSprite("bubble");
        bigbubble = atlas.createSprite("bigbubble");
        explosion = atlas.createSprite("explosion");


        fighter = atlas.createSprite("arrows");

        help = atlas.createSprite("help");
        musicOnOff = atlas.createSprite("music");
        back = atlas.createSprite("back");
        settings = atlas.createSprite("settings");
        checkboxOn = atlas.createSprite("checkboxon");
        checkboxOff = atlas.createSprite("checkboxoff");

        title = new Sprite(new Texture(Gdx.files.internal("data/spritepack/title.png")));
        title.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        //	credits = new Sprite(new Texture(Gdx.files.internal("data/spritepack/credits.png")));
        //	credits.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    }

    public void dispose() {
        atlas.dispose();
    }

}
