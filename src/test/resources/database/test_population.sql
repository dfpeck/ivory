-- Insert Authors --
INSERT INTO Authors (FullName, SortName)
  VALUES ('Safiya Noble', 'Noble Safiya'),
         ('Brian Kernighan', 'Kernighan Brian'),
         ('Dennis Ritchie', 'Ritchie Dennis'),
         ('Fred Brooks', 'Brooks Fred');

-- Insert Texts --
INSERT INTO Texts (FullTitle)
  VALUES ('Algorithms of Oppression');

INSERT INTO Texts (FullTitle, SortTitle)
  VALUES ('The C Programming Language', 'C Programming Language The');

INSERT INTO Texts (FullTitle, DisplayTitle, SortTitle)
  VALUES ('The Mythical Man-Month: Essays on Software Engineering',
          'The Mythical Man-Month', 'Mythical Man-Month The');

-- Relate Texts to Authors --
INSERT INTO TextAuthorRelations (TextId, AuthorId)
  SELECT Texts.TextId, Authors.AuthorId
  FROM Texts, Authors
  WHERE Texts.FullTitle='Algorithms of Oppression' AND Authors.FullName='Safiya Noble';

INSERT INTO TextAuthorRelations (TextId, AuthorId)
  SELECT Texts.TextId, Authors.AuthorId
  FROM Texts, Authors
  WHERE Texts.FullTitle='The C Programming Language'
        AND (Authors.FullName='Brian Kernighan' OR Authors.FullName='Dennis Ritchie');

INSERT INTO TextAuthorRelations (TextId, AuthorId)
  SELECT Texts.TextId, Authors.AuthorId
  FROM Texts, Authors
  WHERE Texts.FullTitle='The Mythical Man-Month: Essays on Software Engineering'
        AND Authors.FullName='Fred Brooks';
