package com.machinecoding.playlistmanager.repository.impl;

import com.machinecoding.playlistmanager.entity.Song;
import com.machinecoding.playlistmanager.repository.SongRepository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SongRepositoryImpl implements SongRepository {

    private static SongRepository songRepository = new SongRepositoryImpl();
    private static List<Song> songList = new ArrayList<>();

    private SongRepositoryImpl() {
        songList = getSampleData();
    }

    public static SongRepository getInstance() {
        return songRepository;
    }

    private static List<Song> getSampleData() {
        List<Song> temp = new ArrayList<>();
        temp.add(new Song(1L, "a", Arrays.asList("Arijit"), Arrays.asList("romance"), 2021, 0, null, 30));
        temp.add(new Song(2L, "b", Arrays.asList("KK"), Arrays.asList("romance"), 2022, 0, null, 40));
        temp.add(new Song(3L, "c", Arrays.asList("Kailash"), Arrays.asList("sad"), 2022, 0, null, 180));
        temp.add(new Song(4L, "d", Arrays.asList("Arijit"), Arrays.asList("sad"), 2021, 0, null, 240));
        return temp;
    }

    @Override
    public Song getSongById(Long id) {
        return songList.stream().filter(song -> song.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Song> getSongByList(List<Long> idList) {
        return songList.stream().filter(song -> idList.contains(song.getId())).collect(Collectors.toList());
    }

    @Override
    public List<Song> getSongByFilter(List<Long> songIds, String artist, String songName, String genre,
                                      Integer releaseYear) {
        List<Song> response = new ArrayList<>();
        for (Song song : songList) {
            boolean flag = StringUtils.hasLength(artist) && song.getArtist().contains(artist);
            if (!songIds.contains(song.getId())) {
                flag = false;
            } else if (StringUtils.hasLength(songName) && !song.getName().equals(songName)) {
                flag = false;
            } else if (StringUtils.hasLength(genre) && !song.getGenre().contains(genre)) {
                flag = false;
            } else if (Objects.nonNull(releaseYear) && !song.getReleaseYear().equals(releaseYear)) {
                flag = false;
            }
            if (flag) {
                response.add(song);
            }
        }
        return response;
    }

    @Override
    public List<Song> getSongByFrequency() {
        return songList.stream().sorted((song1, song2) -> song2.getPlayedCount() - song1.getPlayedCount()).collect(Collectors.toList()).subList(0, 10);
    }

    @Override
    public List<Song> getSongByRecency() {
        return songList.stream().sorted((song1, song2) -> song2.checkLastPlayedTime(song1.getLastPlayedTime())).collect(Collectors.toList()).subList(0, 10);
    }

    @Override
    public Song playSong(Long id) {
        Song response = getSongById(id);
        if (Objects.nonNull(response)) {
            response.setLastPlayedTime(LocalDateTime.now());
            response.setPlayedCount(response.getPlayedCount() + 1);
        }
        return response;
    }
}
