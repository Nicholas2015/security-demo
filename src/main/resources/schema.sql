CREATE TABLE `users` (
                         `id` int NOT NULL,
                         `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `enabled` tinyint(1) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `authorities` (
                               `id` int NOT NULL,
                               `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                               `authority` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
