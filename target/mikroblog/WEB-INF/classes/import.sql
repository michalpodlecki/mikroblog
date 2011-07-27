INSERT INTO USER(id, login, haslo, imie, nazwisko, rok_urodzenia) VALUES(1,'mpodleck','x','Micha³','Podlecki',1989);
INSERT INTO USER(id, login, haslo, imie, nazwisko, rok_urodzenia) VALUES(2,'akowal','x','Adam','Kowalski',1974);
INSERT INTO USER(id, login, haslo, imie, nazwisko, rok_urodzenia) VALUES(3,'kfrywoln','x','Kazimierz','Frywolny',2001);

INSERT INTO POST(tytul, tresc, user_id) VALUES('Pierwszy wpis', 'Cz³owiek napotkawszy przeszkodê, której nie mo¿e zniszczyæ - zaczyna niszczyæ samego siebie..',1);
INSERT INTO POST(tytul, tresc, user_id) VALUES('Druga notka', 'Im cz³owiek ma wiêksz¹ g³êbie w sobie, tym wiêksza pustka mo¿e j¹ wype³niæ.. ',1);
INSERT INTO POST(tytul, tresc, user_id) VALUES('Einstein ma na bani', 'Nie wiem jaka broñ bêdzie u¿yta w trzeciej wojnie œwiatowej, ale czwarta bêdzie na kije i kamienie.',1);

INSERT INTO KOMENTARZ(autor, tresc, post_id) VALUES('KAZIUK','heheheheh',1);
INSERT INTO KOMENTARZ(autor, tresc, post_id) VALUES('Mieczys³aw','nie rozumiem, co w tym œmiesznego ?',1);

INSERT INTO KOMENTARZ(autor, tresc, post_id) VALUES('Natalia','Zauroczy³am siê...',2);