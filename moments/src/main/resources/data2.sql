INSERT
INTO
  users
  (name, user_Name, user_Img, password, email, followers, following, description, date_Of_Birth, ubication)
VALUES
  ('Eric Puigvendrello', 'eric_puig', 'https://media-exp2.licdn.com/dms/image/C4D03AQG17WUfd78sgA/profile-displayphoto-shrink_400_400/0/1587477510501?e=1661385600&v=beta&t=lxPzlDjTbmYs0vSz-B7ef95gRfs-T5Dxj_RTLrcSO8Y', 'password', 'firstuser@gmail.com', 930 , 400 , 'This is my profile!', '21/09/2000', 'Tona, BCN');


INSERT
INTO
  users
  (name, user_Name, user_Img, password, email, followers, following, description, date_Of_Birth, ubication)
VALUES
  ('Monalisa', 'monalisa_official', 'https://ichef.bbci.co.uk/news/640/cpsprodpb/17C16/production/_117320379_giocondacerca.jpg', 'password', 'monalisa@gmail.com', 10, 1, 'Hello', '1aC','Louvre');
INSERT
INTO
  users
  (name, user_Name, user_Img, password, email, followers, following, description, date_Of_Birth, ubication)
VALUES
  ('a', 'a', 'a', 'a', 'a', 0, 0, 'a', 'a', 'a');

  INSERT
  INTO
    users
    (name, user_Name, user_Img, password, email, followers, following, description, date_Of_Birth, ubication)
  VALUES
    ('b', 'b', 'b', 'b', 'b', 0, 0, 'b', 'b', 'b');


  INSERT
INTO
  moments
  (title, img_Url, description, ubication, creator_id)
VALUES
  ('The Lake', 'https://cdn.pixabay.com/photo/2020/02/13/22/36/landscape-4847020_960_720.jpg', 'This is a lake with a description longer than others to prove that description is cuttin at a limit characters', 'Lago de Sanabria', 1);

    INSERT
  INTO
    moments
    (title, img_Url, description, ubication, creator_id)
  VALUES
    ('Rainy day', 'https://cdn.pixabay.com/photo/2018/01/14/23/12/nature-3082832__340.jpg', 'This is a rainy day', 'Transilvania', 2);
      INSERT
    INTO
      moments
      (title, img_Url, description, ubication, creator_id)
    VALUES
      ('Autumn day', 'https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg', 'This is a tipical autumn day', 'Boston', 1);
        INSERT
      INTO
        moments
        (title, img_Url, description, ubication, creator_id)
      VALUES
        ('Forest', 'https://cdn.pixabay.com/photo/2015/06/19/21/24/avenue-815297__340.jpg', 'This is a photo of a wild forest', 'Tona', 1);
          INSERT
        INTO
          moments
          (title, img_Url, description, ubication, creator_id)
        VALUES
          ('Rainbow', 'https://cdn.pixabay.com/photo/2015/05/18/20/05/rainbow-772815_960_720.jpg', 'There is a treasure at the end of the rainbow', 'Somewhere', 1);
            INSERT
          INTO
            moments
            (title, img_Url, description, ubication, creator_id)
          VALUES
            ('Sunset', 'https://cdn.pixabay.com/photo/2019/04/07/11/24/landscape-4109455_960_720.jpg', 'Best sunset', 'Menorca', 1);
              INSERT
            INTO
              moments
              (title, img_Url, description, ubication, creator_id)
            VALUES
              ('Snowed mountains', 'https://cdn.pixabay.com/photo/2017/02/01/22/02/mountain-landscape-2031539_960_720.jpg', 'This is a mountain landscape', 'Mountains', 1);

INSERT
INTO
  comments
  (comment, creator_id, moment_id)
VALUES
  ('Soc un comment que em podran posar like.', 1, 1);




INSERT
INTO
  comments
  (comment, creator_id, moment_id)
VALUES
  ('Soy la mejor', 2, 1);

INSERT
INTO
  moments
  (description, title, img_Url, ubication, creator_id)
VALUES
  ('a', 'a', 'https://cdn.pixabay.com/photo/2017/02/01/22/02/mountain-landscape-2031539_960_720.jpg', 'Tona', 2);

INSERT
INTO
  favs
  (faver_id, moment_id)
VALUES
  (3, 2);
  INSERT
  INTO
    favs
    (faver_id, moment_id)
  VALUES
    (4, 2);
  INSERT
  INTO
    favs
    (faver_id, moment_id)
  VALUES
    (4, 1);