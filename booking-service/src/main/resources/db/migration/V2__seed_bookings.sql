INSERT INTO bookings (court_id, customer_name, customer_email, customer_phone, booking_date, start_time, end_time, total_price, status, notes) VALUES
(1, 'João Silva',   'joao@email.com',   '71999990001', CURRENT_DATE + 1, '09:00', '11:00', 240.00, 'CONFIRMED', 'Jogo de futebol society'),
(2, 'Maria Souza',  'maria@email.com',  '71999990002', CURRENT_DATE + 2, '14:00', '16:00', 180.00, 'CONFIRMED', 'Treino de vôlei de praia'),
(3, 'Carlos Lima',  'carlos@email.com', '71999990003', CURRENT_DATE + 3, '08:00', '09:00', 150.00, 'PENDING',   'Aula de tênis'),
(4, 'Ana Paula',    'ana@email.com',    '71999990004', CURRENT_DATE + 4, '10:00', '12:00', 160.00, 'CONFIRMED', 'Treino de basquete'),
(5, 'Pedro Costa',  'pedro@email.com',  '71999990005', CURRENT_DATE + 5, '19:00', '21:00', 200.00, 'PENDING',   'Pelada de futsal');