INSERT INTO game (game_state, name, game_description, nw_lat, nw_lng, se_lat, se_lng) VALUES ('registration', 'Zombies of the Caribbean', 'A zombie game taking place in the tropics with a pirate theme.', 0, 0, 0, 0);
INSERT INTO game (game_state, name, game_description, nw_lat, nw_lng, se_lat, se_lng) VALUES ('registration', 'Zombies', 'A plain old game of Humans VS Zombies.', 0, 0, 0, 0);
INSERT INTO game (game_state, name, game_description, nw_lat, nw_lng, se_lat, se_lng) VALUES ('registration', 'Terror', 'This is a game of true terror. There are HOW MANY patient zeroes? I thought there could only be one?!', 0, 0, 0, 0);
INSERT INTO game (game_state, name, game_description, nw_lat, nw_lng, se_lat, se_lng) VALUES ('registration', 'War of the Worlds', 'The war is on. Humans outnumber zombies by a factor of 1000s, but for how long? No one can predict the outcome of this one.', 0, 0, 0, 0);
INSERT INTO game (game_state, name, game_description, nw_lat, nw_lng, se_lat, se_lng) VALUES ('registration', 'World War Z', 'A large group of zombies have been building up in a remote area and now theyre headed for the last human colony. How long can they hold out?', 0, 0, 0, 0);
INSERT INTO game (game_state, name, game_description, nw_lat, nw_lng, se_lat, se_lng) VALUES ('registration', 'Zombies oPlenty', 'You thought WWZ was bad. In this game, the zombies outnumber the humans by at least 3 to 1. Will the humans be able to pull a rabbit out of the hat on this one? Everyone is holding their breath.', 0, 0, 0, 0);


INSERT INTO users (first_name, last_name, keycloak, email) VALUES ('Test', 'Osterone', '12345678-1234-1234-1234-AndersAnsder', 'anders.and@gmail.com');
INSERT INTO users (first_name, last_name, keycloak, email) VALUES ('Thomas', 'Doe', '12345678-1234-1234-1234-ThomasThomas', 'thomas@gmail.com');
INSERT INTO users (first_name, last_name, keycloak, email) VALUES ('Kasper', 'Skidespræller', '12345678-1234-1234-1234-KasperKasper', 'Kasper@gmail.com');
INSERT INTO users (first_name, last_name, keycloak, email) VALUES ('Christian', 'bstian', '12345678-1234-1234-1234-123456789112', 'kongChr@gmail.com');
INSERT INTO users (first_name, last_name, keycloak, email) VALUES ('Anders', 'Mand', '12345678-1234-1234-1234-Mandmandmand', 'anders.mand@gmail.com');
INSERT INTO users (first_name, last_name, keycloak, email) VALUES ('John', 'John', '12345678-1234-1234-1234-JohnJohnJohn', 'John@john.john');

INSERT INTO player (bite_code, is_human, is_patient_zero, user_id, game_id) VALUES ('1234', 'n', 'y', 1, 1);
INSERT INTO player (bite_code, is_human, is_patient_zero, user_id, game_id) VALUES ('2345', 'n', 'n', 2, 1);
INSERT INTO player (bite_code, is_human, is_patient_zero, user_id, game_id) VALUES ('3456', 'y', 'n', 3, 1);
INSERT INTO player (bite_code, is_human, is_patient_zero, user_id, game_id) VALUES ('4567', 'y', 'n', 4, 1);
INSERT INTO player (bite_code, is_human, is_patient_zero, user_id, game_id) VALUES ('5678', 'y', 'n', 2, 3);
INSERT INTO player (bite_code, is_human, is_patient_zero, user_id, game_id) VALUES ('6789', 'n', 'n', 2, 4);
INSERT INTO player (bite_code, is_human, is_patient_zero, user_id, game_id) VALUES ('7890', 'y', 'n', 3, 4);
INSERT INTO player (bite_code, is_human, is_patient_zero, user_id, game_id) VALUES ('8901', 'n', 'n', 4, 2);
