package ventus.rggwheel.services.audio;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MediaPlayerService {
    private final Map<AudioPlayerEnum, MediaPlayer> players;
    private int musicTime = 10;

    public enum AudioPlayerEnum {
        MUSIC, SOUND_CLIPS, SFX, BUTTON
    }

    static class SoundDescription {
        final String filename;
        final Duration duration;

        public SoundDescription(String filename, Duration duration) {
            this.filename = filename;
            this.duration = duration;
        }

        public String getFilename() {
            return filename;
        }

        public Duration getDuration() {
            return duration;
        }
    }

    private final Double GLOBAL_VOLUME = 0.7;
    File buttonSoundFile = new File(System.getProperty("user.dir") + "/sound/button.mp3");
    MediaPlayer buttonSound = new MediaPlayer(new Media(buttonSoundFile.toPath().toUri().toString()));

    Map<SoundDescription, Media> sfxMap = load("sound/sfx/");
    Map<SoundDescription, Media> soundClipsMap = load("sound/clips/");
    Map<SoundDescription, Media> musicMap = load("sound/music/");

    public MediaPlayerService() {
        players = new HashMap<>();
        players.put(AudioPlayerEnum.SFX, null);
        players.put(AudioPlayerEnum.MUSIC, null);
        players.put(AudioPlayerEnum.SOUND_CLIPS, null);
    }

    public void play(AudioPlayerEnum player, String fileName) {
        try {
            switch (player) {
                case MUSIC:
                    System.out.println(musicTime);
                    List<SoundDescription> suitableMusic = musicMap.keySet().stream().filter(desc -> Math.round(desc.duration.toSeconds()) >= musicTime).collect(Collectors.toList());
                    Collections.shuffle(suitableMusic);
                    if(suitableMusic.size()>0) {
                        Media randomMusic = musicMap.get(suitableMusic.get(0));
                        System.out.println(randomMusic.getSource());
                        players.replace(AudioPlayerEnum.MUSIC, getAudioPlayer(randomMusic));
                        players.get(AudioPlayerEnum.MUSIC).play();
                    }
                    break;
                case SOUND_CLIPS:
                    players.replace(AudioPlayerEnum.SOUND_CLIPS, getAudioPlayer(getByFilename(AudioPlayerEnum.SOUND_CLIPS, fileName)));
                    players.get(AudioPlayerEnum.SOUND_CLIPS).play();
                    break;
                case SFX:
                    players.replace(AudioPlayerEnum.SFX, getAudioPlayer(getByFilename(AudioPlayerEnum.SFX, fileName)));
                    players.get(AudioPlayerEnum.SFX).play();
                    break;
                case BUTTON:
                    buttonSound.seek(Duration.ZERO);
                    buttonSound.play();
                    break;
            }
        } catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }


    public void fadeout(AudioPlayerEnum player, double fadeoutTime) {
        MediaPlayer currentPlayer = null;
        switch (player) {
            case MUSIC:
                currentPlayer = players.get(AudioPlayerEnum.MUSIC);
                break;
            case SOUND_CLIPS:
                currentPlayer = players.get(AudioPlayerEnum.SOUND_CLIPS);
                break;
            case SFX:
                currentPlayer = players.get(AudioPlayerEnum.SFX);
                break;
        }
        new Timeline(
                new KeyFrame(Duration.seconds(fadeoutTime),
                        new KeyValue(currentPlayer.volumeProperty(), 0))).play();
    }


    public void setVolume(AudioPlayerEnum player, Double volume) {
        try {
            players.get(player).setVolume(volume);
        } catch (NullPointerException e) {
            System.out.println("Player has not been initialized");
        }
    }

    public void seek(AudioPlayerEnum player, Duration destination) {
        try {
            players.get(player).seek(destination);
        } catch (NullPointerException e) {
            System.out.println("Player has not been initialized");
        }
    }

    public void stop(AudioPlayerEnum player) {
        try {
            players.get(player).stop();
        } catch (NullPointerException e) {
            System.out.println("Player has not been initialized");
        }
    }

    public void replay(AudioPlayerEnum player) {
        try {
            MediaPlayer currentPlayer = players.get(player);
            currentPlayer.seek(new Duration(0));
            currentPlayer.play();
        } catch (NullPointerException e) {
            System.out.println("Player has not been initialized");
        }
    }

    private Map<SoundDescription, Media> load(String directoryPath) {
        File mainDir = new File (System.getProperty("user.dir"));
        Map<SoundDescription, Media> mediaMap = new HashMap<>();
        List<Path> result;
        try (Stream<Path> walk = Files.walk(Paths.get(mainDir.toURI().resolve(directoryPath)))) {
            result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
            result.forEach(currentFilePath -> {
                Media currentMedia = new Media(currentFilePath.toUri().toString());
                MediaPlayer currentPlayer = new MediaPlayer(currentMedia);
                currentPlayer.setOnReady(() -> {
                    System.out.println(currentMedia.getSource() + " Duration: "+currentMedia.getDuration().toSeconds());
                    SoundDescription currentDesc = new SoundDescription(currentFilePath.getFileName().toString(), currentMedia.getDuration());
                    mediaMap.put(currentDesc, new Media(currentFilePath.toUri().toString()));
                });
                SoundDescription currentDesc = new SoundDescription(currentFilePath.getFileName().toString(), currentMedia.getDuration());
                mediaMap.put(currentDesc, new Media(currentFilePath.toUri().toString()));
            });
            System.out.println("Directory: " + directoryPath + " Number of files: " + mediaMap.size());
            return mediaMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    private MediaPlayer getAudioPlayer(Media media) {
        MediaPlayer player = new MediaPlayer(media);
        player.setVolume(GLOBAL_VOLUME);
        return player;
    }

    public void setMusicTime(int musicTime) {
        this.musicTime = musicTime;
    }
    
    private Media getByFilename(AudioPlayerEnum audioType, String filename){
        Map<SoundDescription, Media> map;
        switch (audioType) {
            case MUSIC:
                map = musicMap;
                break;
            case SOUND_CLIPS:
                map = soundClipsMap;
                break;
            case SFX:
                map = sfxMap;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + audioType);
        }
        SoundDescription desc = map.keySet().stream().filter(soundDescription -> soundDescription.getFilename().equalsIgnoreCase(filename)).findFirst().orElse(null);
        if(desc==null) throw new IllegalArgumentException("Missing audio file for filename: " + filename);
        return map.get(desc);
    }
}