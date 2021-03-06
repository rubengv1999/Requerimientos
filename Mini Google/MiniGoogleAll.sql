USE [master]
GO
/****** Object:  Database [Mini Google]    Script Date: 30/10/2018 16:49:16 ******/
CREATE DATABASE [Mini Google]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Mini Google', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\Mini Google.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Mini Google_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\Mini Google_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Mini Google] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Mini Google].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Mini Google] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Mini Google] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Mini Google] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Mini Google] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Mini Google] SET ARITHABORT OFF 
GO
ALTER DATABASE [Mini Google] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Mini Google] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Mini Google] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Mini Google] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Mini Google] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Mini Google] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Mini Google] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Mini Google] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Mini Google] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Mini Google] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Mini Google] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Mini Google] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Mini Google] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Mini Google] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Mini Google] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Mini Google] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Mini Google] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Mini Google] SET RECOVERY FULL 
GO
ALTER DATABASE [Mini Google] SET  MULTI_USER 
GO
ALTER DATABASE [Mini Google] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Mini Google] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Mini Google] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Mini Google] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Mini Google] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Mini Google', N'ON'
GO
ALTER DATABASE [Mini Google] SET QUERY_STORE = OFF
GO
USE [Mini Google]
GO
/****** Object:  Table [dbo].[Document]    Script Date: 30/10/2018 16:49:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Document](
	[ID] [int] NOT NULL,
	[Binary] [varchar](max) NOT NULL,
	[Type] [int] NOT NULL,
 CONSTRAINT [PK_Document] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TF]    Script Date: 30/10/2018 16:49:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TF](
	[FK_Document] [int] NOT NULL,
	[FK_Word] [int] NOT NULL,
	[Cantidad] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Word]    Script Date: 30/10/2018 16:49:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Word](
	[ID] [int] NOT NULL,
	[Word] [varchar](50) NOT NULL,
	[IDF] [int] NOT NULL,
 CONSTRAINT [PK_Word] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[TF]  WITH CHECK ADD  CONSTRAINT [FK_TF_Document] FOREIGN KEY([FK_Document])
REFERENCES [dbo].[Document] ([ID])
GO
ALTER TABLE [dbo].[TF] CHECK CONSTRAINT [FK_TF_Document]
GO
ALTER TABLE [dbo].[TF]  WITH CHECK ADD  CONSTRAINT [FK_TF_Word] FOREIGN KEY([FK_Word])
REFERENCES [dbo].[Word] ([ID])
GO
ALTER TABLE [dbo].[TF] CHECK CONSTRAINT [FK_TF_Word]
GO
USE [master]
GO
ALTER DATABASE [Mini Google] SET  READ_WRITE 
GO
