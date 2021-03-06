USE [Mini Google]
GO
/****** Object:  StoredProcedure [dbo].[Search]    Script Date: 01/11/2018 20:35:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Search]
AS
BEGIN
	SET NOCOUNT ON;

    SELECT 
		D.ID,
		1 AS TF_IDF
	FROM Document D
	LEFT JOIN TF ON(TF.FK_Document = D.ID)
	INNER JOIN Word ON (Word.ID = TF.FK_Word)
	ORDER BY TF_IDF DESC
END
