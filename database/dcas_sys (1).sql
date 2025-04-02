-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 02, 2025 at 09:03 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dcas_sys`
--

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `log_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `log_event` varchar(500) NOT NULL,
  `log_description` varchar(200) NOT NULL,
  `log_timestamp` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`log_id`, `user_id`, `log_event`, `log_description`, `log_timestamp`) VALUES
(6, 7, 'LOGIN ', 'User Logged in', '2025-03-30 16:08:46'),
(7, 7, 'LOGIN ', 'User Logged in', '2025-03-30 16:15:02'),
(8, 0, 'LOGOUT', 'User logged out', '2025-03-30 16:20:36'),
(9, 7, 'LOGIN ', 'User Logged in', '2025-03-30 16:21:10'),
(10, 7, 'LOGIN ', 'User Logged in', '2025-03-30 16:26:22'),
(11, 0, 'LOGOUT', 'User logged out', '2025-03-30 16:26:54'),
(12, 0, 'LOGOUT', 'User logged out', '2025-03-30 16:27:00'),
(13, 7, 'LOGIN ', 'User Logged in', '2025-03-30 16:33:34'),
(14, 0, 'LOGOUT', 'User logged out', '2025-03-30 16:40:30'),
(15, 7, 'LOGIN ', 'User Logged in', '2025-03-30 22:21:39'),
(16, 7, 'LOGIN ', 'User Logged in', '2025-03-30 22:25:15'),
(17, 0, 'LOGOUT', 'User logged out', '2025-03-30 22:25:27'),
(18, 7, 'LOGIN ', 'User Logged in', '2025-03-30 22:26:58'),
(19, 7, 'LOGIN ', 'User Logged in', '2025-03-30 22:28:33'),
(20, 7, 'LOGIN ', 'User Logged in', '2025-03-30 22:30:59'),
(21, 7, 'LOGIN ', 'User Logged in', '2025-03-31 00:16:02'),
(22, 7, 'LOGIN ', 'User Logged in', '2025-03-31 06:30:23'),
(23, 7, 'LOGIN ', 'User Logged in', '2025-03-31 06:31:16'),
(24, 7, 'LOGIN ', 'User Logged in', '2025-03-31 07:06:28'),
(25, 7, 'LOGIN ', 'User Logged in', '2025-03-31 13:00:41'),
(26, 7, 'LOGIN ', 'User Logged in', '2025-03-31 13:01:44'),
(27, 7, 'LOGIN ', 'User Logged in', '2025-03-31 13:03:13'),
(28, 7, 'LOGIN ', 'User Logged in', '2025-03-31 13:03:44'),
(29, 7, 'LOGIN ', 'User Logged in', '2025-03-31 13:05:12'),
(30, 7, 'LOGIN ', 'User Logged in', '2025-03-31 14:13:50'),
(31, 7, 'LOGIN ', 'User Logged in', '2025-03-31 17:28:10'),
(32, 7, 'LOGIN ', 'User Logged in', '2025-04-01 03:08:50'),
(33, 7, 'LOGIN ', 'User Logged in', '2025-04-01 03:18:13'),
(34, 7, 'LOGIN ', 'User Logged in', '2025-04-01 04:18:24'),
(35, 7, 'LOGIN ', 'User Logged in', '2025-04-01 05:18:08'),
(36, 7, 'LOGIN ', 'User Logged in', '2025-04-01 05:19:06'),
(37, 7, 'LOGIN ', 'User Logged in', '2025-04-01 05:21:02'),
(38, 7, 'LOGIN ', 'User Logged in', '2025-04-01 05:25:34'),
(39, 7, 'LOGIN ', 'User Logged in', '2025-04-01 22:20:10'),
(40, 7, 'LOGIN ', 'User Logged in', '2025-04-02 04:35:57'),
(41, 7, 'LOGIN ', 'User Logged in', '2025-04-02 04:37:13'),
(42, 0, 'LOGOUT', 'User logged out', '2025-04-02 04:40:32'),
(43, 17, 'LOGIN ', 'User Logged in', '2025-04-02 04:44:08'),
(44, 7, 'LOGIN ', 'User Logged in', '2025-04-02 04:58:41'),
(45, 7, 'LOGIN ', 'User Logged in', '2025-04-02 05:04:20'),
(46, 7, 'LOGIN ', 'User Logged in', '2025-04-02 05:10:59'),
(47, 7, 'LOGIN ', 'User Logged in', '2025-04-02 05:11:38'),
(48, 7, 'LOGOUT', 'User logged out', '2025-04-02 05:16:51'),
(49, 7, 'LOGIN ', 'User Logged in', '2025-04-02 05:17:15'),
(50, 7, 'LOGOUT', 'User logged out', '2025-04-02 05:17:22'),
(51, 7, 'LOGIN ', 'User Logged in', '2025-04-02 05:18:37'),
(52, 7, 'LOGIN ', 'User Logged in', '2025-04-02 05:26:48'),
(53, 7, 'LOGIN ', 'User Logged in', '2025-04-02 06:08:55'),
(54, 7, 'LOGIN ', 'User Logged in', '2025-04-02 14:51:54'),
(55, 7, 'LOGIN ', 'User Logged in', '2025-04-02 14:57:53'),
(56, 7, 'LOGIN ', 'User Logged in', '2025-04-02 15:02:16'),
(57, 7, 'LOGOUT', 'User logged out', '2025-04-02 15:02:49');

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `patient_id` int(20) NOT NULL,
  `user_id` int(20) DEFAULT NULL,
  `p_fname` varchar(255) NOT NULL,
  `p_mname` varchar(255) DEFAULT NULL,
  `p_lname` varchar(255) NOT NULL,
  `p_gender` enum('Male','Female') NOT NULL,
  `p_dob` date NOT NULL,
  `p_age` varchar(20) NOT NULL,
  `p_contactNumber` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`patient_id`, `user_id`, `p_fname`, `p_mname`, `p_lname`, `p_gender`, `p_dob`, `p_age`, `p_contactNumber`) VALUES
