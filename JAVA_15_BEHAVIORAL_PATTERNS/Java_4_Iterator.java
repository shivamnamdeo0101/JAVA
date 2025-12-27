package JAVA_15_BEHAVIORAL_PATTERNS;

import java.util.ArrayList;
import java.util.List;

/**
 * ITERATOR PATTERN – PLAYLIST EXAMPLE
 */
public class Java_4_Iterator {
    public static void main(String[] args) {

        Playlist playlist = new Playlist();
        playlist.addSong("Song1");
        playlist.addSong("Song2");
        playlist.addSong("Song3");

        SongIterator it = playlist.getIterator();
        while(it.hasNext()) {
            System.out.println("Playing: " + it.next());
        }
    }
}

// ==================== AGGREGATE ====================
interface SongCollection {
    SongIterator getIterator();
}

// ==================== ITERATOR ====================
interface SongIterator {
    boolean hasNext();
    String next();
}

// ==================== CONCRETE COLLECTION ====================
class Playlist implements SongCollection {
    private final List<String> songs = new ArrayList<>();

    public void addSong(String song) { songs.add(song); }

    @Override
    public SongIterator getIterator() { return new PlaylistIterator(songs); }
}

// ==================== CONCRETE ITERATOR ====================
class PlaylistIterator implements SongIterator {
    private final List<String> songs;
    private int position = 0;

    public PlaylistIterator(List<String> songs) { this.songs = songs; }

    @Override public boolean hasNext() { return position < songs.size(); }

    @Override public String next() { return songs.get(position++); }
}

/*
================ ITERATOR PATTERN – FEATURES & DESIGN =================
WHAT:
- Provides sequential access to elements without exposing internal representation.
- Example: Playlist iteration.
WHY IT EXISTS:
- Decouples traversal logic from collection.
REAL SYSTEM USAGE:
- Java Collections (List, Set), data streaming, menu navigation
*/
