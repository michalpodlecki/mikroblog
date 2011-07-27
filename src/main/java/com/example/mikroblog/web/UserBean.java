package com.example.mikroblog.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.mikroblog.domain.Komentarz;
import com.example.mikroblog.domain.Post;
import com.example.mikroblog.domain.User;
import com.example.mikroblog.service.UserManager;

@SessionScoped
@Named
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	protected boolean czyZalogowany = false;

	public boolean isCzyZalogowany() {
		return czyZalogowany;
	}

	public void setCzyZalogowany(boolean czyZalogowany) {
		this.czyZalogowany = czyZalogowany;
	}
	
	private boolean sudo = false; // post (edycja, usuwanie, dodawanie)

	public boolean isSudo() {
		return sudo;
	}

	public void setSudo(boolean sudo) {
		this.sudo = sudo;
	}

	@Inject
	UserManager userManager;

	private User user = new User();
	private User zalogowany = new User();
	private Post post = new Post();
	private Komentarz komentarz = new Komentarz();

	public User getZalogowany() {
		return zalogowany;
	}

	public void setZalogowany(User zalogowany) {
		this.zalogowany = zalogowany;
	}

	public Komentarz getKomentarz() {
		return komentarz;
	}

	public void setKomentarz(Komentarz komentarz) {
		this.komentarz = komentarz;
	}

	String tmp;
	
	DataModel<Post> posty = new ListDataModel<Post>();
	DataModel<Komentarz> komentarze = new ListDataModel<Komentarz>();

	public DataModel<Komentarz> getKomentarze() {
		komentarze.setWrappedData(userManager.pobierzWszystkieKomentarze(post));
		return komentarze;
	}

	public void setKomentarze(DataModel<Komentarz> komentarze) {
		this.komentarze = komentarze;
	}

	public DataModel<Post> getPosty() {
		posty.setWrappedData(userManager.pobierzWszystkiePosty(user));
		return posty;
	}

	public void setPosty(DataModel<Post> posty) {
		this.posty = posty;
	}

	/* lista userów - pocz¹tek */
	DataModel<User> users = new ListDataModel<User>();
	
	public DataModel<User> getUsers() {
		users.setWrappedData(userManager.pobierzWszystkichUserow());
		return users;
	}

	public void setUsers(DataModel<User> users) {
		this.users = users;
	}
	/* lista userów - koniec */
	
	

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/* logowanie */
	public String zaloguj() {
		user = new User();
		return "logowanie";
	}
	
	public String logIn() {
		User doZalogowania = new User();
		doZalogowania.setLogin(user.getLogin());
		doZalogowania.setHaslo(user.getHaslo());
		if (userManager.zaloguj(doZalogowania) != null) {
			setZalogowany(userManager.zaloguj(doZalogowania));
			setUser(userManager.zaloguj(doZalogowania));
			setCzyZalogowany(true);
			setSudo(true);
			return pokazPosty();
		} else {
			return zaloguj();
		}
	}

	/* wylogowywanie */
	public String logOut() {
		//resetuj();
		zalogowany = new User();
		setCzyZalogowany(false);
		return zaloguj();
	}

	public String register() {
		/* this. */
		User x = new User();
		x.setLogin(user.getLogin());
		x.setHaslo(user.getHaslo());
		x.setImie(user.getImie());
		x.setNazwisko(user.getNazwisko());
		x.setRok_urodzenia(user.getRok_urodzenia());
		//x.setPosty(posty);
		userManager.addUser(x);
		return "logowanie";
	}

	public String edytujUsera() {
		user = new User();user = zalogowany;
		tmp = user.getHaslo();
		return "edituser";
	}

	/* edycja danych zalogowanego usera */
	public String edit() {
		User toEdit = new User();
		toEdit = user;
		toEdit.setHaslo(tmp);
		userManager.edytujUsera(toEdit);
		return "posty";
	}
	
	public String dodajPosta() {
		Post p = new Post();
		p.setTytul(post.getTytul());
		p.setTresc(post.getTresc());
		userManager.addPost(zalogowany, p);
		post = new Post();
		return "posty";
	}
	
	public String pokazPost() {
		setPost(posty.getRowData());
		return "showpost";
	}
	
	public String pokazPosty() {
		post = new Post();
		return "posty";
	}
	
	public String edytujPost() {
		setPost(posty.getRowData());
		return "editpost";
	}
	
	public String postEdit() {
		userManager.editPost(post);
		return pokazPosty();
	}
	
	public String usunPosta() {
		Post p = new Post();
		p = posty.getRowData();
		userManager.removePost(p);
		return "posty";
	}
	
	public String dodajKomentarz() {
		Komentarz k = new Komentarz();
		k.setAutor(komentarz.getAutor());
		k.setTresc(komentarz.getTresc());
		userManager.addKomentarz(post, k);
		komentarz = new Komentarz();
		return "showpost";
	}
	
	public String usunKomentarz() {
		Komentarz k = new Komentarz();
		k = komentarze.getRowData();
		userManager.removeKomentarz(k);
		return "showpost";
	}

	/*
	public void resetuj() {
		login = "";
		haslo = "";
	}
	*/
	
	public String pokazMojProfil() {
		this.user = new User();
		this.user = zalogowany;
		return pokazPosty();
	}
	
	public String pokazProfil() {
		user = new User();
		user = users.getRowData();
		
		// jesli posty naleza do usera zalogowanego, daj mu prawa sudo
		if(user.getLogin().equals(zalogowany.getLogin())) {
			setSudo(true);
		} else {
			setSudo(false);
		}
		return pokazPosty();
	}
}
