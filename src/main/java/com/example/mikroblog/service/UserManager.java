package com.example.mikroblog.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.mikroblog.domain.Komentarz;
import com.example.mikroblog.domain.Post;
import com.example.mikroblog.domain.User;

@Stateless
public class UserManager {

	@PersistenceContext
	EntityManager em;
	
	public void addUser(User u) {
		User user = new User();
		List<Post> posty = new ArrayList<Post>();
		
		user.setLogin(u.getLogin());
		user.setHaslo(u.getHaslo());
		user.setImie(u.getImie());
		user.setNazwisko(u.getNazwisko());
		user.setRok_urodzenia(u.getRok_urodzenia());
		user.setPosty(posty);
		
		em.persist(user);
	}
	

	public User zaloguj(User u) {
		User doZalogowania = new User();
		try {
			doZalogowania = (User) em.createNamedQuery("user.login").setParameter("login", u.getLogin()).setParameter("haslo", u.getHaslo()).getSingleResult();					//find(User.class, u.getId());
			if(doZalogowania != null) {
				return doZalogowania;
			}
		} catch (Exception e) {}
		return null;
		
	}
	
	public void edytujUsera(User u) {
		em.merge(u);
	}
	
	public void addPost(User u, Post p) {
		User user = new User();
		user.setId(u.getId());
		Post post = new Post();
		post = p;
		post.setUser(user);
		List<Komentarz> komentarze = new ArrayList<Komentarz>();
		post.setKomentarze(komentarze);
		em.persist(post);
	}
	
	public void editPost(Post p) {
		em.merge(p);
	}
	
	public void removePost(Post p) {
		Post doUsuniecia = new Post();
		doUsuniecia = em.find(Post.class, p.getId());
		em.remove(doUsuniecia);
	}
	
	public void addKomentarz(Post p, Komentarz k) {
		Post post = new Post();
		post.setId(p.getId());
		Komentarz komentarz = new Komentarz();
		komentarz = k;
		komentarz.setPost(post);
		em.persist(komentarz);
	}
	
	public void removeKomentarz(Komentarz k) {
		Komentarz doUsuniecia = new Komentarz();
		doUsuniecia = em.find(Komentarz.class, k.getId());
		em.remove(doUsuniecia);
	}
	
	public List<User> pobierzWszystkichUserow() {
		return em.createNamedQuery("user.all").getResultList();
	}
	
	public List<Post> pobierzWszystkiePosty(User u) {
		return em.createNamedQuery("posty.all").setParameter("user", u).getResultList();
	}
	
	public List<Post> pobierzWszystkieKomentarze(Post p) {
		return em.createQuery("from Komentarz k where k.post=:post").setParameter("post", p).getResultList();
	}
}
