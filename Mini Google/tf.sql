
USE [Mini Google]

INSERT INTO dbo.Document 
SELECT book.summary, book.reviewText
FROM OPENROWSET (BULK 'C:\Users\ruben\desktop\mini.json', SINGLE_CLOB) as j
CROSS APPLY OPENJSON (BulkColumn)
WITH ( summary varchar(100), reviewText varchar(MAX)) AS book

DECLARE @IDDocumento  int, @Texto varchar(MAX)
DECLARE cursorDocumento cursor GLOBAL for SELECT IDDocumento, Texto  FROM dbo.Document
OPEN cursorDocumento
FETCH cursorDocumento INTO @IDDocumento , @Texto
WHILE(@@fetch_status=0)
BEGIN	
	DECLARE @Palabra varchar(50)
	DECLARE cursorTabla cursor GLOBAL for SELECT value FROM STRING_SPLIT(@Texto, ' ') WHERE RTRIM(value) <> '';  
	OPEN cursorTabla
	FETCH cursorTabla INTO @Palabra
	WHILE(@@fetch_status=0)
	BEGIN
		DECLARE @IDWord int;
		IF NOT EXISTS (SELECT * FROM dbo.Word WHERE Palabra = @Palabra) 
		BEGIN
			INSERT INTO dbo.Word VALUES (@Palabra, 0)
		END
		SELECT @IDWord = IDWord  FROM dbo.Word WHERE Palabra = @Palabra
		IF NOT EXISTS (SELECT * FROM dbo.TF WHERE FK_Word = @IDWord AND FK_Document = @IDDocumento) 
		BEGIN
			INSERT INTO dbo.TF VALUES (@IDDocumento, @IDWord, 1)
		END
		ELSE
		BEGIN
			UPDATE dbo.TF SET Cantidad = Cantidad + 1 WHERE FK_Word = @IDWord AND FK_Document = @IDDocumento
		END
		UPDATE dbo.Word SET IDF = IDF + 1 WHERE Palabra = @Palabra
		FETCH cursorTabla INTO @Palabra
	END
	CLOSE cursorTabla
	DEALLOCATE cursorTabla
	FETCH cursorDocumento INTO @IDDocumento , @Texto
END
CLOSE cursorDocumento
DEALLOCATE cursorDocumento

