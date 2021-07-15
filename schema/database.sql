create table characters(
    character_id int not null primary key auto_increment,
    name varchar(50) not null,
    `level` int not null,
    exp int not null,
    remaining_health int not null,
    current_equiped_item_id int null,
    character_class ENUM('GREAT_LORD','LORD','PALADIN','CAVALRY','GENERAL','KNIGHT','SWORDMASTER','MYRMIDON','WARRIOR',
                           'FIGHTER','HERO','MERCENARY','SNIPER','ARCHER','SAGE','MAGE','WAR_MONK','PRIEST') not null,
    character_state ENUM('ALIVE','DEAD') not null,
    moved boolean not null
);

create table enemies(
    enemy_id int not null primary key auto_increment,
    name varchar(50) not null,
    `level` int not null,
    exp int not null,
    remaining_health int not null,
    current_equiped_item_id int null,
    character_class ENUM('GREAT_LORD','LORD','PALADIN','CAVALRY','GENERAL','KNIGHT','SWORDMASTER','MYRMIDON','WARRIOR',
                           'FIGHTER','HERO','MERCENARY','SNIPER','ARCHER','SAGE','MAGE','WAR_MONK','PRIEST') not null,
    character_state ENUM('ALIVE','DEAD') not null,
    moved boolean not null,
    drop_item_id int not null,
    boss boolean not null,
    gold_drop int not null
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
    constraint spot_to_character foreign key (character_id) references characters (character_id),
    constraint spot_to_enemy foreign key (enemy_id) references enemies (enemy_id)
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
    itemCategory ENUM('SWORD','AXE','LANCE','TOME','BOW','STAFF') not null
);

CREATE TABLE base_characters (
    base_character_id int not null primary key auto_increment,
    name varchar(50) not null,
    `level` int not null default 1,
    exp int not null default 0,
    remaining_health int not null,
    current_equiped_item_id int null default null,
    character_class ENUM('GREAT_LORD','LORD','PALADIN','CAVALRY','GENERAL','KNIGHT','SWORDMASTER','MYRMIDON','WARRIOR',
                           'FIGHTER','HERO','MERCENARY','SNIPER','ARCHER','SAGE','MAGE','WAR_MONK','PRIEST') not null,
    character_state ENUM('ALIVE','DEAD') not null default 'ALIVE',
    moved boolean not null default false
);

create table player_convoy(
    convoy_id int not null primary key auto_increment
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
    itemCategory ENUM('SWORD','AXE','LANCE','TOME','BOW','STAFF') not null,
    constraint weapon_to_convoy foreign key (convoy_id) references player_convoy (convoy_id)
);

CREATE TABLE player_stat_up_items(
    convoy_id int not null ,
    key (convoy_id),
    seal_type ENUM('MASTER_SEAL','HEART_SEAL') not null,
    constraint seal_to_convoy foreign key (convoy_id) references player_convoy (convoy_id)
);

create table player_seals(
    convoy_id int not null,
    key (convoy_id),
    stat_up_type ENUM('STRENGTH_UP','MAGICK_UP','DEFENSE_UP','RESISTANCE_UP','LUCK_UP','SKILL_UP','SPEED_UP') not null,
    constraint stat_up_to_convoy foreign key (convoy_id) references player_convoy (convoy_id)
);



