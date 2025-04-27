-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2025 at 11:22 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

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
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appointment_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `dentist_id` int(11) NOT NULL,
  `pref_date` datetime(6) NOT NULL,
  `pref_time` datetime(6) NOT NULL,
  `notes` text NOT NULL,
  `a_status` enum('Pending','Confirmed','Completed','Cancelled') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `dentist`
--

CREATE TABLE `dentist` (
  `dentist_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `d_fname` varchar(255) NOT NULL,
  `d_mname` varchar(255) NOT NULL,
  `d_lname` varchar(255) NOT NULL,
  `specialization` enum('General','Orthodontics') NOT NULL,
  `d_contact` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dentist`
--

INSERT INTO `dentist` (`dentist_id`, `user_id`, `d_fname`, `d_mname`, `d_lname`, `specialization`, `d_contact`) VALUES
(1, 5, 'Rasheed', 'P.', 'Tapales', 'General', '09171234567');

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
(57, 7, 'LOGOUT', 'User logged out', '2025-04-02 15:02:49'),
(58, 7, 'LOGIN ', 'User Logged in', '2025-04-20 22:38:56'),
(59, 7, 'LOGOUT', 'User logged out', '2025-04-20 22:40:56'),
(60, 7, 'LOGIN ', 'User Logged in', '2025-04-20 22:56:59'),
(61, 7, 'LOGIN ', 'User Logged in', '2025-04-20 23:03:38'),
(62, 7, 'LOGIN ', 'User Logged in', '2025-04-20 23:13:20'),
(63, 7, 'LOGOUT', 'User logged out', '2025-04-20 23:47:43'),
(64, 7, 'LOGIN ', 'User Logged in', '2025-04-20 23:48:03'),
(65, 7, 'LOGOUT', 'User logged out', '2025-04-20 23:48:38'),
(66, 7, 'LOGIN ', 'User Logged in', '2025-04-20 23:48:55'),
(67, 7, 'LOGOUT', 'User logged out', '2025-04-21 00:17:27'),
(68, 7, 'LOGIN ', 'User Logged in', '2025-04-21 00:17:42'),
(69, 7, 'LOGIN ', 'User Logged in', '2025-04-21 00:28:55'),
(70, 7, 'LOGIN ', 'User Logged in', '2025-04-21 00:31:27'),
(71, 7, 'LOGOUT', 'User logged out', '2025-04-21 00:38:23'),
(72, 7, 'LOGIN ', 'User Logged in', '2025-04-21 05:09:29'),
(73, 7, 'LOGIN ', 'User Logged in', '2025-04-21 05:35:40'),
(74, 7, 'LOGOUT', 'User logged out', '2025-04-21 05:40:39'),
(75, 7, 'LOGIN ', 'User Logged in', '2025-04-21 05:41:13'),
(76, 7, 'LOGIN ', 'User Logged in', '2025-04-21 05:53:10'),
(77, 7, 'LOGOUT', 'User logged out', '2025-04-21 05:57:32'),
(78, 7, 'LOGIN ', 'User Logged in', '2025-04-21 05:57:49'),
(79, 7, 'LOGIN ', 'User Logged in', '2025-04-21 07:06:00'),
(80, 7, 'LOGIN ', 'User Logged in', '2025-04-21 07:07:42'),
(81, 7, 'LOGIN ', 'User Logged in', '2025-04-21 07:16:34'),
(82, 7, 'EDITED ADMIN PROFILE', 'Admin updated their account information.', '2025-04-21 07:54:58'),
(83, 7, 'LOGIN ', 'User Logged in', '2025-04-21 08:05:34'),
(84, 7, 'LOGIN ', 'User Logged in', '2025-04-21 17:08:07'),
(85, 7, 'LOGIN ', 'User Logged in', '2025-04-21 18:00:32'),
(86, 7, 'LOGIN ', 'User Logged in', '2025-04-21 18:03:25'),
(87, 7, 'LOGIN ', 'User Logged in', '2025-04-21 18:08:08'),
(88, 7, 'LOGIN ', 'User Logged in', '2025-04-21 18:08:52'),
(89, 7, 'LOGIN ', 'User Logged in', '2025-04-21 18:09:54'),
(90, 7, 'EDITED ADMIN PROFILE', 'Admin updated their account information.', '2025-04-21 19:49:32'),
(91, 7, 'LOGIN ', 'User Logged in', '2025-04-21 19:58:39'),
(92, 7, 'EDITED ADMIN PROFILE', 'Admin updated their account information.', '2025-04-21 20:38:01'),
(93, 7, 'LOGIN ', 'User Logged in', '2025-04-21 21:04:20'),
(94, 7, 'LOGIN ', 'User Logged in', '2025-04-21 21:07:34'),
(95, 7, 'LOGIN ', 'User Logged in', '2025-04-21 21:11:13'),
(96, 7, 'LOGIN ', 'User Logged in', '2025-04-21 21:17:23'),
(97, 7, 'LOGOUT', 'User logged out', '2025-04-21 21:17:59'),
(98, 7, 'LOGIN ', 'User Logged in', '2025-04-21 21:18:12'),
(99, 7, 'LOGIN ', 'User Logged in', '2025-04-22 05:19:19'),
(100, 7, 'LOGIN ', 'User Logged in', '2025-04-22 05:22:00'),
(101, 7, 'LOGOUT', 'User logged out', '2025-04-22 05:26:06'),
(102, 7, 'LOGIN ', 'User Logged in', '2025-04-22 05:26:23'),
(103, 7, 'LOGIN ', 'User Logged in', '2025-04-22 05:34:18'),
(104, 7, 'LOGIN ', 'User Logged in', '2025-04-22 05:38:20'),
(105, 7, 'LOGIN ', 'User Logged in', '2025-04-22 05:39:16'),
(106, 7, 'EDITED ADMIN PROFILE', 'Admin updated their account information.', '2025-04-22 05:39:45'),
(107, 7, 'LOGIN ', 'User Logged in', '2025-04-22 05:42:57'),
(108, 7, 'LOGIN ', 'User Logged in', '2025-04-22 05:45:32'),
(109, 7, 'LOGIN ', 'User Logged in', '2025-04-22 05:54:48'),
(110, 7, 'LOGIN ', 'User Logged in', '2025-04-22 05:56:50'),
(111, 7, 'LOGIN ', 'User Logged in', '2025-04-22 20:05:35'),
(112, 7, 'LOGIN ', 'User Logged in', '2025-04-22 20:08:56'),
(113, 7, 'EDITED ADMIN PROFILE', 'Admin updated their account information.', '2025-04-22 20:09:17'),
(114, 7, 'EDITED ADMIN PROFILE', 'Admin updated their account information.', '2025-04-22 20:09:50'),
(115, 1, 'LOGIN ', 'User Logged in', '2025-04-22 20:20:08'),
(116, 1, 'EDITED ADMIN PROFILE', 'Admin updated their account information.', '2025-04-22 20:25:57'),
(117, 1, 'EDITED ADMIN PROFILE', 'Admin updated their account information.', '2025-04-22 20:28:31'),
(118, 2, 'LOGIN ', 'User Logged in', '2025-04-22 21:10:23'),
(119, 2, 'LOGOUT', 'User logged out', '2025-04-22 21:11:15'),
(120, 3, 'LOGIN ', 'User Logged in', '2025-04-23 04:15:33'),
(121, 3, 'LOGIN ', 'User Logged in', '2025-04-23 04:20:20'),
(122, 3, 'LOGIN ', 'User Logged in', '2025-04-23 04:28:09'),
(123, 4, 'LOGIN ', 'User Logged in', '2025-04-23 05:14:03'),
(124, 3, 'LOGIN ', 'User Logged in', '2025-04-23 05:30:28'),
(125, 3, 'EDITED ADMIN PROFILE', 'Admin updated their account information.', '2025-04-23 06:01:20'),
(126, 3, 'LOGIN ', 'User Logged in', '2025-04-23 06:08:38'),
(127, 3, 'EDITED ADMIN PROFILE', 'Admin updated their account information.', '2025-04-23 06:09:00'),
(128, 3, 'LOGIN ', 'User Logged in', '2025-04-23 07:09:40'),
(129, 3, 'LOGIN ', 'User Logged in', '2025-04-24 07:09:26'),
(130, 3, 'LOGIN ', 'User Logged in', '2025-04-24 07:57:21'),
(131, 3, 'LOGIN ', 'User Logged in', '2025-04-24 07:57:52'),
(132, 3, 'LOGIN ', 'User Logged in', '2025-04-24 08:44:53'),
(133, 3, 'EDITED ADMIN PROFILE', 'Admin updated their account information.', '2025-04-24 08:54:39'),
(134, 3, 'LOGIN ', 'User Logged in', '2025-04-24 16:32:51'),
(135, 3, 'LOGIN ', 'User Logged in', '2025-04-25 14:32:08'),
(136, 3, 'EDITED ADMIN PROFILE', 'Admin updated their account information.', '2025-04-25 14:32:48'),
(137, 3, 'LOGOUT', 'User logged out', '2025-04-25 14:34:26'),
(138, 3, 'LOGIN ', 'User Logged in', '2025-04-25 14:49:27'),
(139, 3, 'LOGIN ', 'User Logged in', '2025-04-25 20:04:06'),
(140, 3, 'LOGIN ', 'User Logged in', '2025-04-25 20:09:28'),
(141, 3, 'LOGIN ', 'User Logged in', '2025-04-25 20:10:31'),
(142, 3, 'ADDED USER ACCOUNT', 'Admin added user account.', '2025-04-25 20:11:09'),
(143, 3, 'LOGIN ', 'User Logged in', '2025-04-25 20:54:44'),
(144, 3, 'LOGOUT', 'User logged out', '2025-04-25 21:02:58'),
(145, 3, 'LOGIN ', 'User Logged in', '2025-04-25 21:07:44'),
(146, 3, 'LOGIN ', 'User Logged in', '2025-04-25 22:55:19'),
(147, 3, 'LOGIN ', 'User Logged in', '2025-04-25 23:14:16'),
(148, 3, 'LOGOUT', 'User logged out', '2025-04-25 23:16:02'),
(149, 3, 'LOGIN ', 'User Logged in', '2025-04-25 23:23:40'),
(150, 3, 'LOGIN ', 'User Logged in', '2025-04-25 23:26:47'),
(151, 3, 'LOGIN ', 'User Logged in', '2025-04-25 23:28:41'),
(152, 3, 'LOGIN ', 'User Logged in', '2025-04-26 05:13:24'),
(153, 3, 'ADDED USER ACCOUNT', 'Admin added user account.', '2025-04-26 05:40:36'),
(154, 3, 'LOGIN ', 'User Logged in', '2025-04-26 06:25:56'),
(155, 3, 'LOGIN ', 'User Logged in', '2025-04-26 11:45:27'),
(156, 3, 'LOGIN ', 'User Logged in', '2025-04-26 11:46:04'),
(157, 3, 'LOGIN ', 'User Logged in', '2025-04-26 13:54:41'),
(158, 3, 'LOGIN ', 'User Logged in', '2025-04-26 14:36:00'),
(159, 3, 'LOGIN ', 'User Logged in', '2025-04-26 16:30:51'),
(160, 3, 'LOGIN ', 'User Logged in', '2025-04-26 16:38:17'),
(161, 3, 'LOGIN ', 'User Logged in', '2025-04-26 16:44:02'),
(162, 3, 'LOGIN ', 'User Logged in', '2025-04-26 16:49:01'),
(163, 3, 'LOGIN ', 'User Logged in', '2025-04-27 14:05:39'),
(164, 3, 'LOGIN ', 'User Logged in', '2025-04-27 15:07:58'),
(165, 3, 'LOGIN ', 'User Logged in', '2025-04-27 15:46:15'),
(166, 3, 'LOGIN ', 'User Logged in', '2025-04-27 16:01:41'),
(167, 3, 'LOGIN ', 'User Logged in', '2025-04-27 16:44:47'),
(168, 3, 'LOGIN ', 'User Logged in', '2025-04-27 16:53:12'),
(169, 3, 'LOGIN ', 'User Logged in', '2025-04-27 16:55:59');

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
  `p_contactNumber` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`patient_id`, `user_id`, `p_fname`, `p_mname`, `p_lname`, `p_gender`, `p_dob`, `p_contactNumber`) VALUES
