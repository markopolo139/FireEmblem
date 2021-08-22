create table user_info(
    user_id int  not null primary key auto_increment,
    login varchar(100) not null,
    password varchar(150) not null,
    email varchar(100) not null,
    password_token varchar(100) null,
    enabled boolean not null
);

create table user_roles(
    user_id int not null,
    `role` varchar(50) not null,
    constraint role_to_user foreign key (user_id) references user_info (user_id)
);

create table games(
    game_id int not null primary key auto_increment,
    user_id int not null,
    difficulty_setting ENUM('EASY','NORMAL','HARD') not null,
    constraint game_to_user foreign key (user_id) references user_info (user_id)
);

create table characters(
    character_id int not null primary key auto_increment,
    name varchar(50) not null,
    `level` int not null,
    exp int not null,
    remaining_health int not null,
    current_equipped_item_id int null,
    character_class ENUM('GREAT_LORD','LORD','PALADIN','CAVALRY','GENERAL','KNIGHT','SWORDMASTER','MYRMIDON','WARRIOR',
                           'FIGHTER','HERO','MERCENARY','SNIPER','ARCHER','SAGE','MAGE','WAR_MONK','PRIEST') not null,
    character_state ENUM('ALIVE','DEAD') not null,
    moved boolean not null,
    game_id int not null,
    constraint character_to_game foreign key (game_id) references games (game_id)
);

create table enemies(
    enemy_id int not null primary key auto_increment,
    name varchar(50) not null,
    `level` int not null,
    exp int not null,
    remaining_health int not null,
    current_equipped_item_id int null,
    character_class ENUM('GREAT_LORD','LORD','PALADIN','CAVALRY','GENERAL','KNIGHT','SWORDMASTER','MYRMIDON','WARRIOR',
                           'FIGHTER','HERO','MERCENARY','SNIPER','ARCHER','SAGE','MAGE','WAR_MONK','PRIEST') not null,
    character_state ENUM('ALIVE','DEAD') not null,
    moved boolean not null,
    drop_item_id int null,
    boss boolean not null,
    gold_drop int not null,
    game_id int not null,
    constraint enemy_to_game foreign key (game_id) references games (game_id)
);

create table character_stats(
    character_id int not null,
    key (character_id),
    stat_type ENUM('HEALTH','STRENGTH','MAGICK','DEFENSE','RESISTANCE','LUCK','SKILL','SPEED') not null,
    `value` int not null,
    increase_chance int not null,
    constraint stat_to_character foreign key (character_id) references characters (character_id)
);

create table enemies_stats(
    enemy_id int not null,
    key (enemy_id),
    stat_type ENUM('HEALTH','STRENGTH','MAGICK','DEFENSE','RESISTANCE','LUCK','SKILL','SPEED') not null,
    `value` int not null,
    increase_chance int not null,
    constraint stat_to_enemy foreign key (enemy_id) references enemies (enemy_id)
);

create table character_equipment_weapons(
    character_id int not null,
    key (character_id),
    name varchar(50) not null,
    rank int not null,
    might int not null,
    hit int not null,
    avo int not null,
    crit int not null,
    uses int not null,
    `range` int not null,
    worth int not null,
    item_category ENUM('SWORD','AXE','LANCE','TOME','BOW','STAFF') not null,
    constraint weapon_to_character foreign key (character_id) references characters (character_id)
);

create table enemies_equipment_weapons(
    enemy_id int not null,
    key (enemy_id),
    name varchar(50) not null,
    rank int not null,
    might int not null,
    hit int not null,
    avo int not null,
    crit int not null,
    uses int not null,
    `range` int not null,
    worth int not null,
    item_category ENUM('SWORD','AXE','LANCE','TOME','BOW','STAFF') not null,
    constraint weapon_to_enemy foreign key (enemy_id) references enemies (enemy_id)
);

create table character_equipment_heal_items(
    character_id int not null,
    key (character_id),
    heal_type ENUM('VULNERARY','CONCOCTIONS','ELIXIR','MEND','HEAL') not null,
    uses int not null,
    constraint heal_to_character foreign key (character_id) references characters (character_id)
);

create table enemies_equipment_heal_items(
    enemy_id int not null,
    key (enemy_id),
    heal_type ENUM('VULNERARY','CONCOCTIONS','ELIXIR','MEND','HEAL') not null,
    uses int not null,
    constraint heall_to_enemy foreign key (enemy_id) references enemies (enemy_id)
);

create table character_equipment_seals(
    character_id int not null,
    key (character_id),
    seal_type ENUM('MASTER_SEAL','HEART_SEAL') not null,
    constraint seal_to_character foreign key (character_id) references characters (character_id)
);

