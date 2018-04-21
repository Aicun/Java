package com.lac.spring.aop;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements CompactDisc {

	private String title;
	private String artist;
	private String publisher;
	private List<String> tracks;
	
	public SgtPeppers(){}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public List<String> getTracks() {
		return tracks;
	}

	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}

	@Override
	public void play(int trackNumber) {
		System.out.println("Playing " + title + " by " + artist);
	}
	
	public SgtPeppers(
		@Value("${disc.title}") String title,
		@Value("${disc.artist}") String artist) {
		this.title = title;
		this.artist = artist;
	}
	
	public SgtPeppers(
			@Value("#systemProperties['disc.title']") String title,
			@Value("#systemProperties['disc.artist']") String artist,
			@Value("#systemProperties['disc.publisher']")String publisher) {
			this.title = title;
			this.artist = artist;
			this.publisher = publisher;
		}
}
