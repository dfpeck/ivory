INSERT INTO Authors (FullName, SortName)
  VALUES ('Friedrich Engels', 'Engels Friedrich'),
         ('Peter Kropotkin', 'Kropotkin Peter'),
         ('Rosa Luxemburg', 'Luxemburg Rosa'),
         ('Karl Marx', 'Marx Karl');

INSERT INTO Texts (FullTitle, SortTitle)
  VALUES ('The Accumulation of Capital', 'Accumulation of Capital The'),
         ('The Communist Manifesto', 'Communist Manifesto The'),
         ('The Conquest of Bread', 'Conquest of Bread The');

INSERT INTO TextAuthorRelations (TextId, AuthorId)
  SELECT Texts.TextId, Authors.AuthorId
  FROM Texts, Authors
  WHERE Texts.FullTitle='The Conquest of Bread' AND Authors.FullName='Peter Kropotkin';

INSERT INTO TextAuthorRelations (TextId, AuthorId)
  SELECT Texts.TextId, Authors.AuthorId
  FROM Texts, Authors
  WHERE Texts.FullTitle='The Accumulation of Capital' AND Authors.FullName='Rosa Luxemburg';

INSERT INTO TextAuthorRelations (TextId, AuthorId)
  SELECT Texts.TextId, Authors.AuthorId
  FROM Texts, Authors
  WHERE Texts.FullTitle='The Communist Manifesto'
        AND (Authors.FullName='Karl Marx' OR Authors.FullName='Friedrich Engels');
