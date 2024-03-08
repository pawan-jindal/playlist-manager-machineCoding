# README #
### What is this repository for? ###
* Song playlist-manager system for Machine coding round.
* It will focus on the design of a song playlist-manager like Wynk, Spotify, and others. But this design will be a much smaller scale.

### About Code ###
* Implemented using core-Java, so flow will be run at Terminal/console.
* PlaylistManagerApplication.java: Is the driver/runner file or starting point of the service/code.
* For now code is not runnable due to time constraint but satisfy most of the requirement.

### Requirements of Playlist manager application ###
Let’s first understand the requirements.

### Mandatory Requirements ###
The application should take as input (from the command line or a file).
* User Profile(assume creation user already provided so create only abstraction layer):
  * Create user
  * Remove user
* Basic Functions specific to a user profile:
  * Add/create a new Playlist
  * Remove a Playlist
  * Add a song to a playlist
  * Remove a song from the playlist
  * List all songs in a playlist
  * List all playlists and their songs
  * Display the total duration of the song
  * Display the total duration of the playlist
  * Play a Playlist
* Filters based on Artist, Song, Genre, Year of Release: A user should be able to filter songs across his own playlists on the basis of above filters.
* Shuffle: Treat is as a method which on getting called will return you a random song from the playlist.
* Recommendations to be done Across User profiles: Top 10 Played Songs (On the basis of Frequency, On the basis of Recency).