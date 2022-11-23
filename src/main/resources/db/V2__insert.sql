INSERT INTO flight (count, date_start, time_start, time_flying, cost, start_city, end_city, status)
SELECT 100, CURRENT_DATE, '21:00', '8:30', 8000, 'Moscow', 'Paris', 'Departure on time'
UNION
SELECT 100, CURRENT_DATE, '21:00', '10:00', 16000, 'Moscow', 'Madrid', 'Departure on time';
