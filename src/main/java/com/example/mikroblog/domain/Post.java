package com.example.mikroblog.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name="posty.all", query="from Post post where post.user=:user")
public class Post {

	private long id;
	
	private String tytul;
	private String tresc;
	
	// mappedBy -> User.java
	private User user;
	
	private List<Komentarz> komentarze;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTytul() {
		return tytul;
	}
	public void setTytul(String tytul) {
		this.tytul = tytul;
	}
	public String getTresc() {
		return tresc;
	}
	public void setTresc(String tresc) {
		this.tresc = tresc;
	}
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Komentarz> getKomentarze() {
		return komentarze;
	}
	public void setKomentarze(List<Komentarz> komentarze) {
		this.komentarze = komentarze;
	}
		
}
