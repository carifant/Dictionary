CREATE TABLE dictionary (
    id  serial PRIMARY KEY,
    word  varchar(40)
);

CREATE TABLE translation (
    id serial PRIMARY KEY,
    translated_word  varchar(40),
	dictionary_id serial REFERENCES dictionary(id)
	ON DELETE CASCADE
);
