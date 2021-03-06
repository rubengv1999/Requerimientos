USE [Mini Google]
GO
/****** Object:  StoredProcedure [dbo].[MakeQuery]    Script Date: 01/11/2018 20:35:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[MakeQuery]
	@query VARCHAR(MAX)
AS
BEGIN
	SET NOCOUNT ON;
	WITH QueryWords AS(
		SELECT Word.IDWord, Word.IDF
		FROM Word
		WHERE Word.Palabra IN (SELECT * FROM string_split(@query,' '))
	)SELECT TOP 20
		D.Titulo, D.Texto,SUM(TF.Cantidad/ QW.IDF) AS TF_IDF 
	FROM QueryWords QW
	INNER JOIN TF ON (TF.FK_Word = QW.IDWord)
	RIGHT JOIN Document D ON (TF.FK_Document = D.IDDocumento)
	WHERE TF.Cantidad IS NOT NULL
	GROUP BY D.Titulo, D.Texto
	
	ORDER BY TF_IDF DESC
	
END