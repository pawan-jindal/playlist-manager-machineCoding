package com.machinecoding.playlistmanager.repository.impl;

import com.machinecoding.playlistmanager.entity.PlayList;
import com.machinecoding.playlistmanager.entity.Song;
import com.machinecoding.playlistmanager.repository.SongRepository;
import com.machinecoding.playlistmanager.repository.UserPlayListRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class UserPlayListRepositoryImpl implements UserPlayListRepository {

    private static UserPlayListRepository userPlayListRepository;
    private static List<PlayList> playLists;
    private SongRepository songRepository;
    private static long id = 1L;
    Random random = new Random();

    private UserPlayListRepositoryImpl() {
        playLists = new ArrayList<>();
        songRepository = SongRepositoryImpl.getInstance();
    }

    public static UserPlayListRepository getInstance() {
        if (userPlayListRepository == null) {
            userPlayListRepository = new UserPlayListRepositoryImpl();
        }
        return userPlayListRepository;
    }

    @Override
    public List<PlayList> getPlayListByUser(Long userId) {
        return playLists.stream()
                .filter(playList -> playList.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public PlayList createPlayList(Long userId, String playListName) {
        PlayList list = new PlayList(id++, userId, playListName, new ArrayList<>());
        playLists.add(list);
        return list;
    }

    @Override
    public boolean removePlayList(Long userId, String playListName) {
        return playLists.removeIf(playList -> playList.getUserId().equals(userId) && playList.getPlayListName().equals(playListName));
    }

    @Override
    public List<Song> getAllSongsOfPlayList(Long userId, String playListName) {
        PlayList response = getPlayList(userId, playListName);
        if (Objects.isNull(response)) {
            return Collections.emptyList();
        }
        return songRepository.getSongByList(response.getSongList());
    }

    @Override
    public Map<String, List<Song>> getAllSongsOfAllPlayListByUser(Long userId) {
        List<PlayList> playLists = getPlayListByUser(userId);
        if (Objects.isNull(playLists) || playLists.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, List<Song>> response = new HashMap<>();
        for (PlayList playList : playLists) {
            response.put(playList.getPlayListName(), songRepository.getSongByList(playList.getSongList()));
        }
        return response;
    }

    @Override
    public void addSongToPlayList(Long userId, String name, Long songId) {
        PlayList playList = getPlayList(userId, name);
        if (Objects.nonNull(playList)) {
            playList.getSongList().add(songId);
        }
    }

    @Override
    public void removeSongToPlayList(Long userId, String name, Long songId) {
        PlayList playList = getPlayList(userId, name);
        if (Objects.nonNull(playList)) {
            playList.getSongList().remove(songId);
        }
    }

    @Override
    public List<Song> getSongByFilter(Long userId, String artist, String songName, String genre, Integer releaseYear) {
        return songRepository.getSongByFilter(getSongIdsOfUsersAllPlayList(userId), artist, songName, genre,
                releaseYear);
    }

    @Override
    public Song getShuffleSong(Long userId, String playListName) {
        PlayList playList = getPlayList(userId, playListName);
        if(Objects.isNull(playList)){
            return null;
        }
        List<Long> songIdList = playList.getSongList();
        if (Objects.isNull(songIdList) || songIdList.isEmpty()) {
            return null;
        }
        return songRepository.getSongById(songIdList.get(random.nextInt(songIdList.size())));
    }

    private PlayList getPlayList(Long userId, String playListName) {
        return playLists.stream()
                .filter(playList -> playList.getUserId().equals(userId) && playList.getPlayListName().equals(playListName))
                .findFirst().orElse(null);
    }

    private List<Long> getSongIdsOfUsersAllPlayList(Long userId) {
        List<PlayList> playLists = getPlayListByUser(userId);
        if (Objects.isNull(playLists) || playLists.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> songList = new ArrayList<>();
        for (PlayList playList : playLists) {
            songList.addAll(playList.getSongList());
        }
        return songList;
    }
}
