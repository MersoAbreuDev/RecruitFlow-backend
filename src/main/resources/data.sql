
   INSERT INTO tb_usuario ( email, "role", senha , nome)
 SELECT  'root@gmail.com','ADMIN','$2a$12$W4ga0wBDtSMXGY0y41/PAuY8o0E.SGucCpddVRY6gvZhPxA16bpGi','root'
    WHERE NOT EXISTS (SELECT email FROM  tb_usuario WHERE id  = 1);

    INSERT INTO tb_usuario ( email, "role", senha , nome)
     SELECT  'mario@gmail.com','USER','$2a$12$W4ga0wBDtSMXGY0y41/PAuY8o0E.SGucCpddVRY6gvZhPxA16bpGi','Mario Jose'
        WHERE NOT EXISTS (SELECT email FROM  tb_usuario WHERE id  = 2);