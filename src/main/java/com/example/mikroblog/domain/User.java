package com.example.mikroblog.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name="user.login", query="from User user where user.login=:login AND user.haslo=:haslo"),
	@NamedQuery(name="user.all", query="from User")
})
public class User {

	private long id;
	
	private String login;
	private String haslo;
	private String imie, nazwisko;
	private int rok_urodzenia;
	
	private List<Post> posty;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getHaslo() {
		return haslo;
	}
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}	
	public int getRok_urodzenia() {
		return rok_urodzenia;
	}
	public void setRok_urodzenia(int rok_urodzenia) {
		this.rok_urodzenia = rok_urodzenia;
	}

	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	public List<Post> getPosty() {
		return posty;
	}
	public void setPosty(List<Post> posty) {
		this.posty = posty;
	}
	
	
}
