INSERT INTO USER(id, login, haslo, imie, nazwisko, rok_urodzenia) VALUES(1,'mpodleck','x','Micha�','Podlecki',1989);
INSERT INTO USER(id, login, haslo, imie, nazwisko, rok_urodzenia) VALUES(2,'akowal','x','Adam','Kowalski',1974);
INSERT INTO USER(id, login, haslo, imie, nazwisko, rok_urodzenia) VALUES(3,'kfrywoln','x','Kazimierz','Frywolny',2001);

INSERT INTO POST(tytul, tresc, user_id) VALUES('Pierwszy wpis', 'Cz�owiek napotkawszy przeszkod�, kt�rej nie mo�e zniszczy� - zaczyna niszczy� samego siebie..',1);
INSERT INTO POST(tytul, tresc, user_id) VALUES('Druga notka', 'Im cz�owiek ma wi�ksz� g��bie w sobie, tym wi�ksza pustka mo�e j� wype�ni�.. ',1);
INSERT INTO POST(tytul, tresc, user_id) VALUES('Einstein ma na bani', 'Nie wiem jaka bro� b�dzie u�yta w trzeciej wojnie �wiatowej, ale czwarta b�dzie na kije i kamienie.',1);

INSERT INTO KOMENTARZ(autor, tresc, post_id) VALUES('KAZIUK','heheheheh',1);
INSERT INTO KOMENTARZ(autor, tresc, post_id) VALUES('Mieczys�aw','nie rozumiem, co w tym �miesznego ?',1);

INSERT INTO KOMENTARZ(autor, tresc, post_id) VALUES('Natalia','Zauroczy�am si�...',2);