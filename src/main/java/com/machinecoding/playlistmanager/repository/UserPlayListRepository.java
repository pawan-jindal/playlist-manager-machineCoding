package com.machinecoding.playlistmanager.repository;

import com.machinecoding.playlistmanager.entity.PlayList;
import com.machinecoding.playlistmanager.entity.Song;

import java.util.List;
import java.util.Map;

public interface UserPlayListRepository {
    List<PlayList> getPlayListByUser(Long userId);
    PlayList createPlayList(Long userId, String playListName);
    boolean removePlayList(Long userId, String playListName);
    List<Song> getAllSongsOfPlayList(Long userId, String playListName);
    Map<String, List<Song>> getAllSongsOfAllPlayListByUser(Long userId);
    void addSongToPlayList(Long userId, String playListName, Long songId);
    void removeSongToPlayList(Long userId, String playListName, Long songId);
    List<Song> getSongByFilter(Long userId, String artist, String songName, String genre, Integer releaseYear);

    Song getShuffleSong(Long userId, String playListName);
}
