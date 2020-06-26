SELECT Texts.FullTitle AS Title, Authors.FullName as Author
  FROM Texts
  INNER JOIN TextAuthorRelations ON TextAuthorRelations.TextId=Texts.TextId
  INNER JOIN Authors ON TextAuthorRelations.AuthorId=Authors.AuthorId;
