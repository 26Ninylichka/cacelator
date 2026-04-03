-- вставка тестового пользователя для login

INSERT INTO app_user (
    user_id,
    name,
    email,
    password,
    phone_number,
    status,
    created_at,
    updated_at
) VALUES (
             '11111111-1111-1111-1111-111111111111',
             'Test User',
             'test@gmail.com',
             '1234',
             '+49123456789',
             'CREATED',
             NOW(),
             NOW()
         );