(1, NULL, 'First Name', 'Middle Name', 'Last Name', 'Male', '2004-06-11', '20', '048392819231'),
(8, 11, 'Rasheed', '', 'Tapales', 'Male', '2004-06-11', '23', '09876352741');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staff_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `s_fname` varchar(255) NOT NULL,
  `s_mname` varchar(255) NOT NULL,
  `s_lname` varchar(255) NOT NULL,
  `s_gender` enum('Male','Female') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(20) NOT NULL,
  `u_username` varchar(50) NOT NULL,
  `u_email` varchar(50) NOT NULL,
  `u_role` enum('Admin','Patient','Dentist') NOT NULL,
  `u_status` enum('Pending','Active','Deactivated') NOT NULL,
  `u_password` varchar(150) NOT NULL,
  `u_image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `u_username`, `u_email`, `u_role`, `u_status`, `u_password`, `u_image`) VALUES
(1, 'rode', 'rode@gmail.com', 'Admin', 'Active', '3B6X/OvDQV8pvejFq3EPXRO83edXQR5a72N/dI8VXWc=', ''),
(4, 'rodeliza', 'rodeliza@gmail.com', 'Admin', 'Active', 'Q61LLm9J1N3LWJnYComviibvp8sMQ5HRg5Vta2RUX90=', ''),
(5, 'admin', 'admin@gmail.com', 'Admin', 'Active', 'A2PP6ZkUYIA6O0LVVH7urjddwqxsAajDB7miYRb4u5o=', ''),
(6, 'Adminn', 'Aadmin@gmail.com', 'Admin', 'Active', 'tXMdgavKY0WxJJlIZNr3zG53DtqsKYvMpEC4KPXMxXg=', ''),
(7, 'Rodelizaa', '2002@gmail.com', 'Admin', 'Active', 'b5731d81abca6345b124994864daf7cc6e770edaac298bcca440b828f5ccc578', ''),
(8, 'Lovee', 'love.rasheed@gmail.com', 'Dentist', 'Active', '480ca255bf5d78c82913efff0433695b418c85ebf05f4dc5e92e59e951aded36', ''),
(9, 'ahldeng', 'ahlde@gmail.com', 'Admin', 'Pending', '428ad9b31b88aebb5b1c9c7c56fe540b9c8133fce671ca9d17b5deab496027dd', ''),
(11, 'Rasheed', 'shed@gmail.com', 'Patient', 'Active', '233d654151ed3229ef9dc7ba1f24243c26ae4f068e1ec06f458e8fb3f70d8acf', ''),
(12, 'Ahlde', 'deng@gmail.com', 'Patient', 'Pending', '233d654151ed3229ef9dc7ba1f24243c26ae4f068e1ec06f458e8fb3f70d8acf', ''),
(13, 'ediwow', 'wow@gmail.com', 'Patient', 'Pending', 'a52f9f8699beb26fb6dee5ab83828e5609c28dfa18d5a54858534545990b82e2', ''),
(17, 'users', 'user@gmail.com', 'Patient', 'Active', '3d69b9ae0a9559efcbf39b243ba51017b62266c96e0f74faa05ebad087f6f270', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`patient_id`),
  ADD UNIQUE KEY `INDEX` (`user_id`) USING BTREE;

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staff_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `patients`
--
ALTER TABLE `patients`
  MODIFY `patient_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `patients`
--
ALTER TABLE `patients`
  ADD CONSTRAINT `fk_patient_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `patients_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