(1, NULL, 'Rodeliza', NULL, 'La Rosa', 'Female', '2002-10-20', '09871234567'),
(2, NULL, 'Rasheed', NULL, 'Tapales', 'Male', '2004-06-11', '09871245678');

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `service_id` int(11) NOT NULL,
  `category` enum('General','Orthodontics') NOT NULL,
  `service_name` varchar(255) NOT NULL,
  `service_cost` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`service_id`, `category`, `service_name`, `service_cost`) VALUES
(1, 'General', 'Consultations', 500),
(2, 'General', 'Cleanings', 1200),
(3, 'General', 'Tooth Extractions', 1500),
(4, 'General', 'Root Canals', 3500),
(5, 'General', 'Wisdom Tooth Removal', 5000),
(6, 'Orthodontics', 'Braces', 35000),
(7, 'Orthodontics', 'Retainers', 5000);

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

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `user_id`, `s_fname`, `s_mname`, `s_lname`, `s_gender`) VALUES
(2, 3, 'Rode', '', 'La Rosa', 'Female');

-- --------------------------------------------------------

--
-- Table structure for table `treatment_services`
--

CREATE TABLE `treatment_services` (
  `treatment_id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1,
  `total_cost` decimal(10,0) NOT NULL
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
  `u_status` enum('Pending','Active','Archived') NOT NULL,
  `u_password` varchar(150) NOT NULL,
  `u_image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `u_username`, `u_email`, `u_role`, `u_status`, `u_password`, `u_image`) VALUES
