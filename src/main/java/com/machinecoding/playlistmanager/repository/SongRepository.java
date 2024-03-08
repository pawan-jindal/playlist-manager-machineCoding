package com.machinecoding.playlistmanager.repository;

import com.machinecoding.playlistmanager.entity.Song;

import java.util.List;

public interface SongRepository {
    Song getSongById(Long id);
    List<Song> getSongByList(List<Long> idList);
    List<Song> getSongByFilter(List<Long> songIds, String artist, String songName, String genre, Integer releaseYear);
    List<Song> getSongByFrequency();
    List<Song> getSongByRecency();
    Song playSong(Long id);
}
