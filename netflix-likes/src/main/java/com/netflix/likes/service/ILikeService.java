package com.netflix.likes.service;

public interface ILikeService {
	
	void serieLike(String emailUser, Long id_serie, String like);
	
	void movieLike(String emailUser, Long id_movie, String like);

}