create table enemies_equipment_seals(
    enemy_id int not null,
    key (enemy_id),
    seal_type ENUM('MASTER_SEAL','HEART_SEAL') not null,
    constraint seal_to_enemy foreign key (enemy_id) references enemies (enemy_id)
);

create table character_equipment_stats_up(
    character_id int not null,
    key (character_id),
    stat_up_type ENUM('STRENGTH_UP','MAGICK_UP','DEFENSE_UP','RESISTANCE_UP','LUCK_UP','SKILL_UP','SPEED_UP') not null,
    constraint stat_up_to_character foreign key (character_id) references characters (character_id)
);

create table enemies_equipment_stats_up(
    enemy_id int not null,
    key (enemy_id),
    stat_up_type ENUM('STRENGTH_UP','MAGICK_UP','DEFENSE_UP','RESISTANCE_UP','LUCK_UP','SKILL_UP','SPEED_UP') not null,
    constraint stat_up_to_enemy foreign key (enemy_id) references enemies (enemy_id)
);

create table character_weapon_progress(
    character_id int not null,
    key (character_id),
    weapon_type ENUM('SWORD','AXE','LANCE','TOME','BOW','STAFF') not null,
    progress int not null,
    rank int not null,
    constraint weapon_progress_to_character foreign key (character_id) references characters (character_id)
);

create table enemies_weapon_progress(
    enemy_id int not null,
    key (enemy_id),
    weapon_type ENUM('SWORD','AXE','LANCE','TOME','BOW','STAFF') not null,
    progress int not null,
    rank int not null,
    constraint weapon_progress_to_enemy foreign key (enemy_id) references enemies (enemy_id)
);

create table spots(
    spot_id int not null primary key auto_increment,
    spot_type ENUM('FORREST','FORT','GATE','GRASS','PLAIN') not null,
    height int not null,
    width int not null,
    character_id int null,
    enemy_id int null,
    game_id int not null,
    constraint spot_to_character foreign key (character_id) references characters (character_id),
    constraint spot_to_enemy foreign key (enemy_id) references enemies (enemy_id),
    constraint spot_to_game foreign key (game_id) references games (game_id)

);

create table all_healing_items(
    heal_item_id int not null primary key auto_increment,
    heal_type ENUM('VULNERARY','CONCOCTIONS','ELIXIR','MEND','HEAL') not null,
    uses int not null
);

CREATE TABLE all_weapons (
    weapon_id int not null primary key auto_increment,
    name varchar(50) not null,
    rank int not null,
    might int not null,
    hit int not null,
    avo int not null,
    crit int not null,
    uses int not null,
    `range` int not null,
    worth int not null,
    item_category ENUM('SWORD','AXE','LANCE','TOME','BOW','STAFF') not null,
    quality int not null
);

CREATE TABLE base_characters (
    base_character_id int not null primary key auto_increment,
    name varchar(50) not null,
    `level` int not null default 1,
    exp int not null default 0,
    remaining_health int not null,
    current_equipped_item_id int null default null,
    character_class ENUM('GREAT_LORD','LORD','PALADIN','CAVALRY','GENERAL','KNIGHT','SWORDMASTER','MYRMIDON','WARRIOR',
                           'FIGHTER','HERO','MERCENARY','SNIPER','ARCHER','SAGE','MAGE','WAR_MONK','PRIEST') not null,
    character_state ENUM('ALIVE','DEAD') not null default 'ALIVE',
    moved boolean not null default false
);

create table base_character_stats(
    base_character_id int not null,
    key (base_character_id),
    stat_type ENUM('HEALTH','STRENGTH','MAGICK','DEFENSE','RESISTANCE','LUCK','SKILL','SPEED') not null,
    `value` int not null,
    increase_chance int not null,
    constraint stat_to_base_character foreign key (base_character_id) references base_characters (base_character_id)
);

create table player_convoy(
    convoy_id int not null primary key auto_increment,
    money int not null,
    game_id int not null,
    constraint convoy_to_game foreign key (game_id) references games (game_id)
);

CREATE TABLE player_healing_items(
    convoy_id int not null ,
    key (convoy_id),
    heal_type ENUM('VULNERARY','CONCOCTIONS','ELIXIR','MEND','HEAL') not null,
    uses int not null,
    constraint heal_to_convoy foreign key (convoy_id) references player_convoy (convoy_id)
);

create table player_weapons(
    convoy_id int not null ,
    key (convoy_id),
    name varchar(50) not null,
    rank int not null,
    might int not null,
    hit int not null,
    avo int not null,
    crit int not null,
    uses int not null,
    `range` int not null,
    worth int not null,
    item_category ENUM('SWORD','AXE','LANCE','TOME','BOW','STAFF') not null,
    constraint weapon_to_convoy foreign key (convoy_id) references player_convoy (convoy_id)
);

