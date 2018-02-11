

-- ----------------------------
-- Sequence structure for id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."id_seq";
CREATE SEQUENCE "public"."id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 15
 CACHE 1;
SELECT setval('"public"."id_seq"', 15, true);

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS "public"."author";
CREATE TABLE "public"."author" (
"id" int8 DEFAULT nextval('id_seq'::regclass) NOT NULL,
"fio" varchar(300) COLLATE "default" NOT NULL,
"birthday" date NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS "public"."book";
CREATE TABLE "public"."book" (
"id" int8 NOT NULL,
"name" varchar(45) COLLATE "default" NOT NULL,
"content" bytea,
"page_count" int4 NOT NULL,
"isbn" varchar(100) COLLATE "default" NOT NULL,
"genre_id" int8 NOT NULL,
"author_id" int8 NOT NULL,
"publish_year" int4 NOT NULL,
"publisher_id" int8 NOT NULL,
"image" bytea,
"descr" text COLLATE "default",
"rating" int2,
"vote_count" int2
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for genre
-- ----------------------------
DROP TABLE IF EXISTS "public"."genre";
CREATE TABLE "public"."genre" (
"id" int8 DEFAULT nextval('id_seq'::regclass) NOT NULL,
"name" varchar(100) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS "public"."groups";
CREATE TABLE "public"."groups" (
"GROUPID" varchar(255) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for publisher
-- ----------------------------
DROP TABLE IF EXISTS "public"."publisher";
CREATE TABLE "public"."publisher" (
"id" int8 DEFAULT nextval('id_seq'::regclass) NOT NULL,
"name" varchar(100) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
"userid" varchar(255) COLLATE "default" NOT NULL,
"password" varchar(255) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for users_groups
-- ----------------------------
DROP TABLE IF EXISTS "public"."users_groups";
CREATE TABLE "public"."users_groups" (
"GROUPID" varchar(255) COLLATE "default" NOT NULL,
"USERID" varchar(255) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS "public"."vote";
CREATE TABLE "public"."vote" (
"id" int4 NOT NULL,
"value" int4 NOT NULL,
"book_id" int4 NOT NULL,
"username" varchar(255) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table author
-- ----------------------------
ALTER TABLE "public"."author" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table book
-- ----------------------------
ALTER TABLE "public"."book" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table genre
-- ----------------------------
ALTER TABLE "public"."genre" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table groups
-- ----------------------------
ALTER TABLE "public"."groups" ADD PRIMARY KEY ("GROUPID");

-- ----------------------------
-- Primary Key structure for table publisher
-- ----------------------------
ALTER TABLE "public"."publisher" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD PRIMARY KEY ("userid");

-- ----------------------------
-- Primary Key structure for table users_groups
-- ----------------------------
ALTER TABLE "public"."users_groups" ADD PRIMARY KEY ("GROUPID", "USERID");

-- ----------------------------
-- Primary Key structure for table vote
-- ----------------------------
ALTER TABLE "public"."vote" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."book"
-- ----------------------------
ALTER TABLE "public"."book" ADD FOREIGN KEY ("genre_id") REFERENCES "public"."genre" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."book" ADD FOREIGN KEY ("publisher_id") REFERENCES "public"."publisher" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."book" ADD FOREIGN KEY ("author_id") REFERENCES "public"."author" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."users_groups"
-- ----------------------------
ALTER TABLE "public"."users_groups" ADD FOREIGN KEY ("USERID") REFERENCES "public"."users" ("userid") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."users_groups" ADD FOREIGN KEY ("GROUPID") REFERENCES "public"."groups" ("GROUPID") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."vote"
-- ----------------------------
ALTER TABLE "public"."vote" ADD FOREIGN KEY ("book_id") REFERENCES "public"."book" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."vote" ADD FOREIGN KEY ("username") REFERENCES "public"."users" ("userid") ON DELETE CASCADE ON UPDATE CASCADE;
