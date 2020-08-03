INSERT INTO server_details(server_id,region) VALUES
(1, 'us-east'),
(2, 'us-west'),
(3, 'asia');
 
INSERT INTO cpu_details(cpu_id,server_id,server_type, server_capacity, cost_per_hour) VALUES
(101, 1, 'large',   1, 0.12),
(102, 1, 'xlarge',  2, 0.23),
(103, 1, '2xlarge', 4, 0.45),
(104, 1, '4xlarge', 8, 0.774),
(105, 1, '8xlarge', 16, 1.4),
(106, 1, '10xlarge', 32, 2.82),
(201, 2, 'large',   1, 0.14),
(203, 2, '2xlarge',  4, 0.413),
(204, 2, '4xlarge', 8, 0.89),
(205, 2, '8xlarge', 16, 1.3),
(206, 2, '10xlarge', 32, 2.97),
(301, 3, 'large', 1, 0.11),
(303, 3, 'xlarge', 2, 0.20),
(304, 3, '4xlarge', 8, 0.67),
(305, 3, '8xlarge', 16, 1.18);