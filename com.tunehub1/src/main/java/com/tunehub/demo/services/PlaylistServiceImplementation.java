package com.tunehub.demo.services;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.demo.repositories.PlaylistRepository;
import com.tunehub.demo.entities.Playlist;


@Service
public class PlaylistServiceImplementation 
		implements PlaylistService
		{
@Autowired 
PlaylistRepository repo;
	@Override
	public void addPlaylist(Playlist playlist) {
		repo.save(playlist);		
	}
	@Override
	public List<Playlist> fetchAllPlaylists() {
		
		return repo.findAll();
	} 
		
	

}