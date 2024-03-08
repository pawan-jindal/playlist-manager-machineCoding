package com.machinecoding.playlistmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Song {
    private Long id; // primary key
    private String name;
    private List<String> artist;
    private List<String> genre;
    private Integer releaseYear;
    private Integer playedCount;
    private LocalDateTime lastPlayedTime;
    private Integer duration; // in seconds

    public int checkLastPlayedTime(LocalDateTime song2){
        if(this.getLastPlayedTime().isBefore(song2)){
            return -1;
        } else if(this.getLastPlayedTime().isEqual(song2)){
            return 0;
        }else{
            return 1;
        }
    }
}