(3, 'rode', 'rode.2002@gmail.com', 'Admin', 'Active', 'b5731d81abca6345b124994864daf7cc6e770edaac298bcca440b828f5ccc578', NULL),
(4, 'love', 'love@gmail.com', 'Patient', 'Active', '0342a32dd9469b371fd499c3ba33be823896040ed898de28b0141d0399d7a164', 'default/u_blank.jpg'),
(5, 'Rasheed', 'shed@gmail.com', 'Dentist', 'Active', '0342a32dd9469b371fd499c3ba33be823896040ed898de28b0141d0399d7a164', 'default/u_blank.jpg'),
(6, 'deng', 'deng@gmail.com', 'Patient', 'Pending', '0342a32dd9469b371fd499c3ba33be823896040ed898de28b0141d0399d7a164', 'src/u_images/11.jpg'),
(7, 'test', 'test@gmail.com', 'Admin', 'Active', '0342a32dd9469b371fd499c3ba33be823896040ed898de28b0141d0399d7a164', 'src/u_images/_444.JPG');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appointment_id`),
  ADD UNIQUE KEY `patient_id` (`patient_id`,`dentist_id`),
  ADD KEY `dentist_id` (`dentist_id`);

--
-- Indexes for table `dentist`
--
ALTER TABLE `dentist`
  ADD PRIMARY KEY (`dentist_id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`log_id`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`patient_id`),
  ADD UNIQUE KEY `INDEX` (`user_id`) USING BTREE;

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`service_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staff_id`);

--
-- Indexes for table `treatment_services`
--
ALTER TABLE `treatment_services`
  ADD PRIMARY KEY (`treatment_id`),
  ADD UNIQUE KEY `appointment_id` (`appointment_id`,`service_id`),
  ADD KEY `service_id` (`service_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `dentist`
--
ALTER TABLE `dentist`
  MODIFY `dentist_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=170;

--
-- AUTO_INCREMENT for table `patients`
--
ALTER TABLE `patients`
  MODIFY `patient_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `treatment_services`
--
ALTER TABLE `treatment_services`
  MODIFY `treatment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`),
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`dentist_id`) REFERENCES `dentist` (`dentist_id`);

--
-- Constraints for table `dentist`
--
ALTER TABLE `dentist`
  ADD CONSTRAINT `dentist_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `patients`
--
ALTER TABLE `patients`
  ADD CONSTRAINT `patients_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `treatment_services`
--
ALTER TABLE `treatment_services`
  ADD CONSTRAINT `treatment_services_ibfk_1` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`appointment_id`),
  ADD CONSTRAINT `treatment_services_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `services` (`service_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
