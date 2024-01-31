-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 31, 2024 at 04:41 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `workingman`
--

-- --------------------------------------------------------

--
-- Table structure for table `assign`
--

CREATE TABLE `assign` (
  `assignid` int(11) NOT NULL,
  `task_giver` int(11) DEFAULT NULL,
  `taskid` int(11) DEFAULT NULL,
  `task_recipient` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `assign`
--

INSERT INTO `assign` (`assignid`, `task_giver`, `taskid`, `task_recipient`) VALUES
(1, 2, 16, 1),
(2, 1, 17, 3),
(6, 1, 16, 3),
(7, 1, 18, 2),
(8, 2, 19, 1);

-- --------------------------------------------------------

--
-- Table structure for table `issues`
--

CREATE TABLE `issues` (
  `issuesid` int(11) NOT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `issues_name` varchar(255) DEFAULT NULL,
  `taskid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `issues`
--

INSERT INTO `issues` (`issuesid`, `description`, `issues_name`, `taskid`) VALUES
(15, 'ghi chú mới', 'Ghi chú', 16),
(16, '', 'Ghi chú', 17),
(17, 'This is a new issue ', 'Ghi chú', 18),
(18, 'davcsava', 'Ghi chú', 19);

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `taskid` int(11) NOT NULL,
  `deadline` varchar(255) DEFAULT NULL,
  `divinefrom` int(11) DEFAULT NULL,
  `status` varchar(300) DEFAULT NULL,
  `task_description` varchar(5000) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`taskid`, `deadline`, `divinefrom`, `status`, `task_description`, `start_date`, `result`) VALUES
(16, '2025-01-01', NULL, 'Hoàn thành', 'công việc mới', '2024-01-25', 'KHÔNG ĐẠT'),
(17, '2025-01-01', NULL, 'Hoàn thành', 'công việc thứ 2', '2024-01-25', 'ĐẠT'),
(18, '2025-02-05', NULL, 'Đã tiếp nhận', 'Testing Progress: Công việc mới', '2024-01-26', NULL),
(19, '2025-11-25', NULL, 'Đang xử lý', 'new tassk', '2024-01-29', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userid` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `permission` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `password`, `permission`, `username`, `image`, `email`) VALUES
(1, '1', 1, 'phuquy', 'Truong Nguyen Phu Quy Photo.jpg', NULL),
(2, '1', 2, 'user1', 'Truong Nguyen Phu Quy Photo.jpg', NULL),
(3, '1', 2, 'user2', 'Truong Nguyen Phu Quy Photo.jpg', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assign`
--
ALTER TABLE `assign`
  ADD PRIMARY KEY (`assignid`),
  ADD KEY `FKpo71hgw3hsmoghr56no88olk2` (`task_giver`),
  ADD KEY `FK2l8egclu72vmrtib8ep0gue9s` (`taskid`),
  ADD KEY `FKi4vlwxsp33f5f5ads6ca1rrye` (`task_recipient`);

--
-- Indexes for table `issues`
--
ALTER TABLE `issues`
  ADD PRIMARY KEY (`issuesid`),
  ADD KEY `FKao7lbfblclce2a5hm79vanycf` (`taskid`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`taskid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assign`
--
ALTER TABLE `assign`
  MODIFY `assignid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `issues`
--
ALTER TABLE `issues`
  MODIFY `issuesid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `taskid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assign`
--
ALTER TABLE `assign`
  ADD CONSTRAINT `FK2l8egclu72vmrtib8ep0gue9s` FOREIGN KEY (`taskid`) REFERENCES `task` (`taskid`),
  ADD CONSTRAINT `FKi4vlwxsp33f5f5ads6ca1rrye` FOREIGN KEY (`task_recipient`) REFERENCES `user` (`userid`),
  ADD CONSTRAINT `FKpo71hgw3hsmoghr56no88olk2` FOREIGN KEY (`task_giver`) REFERENCES `user` (`userid`);

--
-- Constraints for table `issues`
--
ALTER TABLE `issues`
  ADD CONSTRAINT `FKao7lbfblclce2a5hm79vanycf` FOREIGN KEY (`taskid`) REFERENCES `task` (`taskid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
