create database RentingDisk

USE [RentingDisk]
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [lateFee], [name], [rentalCharge], [rentalPeriod]) VALUES (1, 20000, N'Game', 30000, 7)
INSERT [dbo].[Category] ([id], [lateFee], [name], [rentalCharge], [rentalPeriod]) VALUES (2, 15000, N'Phim', 25000, 5)
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Title] ON 

INSERT [dbo].[Title] ([id], [enabled], [name], [numberOfCopies], [categoryID]) VALUES (1, 1, N'Call of Duty: BlackOps', 3, 1)
INSERT [dbo].[Title] ([id], [enabled], [name], [numberOfCopies], [categoryID]) VALUES (2, 1, N'Genshin Impact', 3, 1)
INSERT [dbo].[Title] ([id], [enabled], [name], [numberOfCopies], [categoryID]) VALUES (3, 1, N'CrossCode', 3, 1)
INSERT [dbo].[Title] ([id], [enabled], [name], [numberOfCopies], [categoryID]) VALUES (4, 1, N'Fate/Grand Order: Camelot', 3, 2)
INSERT [dbo].[Title] ([id], [enabled], [name], [numberOfCopies], [categoryID]) VALUES (5, 1, N'Assassination Classroom', 3, 2)
SET IDENTITY_INSERT [dbo].[Title] OFF
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([id], [address], [enabled], [name], [phoneNumber]) VALUES (1, N'TP HCM', 1, N'Gia Anh', N'0111444777')
INSERT [dbo].[Customer] ([id], [address], [enabled], [name], [phoneNumber]) VALUES (2, N'TP HCM', 1, N'Thành Khoa', N'0222555888')
INSERT [dbo].[Customer] ([id], [address], [enabled], [name], [phoneNumber]) VALUES (3, N'TP HCM', 1, N'Minh Hiếu', N'0333666999')
INSERT [dbo].[Customer] ([id], [address], [enabled], [name], [phoneNumber]) VALUES (4, N'TP HCM', 1, N'Nguyên Ân', N'0123456789')
SET IDENTITY_INSERT [dbo].[Customer] OFF
SET IDENTITY_INSERT [dbo].[Disk] ON 

INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (1, 1, N'rented', 1)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (2, 1, N'rented', 1)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (3, 1, N'rented', 1)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (4, 1, N'onShelf', 2)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (5, 1, N'onShelf', 2)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (6, 1, N'onShelf', 2)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (7, 1, N'onShelf', 3)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (8, 1, N'onShelf', 3)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (9, 1, N'onShelf', 3)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (10, 1, N'onShelf', 4)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (11, 1, N'onShelf', 4)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (12, 1, N'onShelf', 4)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (13, 1, N'onShelf', 5)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (14, 1, N'onShelf', 5)
INSERT [dbo].[Disk] ([id], [enabled], [status], [titleID]) VALUES (15, 1, N'onShelf', 5)
SET IDENTITY_INSERT [dbo].[Disk] OFF
SET IDENTITY_INSERT [dbo].[RentalRecord] ON 

INSERT [dbo].[RentalRecord] ([id], [dueDate], [isPaid], [lateFee], [rentDate], [rentalCharge], [returnDate], [customerID], [diskID]) VALUES (1, CAST(N'2021-08-08' AS Date), 0, 0, CAST(N'2021-08-01' AS Date), 30000, NULL, 1, 1)
INSERT [dbo].[RentalRecord] ([id], [dueDate], [isPaid], [lateFee], [rentDate], [rentalCharge], [returnDate], [customerID], [diskID]) VALUES (2, CAST(N'2021-08-06' AS Date), 0, 15000, CAST(N'2021-08-01' AS Date), 25000, CAST(N'2021-08-12' AS Date), 1, 10)
INSERT [dbo].[RentalRecord] ([id], [dueDate], [isPaid], [lateFee], [rentDate], [rentalCharge], [returnDate], [customerID], [diskID]) VALUES (3, CAST(N'2021-08-08' AS Date), 0, 0, CAST(N'2021-08-01' AS Date), 30000, NULL, 1, 2)
INSERT [dbo].[RentalRecord] ([id], [dueDate], [isPaid], [lateFee], [rentDate], [rentalCharge], [returnDate], [customerID], [diskID]) VALUES (4, CAST(N'2021-08-08' AS Date), 0, 0, CAST(N'2021-08-01' AS Date), 30000, NULL, 1, 3)
INSERT [dbo].[RentalRecord] ([id], [dueDate], [isPaid], [lateFee], [rentDate], [rentalCharge], [returnDate], [customerID], [diskID]) VALUES (5, CAST(N'2021-08-08' AS Date), 0, 20000, CAST(N'2021-08-01' AS Date), 30000, CAST(N'2021-08-12' AS Date), 1, 6)
SET IDENTITY_INSERT [dbo].[RentalRecord] OFF
SET IDENTITY_INSERT [dbo].[ReservationRecord] ON 

INSERT [dbo].[ReservationRecord] ([id], [orderDate], [customerID], [diskID], [titleID]) VALUES (1, CAST(N'2021-08-01' AS Date), 2, NULL, 1)
INSERT [dbo].[ReservationRecord] ([id], [orderDate], [customerID], [diskID], [titleID]) VALUES (2, CAST(N'2021-08-01' AS Date), 3, NULL, 1)
SET IDENTITY_INSERT [dbo].[ReservationRecord] OFF