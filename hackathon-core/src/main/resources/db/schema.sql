CREATE TABLE devices
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    deviceName VARCHAR(100),
    version    VARCHAR(10),
    uid        VARCHAR(50),
    appium_url VARCHAR(50),
    port       INT,
    status     VARCHAR(20)
);