CREATE TABLE player_seals(
    convoy_id int not null ,
    key (convoy_id),
    seal_type ENUM('MASTER_SEAL','HEART_SEAL') not null,
    constraint seal_to_convoy foreign key (convoy_id) references player_convoy (convoy_id)
);

create table player_stat_up_items(
    convoy_id int not null,
    key (convoy_id),
    stat_up_type ENUM('STRENGTH_UP','MAGICK_UP','DEFENSE_UP','RESISTANCE_UP','LUCK_UP','SKILL_UP','SPEED_UP') not null,
    constraint stat_up_to_convoy foreign key (convoy_id) references player_convoy (convoy_id)
);

INSERT INTO `all_healing_items` (`heal_item_id`, `heal_type`, `uses`) VALUES
(1, 'VULNERARY', 3),
(2, 'CONCOCTIONS', 2),
(3, 'ELIXIR', 1),
(4, 'MEND', 20),
(5, 'HEAL', 30);

INSERT INTO `all_weapons` (`weapon_id`, `name`, `rank`, `might`, `hit`, `avo`, `crit`, `uses`, `range`, `worth`, `item_category`, `quality`) VALUES
(1, 'Bronze Sword', 1, 3, 100, 0, 0, 50, 1, 350, 'SWORD', 1),
(2, 'Iron Sword', 2, 5, 95, 0, 0, 40, 1, 520, 'SWORD', 2),
(3, 'Steel Sword', 3, 8, 90, 0, 0, 35, 1, 840, 'SWORD', 3),
(4, 'Silver Sword', 4, 11, 85, 0, 0, 30, 1, 1410, 'SWORD', 4),
(5, 'Killing Edge', 3, 9, 90, 5, 30, 30, 1, 1470, 'SWORD', 3),
(6, 'Bronze Lance', 1, 3, 90, 0, 0, 50, 1, 350, 'LANCE', 1),
(7, 'Iron Lance', 2, 6, 85, 0, 0, 40, 1, 560, 'LANCE', 2),
(8, 'Steel Lance', 3, 9, 80, 0, 0, 35, 1, 910, 'LANCE', 3),
(9, 'Silver Lance', 4, 13, 75, 0, 0, 30, 1, 1560, 'LANCE', 4),
(10, 'Killer Lance', 3, 10, 80, 3, 30, 30, 1, 1680, 'LANCE', 3),
(11, 'Bronze Axe', 1, 4, 80, 0, 0, 50, 1, 400, 'AXE', 1),
(12, 'Iron Axe', 2, 7, 75, 0, 0, 40, 1, 600, 'AXE', 2),
(13, 'Steel Axe', 3, 11, 70, 0, 0, 35, 1, 980, 'AXE', 3),
(14, 'Silver Axe', 4, 15, 65, 0, 0, 30, 1, 1740, 'AXE', 4),
(15, 'Killer Axe', 3, 14, 70, 5, 35, 30, 1, 1860, 'AXE', 3),
(16, 'Bronze Bow', 1, 3, 90, 0, 0, 50, 2, 350, 'BOW', 1),
(17, 'Iron Bow', 2, 6, 85, 0, 0, 40, 2, 560, 'BOW', 2),
(18, 'Steel Bow', 3, 9, 80, 0, 0, 35, 2, 910, 'BOW', 3),
(19, 'Silver Bow', 4, 13, 75, 0, 0, 30, 2, 1560, 'BOW', 4),
(20, 'Killer Bow', 3, 10, 80, 0, 40, 30, 2, 1680, 'BOW', 3),
(21, 'Fire', 1, 2, 90, 0, 0, 45, 2, 540, 'TOME', 1),
(22, 'Elfire', 2, 5, 85, 0, 0, 35, 2, 980, 'TOME', 2),
(23, 'Arcfire', 3, 8, 80, 0, 0, 30, 2, 1440, 'TOME', 3),
(24, 'Bolganone', 4, 12, 75, 0, 0, 25, 2, 2000, 'TOME', 4),
(25, 'Excalibur', 3, 11, 85, 0, 30, 25, 2, 2100, 'TOME', 3);

