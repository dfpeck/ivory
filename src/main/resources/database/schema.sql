-- Data tables --
CREATE TABLE Texts (
  TextId IDENTITY NOT NULL,
  FullTitle VARCHAR(32767) NOT NULL,
  DisplayTitle VARCHAR(255) DEFAULT NULL,
  SortTitle VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (TextId)
);

CREATE TRIGGER texts_default_fields_before_insert
  BEFORE INSERT ON Texts
  FOR EACH ROW AS
  $$org.h2.api.Trigger create()
  {return new ivory.database.triggers.DefaultFromFieldTrigger(java.util.Map.of(2, 1, 3, 1));}$$;

CREATE TRIGGER texts_default_fields_after_update
  BEFORE UPDATE ON Texts
  FOR EACH ROW AS
  $$org.h2.api.Trigger create()
  {return new ivory.database.triggers.DefaultFromFieldTrigger(java.util.Map.of(2, 1, 3, 1));}$$;

CREATE TABLE Authors (
  AuthorId IDENTITY NOT NULL,
  FullName VARCHAR(255) NOT NULL,
  DisplayName VARCHAR(255) DEFAULT NULL,
  SortName VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (AuthorId)
);

CREATE TRIGGER authors_default_fields_before_insert
  BEFORE INSERT ON Authors
  FOR EACH ROW AS
  $$org.h2.api.Trigger create()
  {return new ivory.database.triggers.DefaultFromFieldTrigger(java.util.Map.of(2, 1, 3, 1));}$$;

CREATE TRIGGER authors_default_fields_after_update
  BEFORE UPDATE ON Authors
  FOR EACH ROW AS
  $$org.h2.api.Trigger create()
  {return new ivory.database.triggers.DefaultFromFieldTrigger(java.util.Map.of(2, 1, 3, 1));}$$;

CREATE TABLE Tags (
  TagId IDENTITY NOT NULL,
  TagName VARCHAR(255) NOT NULL,
  Comment VARCHAR(2047) DEFAULT NULL,
  PRIMARY KEY (TagId)
);


-- Join tables --
CREATE TABLE TextAuthorRelations (
  TextId LONG NOT NULL,
  AuthorId LONG NOT NULL,
  FOREIGN KEY (TextId) REFERENCES Texts(TextId),
  FOREIGN KEY (AuthorId) REFERENCES Authors(AuthorId),
  PRIMARY KEY (TextId, AuthorId)
);

CREATE TABLE TextTagRelations (
  TextId LONG NOT NULL,
  TagId LONG NOT NULL,
  FOREIGN KEY (TextId) REFERENCES Texts(TextId),
  FOREIGN KEY (TagId) REFERENCES Tags(TagId),
  PRIMARY KEY (TextId, TagId)
);

CREATE TABLE AuthorTagRelations (
  AuthorId LONG NOT NULL,
  TagId LONG NOT NULL,
  FOREIGN KEY (AuthorId) REFERENCES Authors(AuthorId),
  FOREIGN KEY (TagId) REFERENCES Tags(TagId),
  PRIMARY KEY (AuthorId, TagId)
);
