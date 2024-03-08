package com.machinecoding.playlistmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayList {
    private Long id; // primary key
    private Long userId;
    private String playListName;
    private List<Long> songList; // primary key of song
}