INSERT INTO `base_characters` (`base_character_id`, `name`, `level`, `exp`, `remaining_health`, `current_equipped_item_id`, `character_class`, `character_state`, `moved`) VALUES
(1, 'Chrom', 1, 0, 19, NULL, 'LORD', 'ALIVE', 0),
(2, 'Lissa', 1, 0, 17, NULL, 'PRIEST', 'ALIVE', 0),
(3, 'Virion', 1, 0, 17, NULL, 'ARCHER', 'ALIVE', 0),
(4, 'Stahl', 1, 0, 19, NULL, 'CAVALRY', 'ALIVE', 0),
(5, 'Vaike', 1, 0, 21, NULL, 'FIGHTER', 'ALIVE', 0),
(6, 'Miriel', 1, 0, 17, NULL, 'MAGE', 'ALIVE', 0),
(7, 'Kellam', 1, 0, 19, NULL, 'KNIGHT', 'ALIVE', 0),
(8, 'Lon\'qu', 1, 0, 17, NULL, 'MYRMIDON', 'ALIVE', 0),
(9, 'Ricken', 1, 0, 17, NULL, 'MAGE', 'ALIVE', 0),
(10, 'Anna', 1, 0, 19, NULL, 'MERCENARY', 'ALIVE', 0);

INSERT INTO `base_character_stats` (`base_character_id`, `stat_type`, `value`, `increase_chance`) VALUES
(1, 'HEALTH', 3, 45),
(1, 'STRENGTH', 3, 40),
(1, 'MAGICK', 3, 10),
(1, 'DEFENSE', 3, 35),
(1, 'RESISTANCE', 3, 20),
(1, 'LUCK', 3, 70),
(1, 'SKILL', 3, 40),
(1, 'SPEED', 3, 40),
(2, 'HEALTH', 3, 35),
(2, 'STRENGTH', 3, 25),
(2, 'MAGICK', 3, 35),
(2, 'DEFENSE', 3, 15),
(2, 'RESISTANCE', 3, 35),
(2, 'LUCK', 3, 65),
(2, 'SKILL', 3, 30),
(2, 'SPEED', 3, 35),
(3, 'HEALTH', 3, 35),
(3, 'STRENGTH', 3, 40),
(3, 'MAGICK', 3, 30),
(3, 'DEFENSE', 3, 25),
(3, 'RESISTANCE', 3, 25),
(3, 'LUCK', 3, 40),
(3, 'SKILL', 3, 40),
(3, 'SPEED', 3, 45),
(4, 'HEALTH', 3, 50),
(4, 'STRENGTH', 3, 45),
(4, 'MAGICK', 3, 10),
(4, 'DEFENSE', 3, 50),
(4, 'RESISTANCE', 3, 10),
(4, 'LUCK', 3, 50),
(4, 'SKILL', 3, 35),
(4, 'SPEED', 3, 30),
(5, 'HEALTH', 3, 60),
(5, 'STRENGTH', 3, 50),
(5, 'MAGICK', 3, 10),
(5, 'DEFENSE', 3, 40),
(5, 'RESISTANCE', 3, 5),
(5, 'LUCK', 3, 45),
(5, 'SKILL', 3, 45),
(5, 'SPEED', 3, 35),
(6, 'HEALTH', 3, 35),
(6, 'STRENGTH', 3, 15),
(6, 'MAGICK', 3, 40),
(6, 'DEFENSE', 3, 20),
(6, 'RESISTANCE', 3, 30),
(6, 'LUCK', 3, 50),
(6, 'SKILL', 3, 40),
(6, 'SPEED', 3, 40),
(7, 'HEALTH', 3, 50),
(7, 'STRENGTH', 3, 40),
(7, 'MAGICK', 3, 15),
(7, 'DEFENSE', 3, 55),
(7, 'RESISTANCE', 3, 30),
(7, 'LUCK', 3, 35),
(7, 'SKILL', 3, 40),
(7, 'SPEED', 3, 35),
(8, 'HEALTH', 3, 40),
(8, 'STRENGTH', 3, 35),
(8, 'MAGICK', 3, 20),
(8, 'DEFENSE', 3, 25),
(8, 'RESISTANCE', 3, 20),
(8, 'LUCK', 3, 55),
(8, 'SKILL', 3, 50),
(8, 'SPEED', 3, 50),
(9, 'HEALTH', 3, 50),
(9, 'STRENGTH', 3, 20),
(9, 'MAGICK', 3, 35),
(9, 'DEFENSE', 3, 30),
(9, 'RESISTANCE', 3, 25),
(9, 'LUCK', 3, 65),
(9, 'SKILL', 3, 30),
(9, 'SPEED', 3, 30),
(10, 'HEALTH', 3, 45),
(10, 'STRENGTH', 3, 30),
(10, 'MAGICK', 3, 30),
(10, 'DEFENSE', 3, 30),
(10, 'RESISTANCE', 3, 30),
(10, 'LUCK', 3, 80),
(10, 'SKILL', 3, 35),
(10, 'SPEED', 3, 35);