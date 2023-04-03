create database `commercedb`;
use `commercedb`;

CREATE TABLE IF NOT EXISTS `brands` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`description` VARCHAR(255) NULL DEFAULT NULL,
	`name` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `categories` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `customer_product` (
	`customer_id` BINARY(50) NOT NULL,
	`product_id` INT NOT NULL,
	PRIMARY KEY (`customer_id`, `product_id`)
);

CREATE TABLE IF NOT EXISTS `customers` (
	`id` BINARY(50) NOT NULL,
	`dob` DATE NULL DEFAULT NULL,
	`email` VARCHAR(255) NULL DEFAULT NULL,
	`gender` VARCHAR(255) NULL DEFAULT NULL,
	`name` VARCHAR(255) NULL DEFAULT NULL,
	`password` VARCHAR(255) NULL DEFAULT NULL,
	`username` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `order_product` (
	`order_id` INT NOT NULL,
	`product_id` INT NOT NULL,
	PRIMARY KEY (`order_id`, `product_id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`address` VARCHAR(255) NULL DEFAULT NULL,
	`cost` DOUBLE NOT NULL,
	`create_at` DATE NULL DEFAULT NULL,
	`phone` VARCHAR(255) NULL DEFAULT NULL,
	`customer_id` BINARY(50) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `product_category` (
	`product_id` INT NOT NULL,
	`category_id` INT NOT NULL,
	PRIMARY KEY (`product_id`, `category_id`)
);

CREATE TABLE IF NOT EXISTS `products` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`color` VARCHAR(255) NULL DEFAULT NULL,
	`description` VARCHAR(255) NULL DEFAULT NULL,
	`image` VARCHAR(255) NULL DEFAULT NULL,
	`name` VARCHAR(255) NULL DEFAULT NULL,
	`price` DOUBLE NOT NULL,
	`quantity` INT NOT NULL,
	`brand_id` INT NULL DEFAULT NULL,
	`category_id` INT NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

-- Khóa ngoại 
ALTER TABLE products ADD CONSTRAINT FK_Products_Brands FOREIGN KEY(`brand_id`) REFERENCES brands(`id`);
ALTER TABLE products ADD CONSTRAINT FK_Products_Categories FOREIGN KEY(`category_id`) REFERENCES categories(`id`); 

ALTER TABLE order_product ADD CONSTRAINT FK_orders_product FOREIGN KEY(`order_id`) REFERENCES orders(`id`);
ALTER TABLE order_product ADD CONSTRAINT FK_order_products FOREIGN KEY(`product_id`) REFERENCES products(`id`);

ALTER TABLE customer_product ADD CONSTRAINT FK_customers_product FOREIGN KEY(`customer_id`) REFERENCES customers(`id`);
ALTER TABLE customer_product ADD CONSTRAINT FK_customer_products FOREIGN KEY(`product_id`) REFERENCES products(`id`);

-- ALTER TABLE product_category ADD CONSTRAINT FK_products_category FOREIGN KEY(`product_id`) REFERENCES products(`id`);
-- ALTER TABLE product_category ADD CONSTRAINT FK_product_categories FOREIGN KEY(`product_id`) REFERENCES categories(`id`);

ALTER TABLE orders ADD CONSTRAINT FK_customers_orders FOREIGN KEY(`customer_id`) REFERENCES customers(`id`);

-- thêm dữ liệu 
INSERT INTO `brands` VALUES 
	(1,'Từ \"Máy tính xách tay bắt mắt nhất\" đến \"Sản phẩm và công nghệ sáng tạo nhất\", Lenovo đã vinh dự giành được hơn 80 giải thưởng từ CES.','Lenovo'),
	(2,'AsusTek Computer Inc. là một tập đoàn đa quốc gia có trụ sở chính tại Đài Loan, chuyên sản xuất các mặt hàng điện tử và phần cứng máy tính như máy tính để bàn, máy tính xách tay, netbook, điện thoại di động','Asus'),
	(3,'Acer Inc. là tập đoàn đa quốc gia về thiết bị điện tử và phần cứng máy tính của Đài Loan có trụ sở tại Tịch Chỉ, Tân Bắc, Đài Loan.','Acer'),
	(4,'Dell Inc. là một công ty đa quốc gia của Hoa Kỳ về phát triển và thương mại hóa công nghệ máy tính có trụ sở tại Round Rock, Texas, Hoa Kỳ. Dell được thành lập năm 1984 do chủ quản gia Michael Dell đồng sáng lập. Đây là công ty có thu nhập lớn thứ 28 tại ','Dell'),
	(5,'Apple Inc. là một Tập đoàn công nghệ đa quốc gia của Mỹ có trụ sở chính tại Cupertino, California, chuyên Thiết kế, phát triển và bán thiết bị điện tử tiêu dùng, phần mềm máy tính và các Dịch vụ trực tuyến.','Apple'),
	(6,'Tập đoàn Intel thành lập năm 1968 tại Santa Clara, California, Hoa Kỳ, là nhà sản xuất các sản phẩm như chip vi xử lý cho máy tính, bo mạch chủ, ổ nhớ flash, card mạng và các thiết bị máy tính khác.','Intel'),
	(7,'Gigabyte Technology Co., Ltd., là một nhà sản xuất và phân phối các thiết bị phần cứng máy tính của Đài Loan. Khẩu hiệu của hãng là \"Upgrage Your Life\" - \"nâng cấp cuộc sống của bạn\". Sản phẩm của GIGABYTE bao gồm bo mạch chủ, card đồ họa, tản nhiệt, vỏ m','Gigabyte'),
	(8,'MSI là một tập đoàn công nghệ thông tin đa quốc gia có trụ sở chính ở Tân Bắc, Đài Loan, với logo là một con rồng màu đỏ.','MSI');

INSERT INTO `categories` VALUES 
	(1,'Vi xử lý'),
	(2,'Bo mạch chủ'),
	(3,'Ram'),
	(4,'HDD'),
	(5,'SSD'),
	(6,'Card màn hình'),
	(7,'Nguồn'),
	(8,'Tản nhiệt'),
	(9,'Màn hình'),
	(10,'Bàn Phím'),
	(11,'Chuột');

INSERT INTO `customers` VALUES 
	(_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1990-01-01','customer1@example.com','male','Customer 1','123456','customer1'),
	(_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1995-02-02','customer2@example.com','female','Customer 2','123456','customer2'),
	(_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1985-03-03','customer3@example.com','male','Customer 3','123456','customer3'),
	(_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1993-04-04','customer4@example.com','female','Customer 4','123456','customer4'),
	(_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','1998-05-05','customer5@example.com','male','Customer 5','123456','customer5');
	-- (_binary 'xv\ÍÊ5Ib¦Fü²TÛ¤','2023-04-03','nghia@gmail.com','female','Lê Công Nghĩa','123456','conghiale');
    
INSERT INTO `orders` VALUES 
	(1,'123 Main St',50,'2023-01-01','0826611778',_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),
	(2,'456 Oak Ave',75,'2023-02-01','0826611779',_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),
	(3,'789 Elm St',100,'2023-03-01','0826611771',_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),
	(4,'1012 Pine St',125,'2023-04-01','0826611772',_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),
	(5,'1315 Maple Ave',150,'2022-05-01','0826611773',_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),
	(6,'1618 Oak St',175,'2022-06-01','0826611774',_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),
	(7,'1921 Elm Ave',200,'2022-07-01','0826611775',_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),
	(8,'2224 Pine St',225,'2022-08-01','0826611776',_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),
	(9,'2527 Maple Ave',250,'2022-09-01','0826611777',_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),
	(10,'2830 Oak St',275,'2022-10-01','0826611788',_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0');

INSERT INTO `products` VALUES 
	(1,'Red','Product description 1','image1.jpg','Product name 1',10,100,1,1),
	(2,'Blue','Product description 2','image1.jpg','Product name 2',20,200,2,2),
	(3,'Green','Product description 3','image1.jpg','Product name 3',30,300,3,3),
	(4,'Yellow','Product description 4','image1.jpg','Product name 4',40,400,4,4),
	(5,'Black','Product description 5','image1.jpg','Product name 5',50,500,5,5),
	(6,'White','Product description 6','image1.jpg','Product name 6',60,600,1,6),
	(7,'Orange','Product description 7','image1.jpg','Product name 7',70,700,2,7),
	(8,'Purple','Product description 8','image1.jpg','Product name 8',80,800,3,8),
	(9,'Gray','Product description 9','image1.jpg','Product name 9',90,900,4,9),
	(10,'Brown','Product description 10','image1.jpg','Product name 10',100,1000,5,10),
	(11,'Red','Product description 11','image1.jpg','Product name 11',110,1100,1,11),
	(12,'Blue','Product description 12','image1.jpg','Product name 12',120,1200,2,1),
	(13,'Green','Product description 13','image1.jpg','Product name 13',130,1300,3,2),
	(14,'Yellow','Product description 14','image1.jpg','Product name 14',140,1400,4,3),
	(15,'Black','Product description 15','image1.jpg','Product name 15',150,1500,5,4);


