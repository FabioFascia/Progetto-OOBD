PGDMP          +                y            DBProgettoOOBD    13.1    13.1 M               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    32869    DBProgettoOOBD    DATABASE     l   CREATE DATABASE "DBProgettoOOBD" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Italian_Italy.1252';
     DROP DATABASE "DBProgettoOOBD";
                adminprogetto    false            �            1255    65716    funzione_aumento_valutazione()    FUNCTION     c  CREATE FUNCTION public.funzione_aumento_valutazione() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    New_Val INTEGER;
BEGIN
    SELECT Valutazione INTO New_Val
    FROM DIPENDENTE
    WHERE CodF = NEW.CodF;

    New_Val := New_Val + 1;

    UPDATE DIPENDENTE
    SET Valutazione = New_Val
    WHERE CodF = NEW.CodF;

    RETURN NULL;
END;
$$;
 5   DROP FUNCTION public.funzione_aumento_valutazione();
       public          adminprogetto    false            �            1255    33087    funzione_capienza_sala()    FUNCTION     >  CREATE FUNCTION public.funzione_capienza_sala() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    Num_Posti INTEGER;
    Num_Dipendenti INTEGER;
BEGIN
    SELECT NumPosti INTO Num_Posti
    FROM MEETINGF AS MF NATURAL JOIN SALA AS S
    WHERE MF.CodMF = NEW.CodMF;

    SELECT COUNT(CodF) INTO Num_Dipendenti
    FROM PARTECIPAMF AS PF
    WHERE PF.CodMF = NEW.CodMF
    GROUP BY PF.CodMF;
      
    IF(Num_Dipendenti >= Num_Posti) THEN
	RAISE EXCEPTION 'ERRORE: Il limite di partecipanti al meeting è già stato raggiunto';
    END IF;

    RETURN NEW;
END;
$$;
 /   DROP FUNCTION public.funzione_capienza_sala();
       public          adminprogetto    false            �            1255    73761    funzione_consistenza_meetingf()    FUNCTION     �  CREATE FUNCTION public.funzione_consistenza_meetingf() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF EXISTS (SELECT *
		   FROM MEETINGF AS MF
		   WHERE NEW.CodSala = MF.CodSala AND NEW.Data = MF.Data AND
		   ((NEW.OraI BETWEEN MF.OraI AND MF.OraF) OR
		   (NEW.OraF BETWEEN MF.OraI AND MF.OraF))) THEN
	RAISE EXCEPTION 'ERRORE: Un altro meeting è in conflitto con quello inserito';
    END IF;

    RETURN NEW;
END;
$$;
 6   DROP FUNCTION public.funzione_consistenza_meetingf();
       public          adminprogetto    false            �            1255    73732 "   funzione_diminuzione_valutazione()    FUNCTION     g  CREATE FUNCTION public.funzione_diminuzione_valutazione() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    New_Val INTEGER;
BEGIN
    SELECT Valutazione INTO New_Val
    FROM DIPENDENTE
    WHERE CodF = OLD.CodF;

    New_Val := New_Val - 1;

    UPDATE DIPENDENTE
    SET Valutazione = New_Val
    WHERE CodF = OLD.CodF;

    RETURN NULL;
END;
$$;
 9   DROP FUNCTION public.funzione_diminuzione_valutazione();
       public          adminprogetto    false            �            1255    33043    funzione_limite_meetingt()    FUNCTION     \  CREATE FUNCTION public.funzione_limite_meetingt() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    Num_Massimo INTEGER;
    Num_Partecipanti INTEGER;
BEGIN

    SELECT NumLimite INTO Num_Massimo
    FROM MEETINGT AS MT
    WHERE MT.CodMT = NEW.CodMT;

    SELECT COUNT(CodF) INTO Num_Partecipanti
    FROM PARTECIPAMT AS PT
    WHERE PT.CodMT = NEW.CodMT
    GROUP BY PT.CodMT;
  
    IF(Num_Massimo IS NOT NULL AND Num_Partecipanti >= Num_Massimo) THEN
            RAISE EXCEPTION 'ERRORE: É stato superato il limite massimo di partecipanti al meeting';
    END IF;

    RETURN NEW;
END;
$$;
 1   DROP FUNCTION public.funzione_limite_meetingt();
       public          adminprogetto    false            �            1255    33093    funzione_partecipa_meetingf()    FUNCTION     �  CREATE FUNCTION public.funzione_partecipa_meetingf() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    data_meeting DATE;
    ora_inizio TIME;
    ora_fine TIME;
BEGIN
    SELECT MF.Data, MF.OraI, MF.OraF INTO data_meeting, ora_inizio, ora_fine
    FROM PARTECIPAMF AS PMF NATURAL JOIN MEETINGF AS MF
    WHERE NEW.CodF = PMF.CodF AND NEW.CodMF = MF.CodMF;

    IF EXISTS (SELECT *
		   FROM PARTECIPAMF AS PMF NATURAL JOIN
		   MEETINGF AS MF
		   WHERE NEW.CodF = PMF.CodF AND data_meeting = MF.Data AND 
		       ((ora_inizio BETWEEN MF.OraI AND MF.OraF) OR
			(ora_fine BETWEEN MF.OraI AND MF.OraF)))
	OR EXISTS (SELECT *
			FROM PARTECIPAMT AS PMT NATURAL JOIN
				MEETINGT AS MT
			WHERE NEW.CodF = PMT.CodF AND data_meeting = MT.Data AND
			    ((ora_inizio BETWEEN MT.OraI AND MT.OraF) OR
			    (ora_fine BETWEEN MT.OraI AND MT.OraF))) THEN
	RAISE EXCEPTION 'ERRORE: Questo dipendente partecipa già ad un altro meeting in quel lasso di tempo';
    END IF;

    RETURN NEW;
END;
$$;
 4   DROP FUNCTION public.funzione_partecipa_meetingf();
       public          adminprogetto    false            �            1255    33095    funzione_partecipa_meetingt()    FUNCTION     �  CREATE FUNCTION public.funzione_partecipa_meetingt() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    data_meeting DATE;
    ora_inizio TIME;
    ora_fine TIME;
BEGIN
    SELECT MT.Data, MT.OraI, MT.OraF INTO data_meeting, ora_inizio, ora_fine
    FROM PARTECIPAMT AS PMT NATURAL JOIN
	MEETINGT AS MT
    WHERE NEW.CodF = PMT.CodF AND NEW.CodMT = MT.CodMT;

    IF EXISTS (SELECT *
		   FROM PARTECIPAMF AS PMF NATURAL JOIN
			MEETINGF AS MF
		   WHERE NEW.CodF = PMF.CodF AND data_meeting = MF.Data AND 
		       ((ora_inizio BETWEEN MF.OraI AND MF.OraF) OR
			(ora_fine BETWEEN MF.OraI AND MF.OraF)))
	OR EXISTS (SELECT *
			FROM PARTECIPAMT AS PMT NATURAL JOIN
				MEETINGT AS MT
			WHERE NEW.CodF = PMT.CodF AND data_meeting = MT.Data AND
			    ((ora_inizio BETWEEN MT.OraI AND MT.OraF) OR
			    (ora_fine BETWEEN MT.OraI AND MT.OraF))) THEN
	RAISE EXCEPTION 'ERRORE: Questo dipendente partecipa già ad un altro meeting in quel lasso di tempo';
    END IF;

    RETURN NEW;
END;
$$;
 4   DROP FUNCTION public.funzione_partecipa_meetingt();
       public          adminprogetto    false            �            1255    73754    funzione_sequenza_meetingf()    FUNCTION     �   CREATE FUNCTION public.funzione_sequenza_meetingf() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF (NEW.CodMF IS NULL) THEN
	NEW.CodMF := NEXTVAL('N_MEETINGF');
    END IF;

    RETURN NEW;
END;
$$;
 3   DROP FUNCTION public.funzione_sequenza_meetingf();
       public          adminprogetto    false            �            1255    73756    funzione_sequenza_meetingt()    FUNCTION     �   CREATE FUNCTION public.funzione_sequenza_meetingt() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF (NEW.CodMT IS NULL) THEN
	NEW.CodMT := NEXTVAL('N_MEETINGT');
    END IF;

    RETURN NEW;
END;
$$;
 3   DROP FUNCTION public.funzione_sequenza_meetingt();
       public          adminprogetto    false            �            1255    73752    funzione_sequenza_progetto()    FUNCTION     �   CREATE FUNCTION public.funzione_sequenza_progetto() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF (NEW.CodP IS NULL) THEN
	NEW.CodP := NEXTVAL('N_PROGETTO');
    END IF;

    RETURN NEW;
END;
$$;
 3   DROP FUNCTION public.funzione_sequenza_progetto();
       public          adminprogetto    false            �            1255    73758    funzione_sequenza_sala()    FUNCTION     �   CREATE FUNCTION public.funzione_sequenza_sala() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF (NEW.CodSala IS NULL) THEN
	NEW.CodSala := NEXTVAL('N_SALA');
    END IF;

    RETURN NEW;
END;
$$;
 /   DROP FUNCTION public.funzione_sequenza_sala();
       public          adminprogetto    false            �            1255    65724 #   funzione_totalità_projectmanager()    FUNCTION     �  CREATE FUNCTION public."funzione_totalità_projectmanager"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF NOT EXISTS (SELECT *
		   FROM DIPENDENTE
		   WHERE CodF = 'AAAAAA00A00A000A') THEN
	INSERT INTO DIPENDENTE (CodF,Nome,Cognome,Valutazione)
	    VALUES ('AAAAAA00A00A000A', 'A', 'A', 0);
    END IF;

    INSERT INTO PARTECIPANTE (CodF, CodP, Ruolo)
	VALUES('AAAAAA00A00A000A', NEW.CodP, 'project manager');

    RETURN NEW;
END;
$$;
 <   DROP FUNCTION public."funzione_totalità_projectmanager"();
       public          adminprogetto    false            �            1255    33031 "   funzione_unicità_projectmanager()    FUNCTION     x  CREATE FUNCTION public."funzione_unicità_projectmanager"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF EXISTS (SELECT *
               FROM PARTECIPANTE AS P
               WHERE P.CodP = NEW.CodP AND P.Ruolo = 'Project Manager') THEN
        RAISE EXCEPTION 'ERRORE ESISTE GIA UN PROJECT MANAGER ASSOCIATO AL PROGETTO';
    END IF;

    RETURN NEW;
END;
$$;
 ;   DROP FUNCTION public."funzione_unicità_projectmanager"();
       public          adminprogetto    false            �            1255    73751    get_salario_medio()    FUNCTION     �   CREATE FUNCTION public.get_salario_medio() RETURNS real
    LANGUAGE plpgsql
    AS $$
DECLARE
	ret REAL;
BEGIN
    ret = (SELECT AVG(D.Salario)
           FROM DIPENDENTE AS D);
		   
	RETURN ret;
END
$$;
 *   DROP FUNCTION public.get_salario_medio();
       public          adminprogetto    false            �            1255    73760 @   insert_progetto(character varying, character, character varying) 	   PROCEDURE     k  CREATE PROCEDURE public.insert_progetto(tipologia character varying, codpm character, descrizione character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO PROGETTO (CodP, Tipologia, Descrizione)
	VALUES (0, tipologia, descrizione);

    UPDATE PARTECIPANTE
    SET CodF = codpm
    WHERE CodP = new_prog AND Ruolo ILIKE 'Project Manager';
END;
$$;
 t   DROP PROCEDURE public.insert_progetto(tipologia character varying, codpm character, descrizione character varying);
       public          adminprogetto    false            �            1259    65600    ambito    TABLE     ]   CREATE TABLE public.ambito (
    codp integer,
    keyword character varying(30) NOT NULL
);
    DROP TABLE public.ambito;
       public         heap    adminprogetto    false            �            1259    65537 
   dipendente    TABLE     F  CREATE TABLE public.dipendente (
    codf character(16) DEFAULT 'AAAAAA00A00A000A'::bpchar NOT NULL,
    nome character varying(30) DEFAULT ''::character varying NOT NULL,
    cognome character varying(30) NOT NULL,
    salario real,
    valutazione integer DEFAULT 0 NOT NULL,
    CONSTRAINT dipendente_codf_check CHECK ((codf ~* '^[A-Za-z]{6}[0-9]{2}[A-Za-z][0-9]{2}[A-Za-z][0-9]{3}[A-Za-z]$'::text)),
    CONSTRAINT dipendente_cognome_check CHECK (((cognome)::text ~* '^[A-Za-z ]+$'::text)),
    CONSTRAINT dipendente_nome_check CHECK (((nome)::text ~* '^[A-Za-z ]+$'::text))
);
    DROP TABLE public.dipendente;
       public         heap    adminprogetto    false            �            1259    65662    meetingf    TABLE     �   CREATE TABLE public.meetingf (
    codmf integer NOT NULL,
    codp integer,
    data date NOT NULL,
    orai time without time zone NOT NULL,
    oraf time without time zone NOT NULL,
    codsala integer
);
    DROP TABLE public.meetingf;
       public         heap    adminprogetto    false            �            1259    65575    meetingt    TABLE       CREATE TABLE public.meetingt (
    codmt integer NOT NULL,
    codp integer,
    data date NOT NULL,
    orai time without time zone NOT NULL,
    oraf time without time zone NOT NULL,
    piattaforma character varying(30) NOT NULL,
    numlimite integer
);
    DROP TABLE public.meetingt;
       public         heap    adminprogetto    false            �            1259    32966 
   n_meetingf    SEQUENCE     r   CREATE SEQUENCE public.n_meetingf
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.n_meetingf;
       public          adminprogetto    false            �            1259    32968 
   n_meetingt    SEQUENCE     r   CREATE SEQUENCE public.n_meetingt
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.n_meetingt;
       public          adminprogetto    false            �            1259    32964 
   n_progetto    SEQUENCE     r   CREATE SEQUENCE public.n_progetto
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.n_progetto;
       public          adminprogetto    false            �            1259    32970    n_sala    SEQUENCE     n   CREATE SEQUENCE public.n_sala
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;
    DROP SEQUENCE public.n_sala;
       public          adminprogetto    false            �            1259    65677    partecipamf    TABLE     O   CREATE TABLE public.partecipamf (
    codf character(16),
    codmf integer
);
    DROP TABLE public.partecipamf;
       public         heap    adminprogetto    false            �            1259    65621    partecipamt    TABLE     O   CREATE TABLE public.partecipamt (
    codf character(16),
    codmt integer
);
    DROP TABLE public.partecipamt;
       public         heap    adminprogetto    false            �            1259    65585    partecipante    TABLE     y   CREATE TABLE public.partecipante (
    codf character(16),
    codp integer,
    ruolo character varying(30) NOT NULL
);
     DROP TABLE public.partecipante;
       public         heap    adminprogetto    false            �            1259    65547    progetto    TABLE     �   CREATE TABLE public.progetto (
    codp integer NOT NULL,
    tipologia character varying(30) NOT NULL,
    descrizione character varying(200)
);
    DROP TABLE public.progetto;
       public         heap    adminprogetto    false            �            1259    65654    sala    TABLE     �  CREATE TABLE public.sala (
    codsala integer NOT NULL,
    "città" character varying(30) NOT NULL,
    provincia character(2) NOT NULL,
    indirizzo character varying(50) NOT NULL,
    numerocivico integer,
    numposti integer NOT NULL,
    CONSTRAINT "sala_città_check" CHECK ((("città")::text ~* '^[A-Za-z ]+$'::text)),
    CONSTRAINT sala_indirizzo_check CHECK (((indirizzo)::text ~* '^[A-Za-z ]+$'::text)),
    CONSTRAINT sala_provincia_check CHECK ((provincia ~* '^[A-Z]{2}$'::text))
);
    DROP TABLE public.sala;
       public         heap    adminprogetto    false                      0    65600    ambito 
   TABLE DATA           /   COPY public.ambito (codp, keyword) FROM stdin;
    public          adminprogetto    false    208   Wu                 0    65537 
   dipendente 
   TABLE DATA           O   COPY public.dipendente (codf, nome, cognome, salario, valutazione) FROM stdin;
    public          adminprogetto    false    204   �u                 0    65662    meetingf 
   TABLE DATA           J   COPY public.meetingf (codmf, codp, data, orai, oraf, codsala) FROM stdin;
    public          adminprogetto    false    211   .w                 0    65575    meetingt 
   TABLE DATA           Y   COPY public.meetingt (codmt, codp, data, orai, oraf, piattaforma, numlimite) FROM stdin;
    public          adminprogetto    false    206   �w                 0    65677    partecipamf 
   TABLE DATA           2   COPY public.partecipamf (codf, codmf) FROM stdin;
    public          adminprogetto    false    212   �x                 0    65621    partecipamt 
   TABLE DATA           2   COPY public.partecipamt (codf, codmt) FROM stdin;
    public          adminprogetto    false    209   By                 0    65585    partecipante 
   TABLE DATA           9   COPY public.partecipante (codf, codp, ruolo) FROM stdin;
    public          adminprogetto    false    207   �y                 0    65547    progetto 
   TABLE DATA           @   COPY public.progetto (codp, tipologia, descrizione) FROM stdin;
    public          adminprogetto    false    205   s{                 0    65654    sala 
   TABLE DATA           _   COPY public.sala (codsala, "città", provincia, indirizzo, numerocivico, numposti) FROM stdin;
    public          adminprogetto    false    210   "|                  0    0 
   n_meetingf    SEQUENCE SET     9   SELECT pg_catalog.setval('public.n_meetingf', 11, true);
          public          adminprogetto    false    201                       0    0 
   n_meetingt    SEQUENCE SET     8   SELECT pg_catalog.setval('public.n_meetingt', 7, true);
          public          adminprogetto    false    202                       0    0 
   n_progetto    SEQUENCE SET     8   SELECT pg_catalog.setval('public.n_progetto', 7, true);
          public          adminprogetto    false    200                        0    0    n_sala    SEQUENCE SET     4   SELECT pg_catalog.setval('public.n_sala', 5, true);
          public          adminprogetto    false    203            a           2606    65546    dipendente dipendente_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.dipendente
    ADD CONSTRAINT dipendente_pkey PRIMARY KEY (codf);
 D   ALTER TABLE ONLY public.dipendente DROP CONSTRAINT dipendente_pkey;
       public            adminprogetto    false    204            m           2606    65666    meetingf meetingf_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.meetingf
    ADD CONSTRAINT meetingf_pkey PRIMARY KEY (codmf);
 @   ALTER TABLE ONLY public.meetingf DROP CONSTRAINT meetingf_pkey;
       public            adminprogetto    false    211            e           2606    65579    meetingt meetingt_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.meetingt
    ADD CONSTRAINT meetingt_pkey PRIMARY KEY (codmt);
 @   ALTER TABLE ONLY public.meetingt DROP CONSTRAINT meetingt_pkey;
       public            adminprogetto    false    206            c           2606    65551    progetto progetto_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.progetto
    ADD CONSTRAINT progetto_pkey PRIMARY KEY (codp);
 @   ALTER TABLE ONLY public.progetto DROP CONSTRAINT progetto_pkey;
       public            adminprogetto    false    205            k           2606    65661    sala sala_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.sala
    ADD CONSTRAINT sala_pkey PRIMARY KEY (codsala);
 8   ALTER TABLE ONLY public.sala DROP CONSTRAINT sala_pkey;
       public            adminprogetto    false    210            o           2606    73747    partecipamf u_pmf 
   CONSTRAINT     S   ALTER TABLE ONLY public.partecipamf
    ADD CONSTRAINT u_pmf UNIQUE (codf, codmf);
 ;   ALTER TABLE ONLY public.partecipamf DROP CONSTRAINT u_pmf;
       public            adminprogetto    false    212    212            i           2606    73749    partecipamt u_pmt 
   CONSTRAINT     S   ALTER TABLE ONLY public.partecipamt
    ADD CONSTRAINT u_pmt UNIQUE (codf, codmt);
 ;   ALTER TABLE ONLY public.partecipamt DROP CONSTRAINT u_pmt;
       public            adminprogetto    false    209    209            g           2606    65589    partecipante u_ruolo 
   CONSTRAINT     U   ALTER TABLE ONLY public.partecipante
    ADD CONSTRAINT u_ruolo UNIQUE (codf, codp);
 >   ALTER TABLE ONLY public.partecipante DROP CONSTRAINT u_ruolo;
       public            adminprogetto    false    207    207            �           2620    65718 0   partecipamf trigger_aumento_valutazione_meetingf    TRIGGER     �   CREATE TRIGGER trigger_aumento_valutazione_meetingf AFTER INSERT ON public.partecipamf FOR EACH ROW EXECUTE FUNCTION public.funzione_aumento_valutazione();
 I   DROP TRIGGER trigger_aumento_valutazione_meetingf ON public.partecipamf;
       public          adminprogetto    false    228    212            �           2620    65719 0   partecipamt trigger_aumento_valutazione_meetingt    TRIGGER     �   CREATE TRIGGER trigger_aumento_valutazione_meetingt AFTER INSERT ON public.partecipamt FOR EACH ROW EXECUTE FUNCTION public.funzione_aumento_valutazione();
 I   DROP TRIGGER trigger_aumento_valutazione_meetingt ON public.partecipamt;
       public          adminprogetto    false    228    209            }           2620    73729 1   partecipante trigger_aumento_valutazione_progetto    TRIGGER     �   CREATE TRIGGER trigger_aumento_valutazione_progetto AFTER INSERT ON public.partecipante FOR EACH ROW EXECUTE FUNCTION public.funzione_aumento_valutazione();
 J   DROP TRIGGER trigger_aumento_valutazione_progetto ON public.partecipante;
       public          adminprogetto    false    207    228            �           2620    73734 4   partecipamf trigger_diminuzione_valutazione_meetingf    TRIGGER     �   CREATE TRIGGER trigger_diminuzione_valutazione_meetingf AFTER DELETE ON public.partecipamf FOR EACH ROW EXECUTE FUNCTION public.funzione_diminuzione_valutazione();
 M   DROP TRIGGER trigger_diminuzione_valutazione_meetingf ON public.partecipamf;
       public          adminprogetto    false    215    212            �           2620    73735 4   partecipamt trigger_diminuzione_valutazione_meetingt    TRIGGER     �   CREATE TRIGGER trigger_diminuzione_valutazione_meetingt AFTER DELETE ON public.partecipamt FOR EACH ROW EXECUTE FUNCTION public.funzione_diminuzione_valutazione();
 M   DROP TRIGGER trigger_diminuzione_valutazione_meetingt ON public.partecipamt;
       public          adminprogetto    false    215    209            ~           2620    73733 5   partecipante trigger_diminuzione_valutazione_progetto    TRIGGER     �   CREATE TRIGGER trigger_diminuzione_valutazione_progetto AFTER DELETE ON public.partecipante FOR EACH ROW EXECUTE FUNCTION public.funzione_diminuzione_valutazione();
 N   DROP TRIGGER trigger_diminuzione_valutazione_progetto ON public.partecipante;
       public          adminprogetto    false    215    207            �           2620    73745 .   partecipante trigger_modifica_valutazione_down    TRIGGER     �   CREATE TRIGGER trigger_modifica_valutazione_down AFTER UPDATE ON public.partecipante FOR EACH ROW EXECUTE FUNCTION public.funzione_diminuzione_valutazione();
 G   DROP TRIGGER trigger_modifica_valutazione_down ON public.partecipante;
       public          adminprogetto    false    215    207                       2620    73744 ,   partecipante trigger_modifica_valutazione_up    TRIGGER     �   CREATE TRIGGER trigger_modifica_valutazione_up AFTER UPDATE ON public.partecipante FOR EACH ROW EXECUTE FUNCTION public.funzione_aumento_valutazione();
 E   DROP TRIGGER trigger_modifica_valutazione_up ON public.partecipante;
       public          adminprogetto    false    228    207            �           2620    73755 "   meetingf trigger_sequenza_meetingf    TRIGGER     �   CREATE TRIGGER trigger_sequenza_meetingf BEFORE INSERT ON public.meetingf FOR EACH ROW EXECUTE FUNCTION public.funzione_sequenza_meetingf();
 ;   DROP TRIGGER trigger_sequenza_meetingf ON public.meetingf;
       public          adminprogetto    false    232    211            |           2620    73757 "   meetingt trigger_sequenza_meetingt    TRIGGER     �   CREATE TRIGGER trigger_sequenza_meetingt BEFORE INSERT ON public.meetingt FOR EACH ROW EXECUTE FUNCTION public.funzione_sequenza_meetingt();
 ;   DROP TRIGGER trigger_sequenza_meetingt ON public.meetingt;
       public          adminprogetto    false    233    206            z           2620    73753 "   progetto trigger_sequenza_progetto    TRIGGER     �   CREATE TRIGGER trigger_sequenza_progetto BEFORE INSERT ON public.progetto FOR EACH ROW EXECUTE FUNCTION public.funzione_sequenza_progetto();
 ;   DROP TRIGGER trigger_sequenza_progetto ON public.progetto;
       public          adminprogetto    false    231    205            �           2620    73759    sala trigger_sequenza_sala    TRIGGER     �   CREATE TRIGGER trigger_sequenza_sala BEFORE INSERT ON public.sala FOR EACH ROW EXECUTE FUNCTION public.funzione_sequenza_sala();
 3   DROP TRIGGER trigger_sequenza_sala ON public.sala;
       public          adminprogetto    false    234    210            �           2620    73762 %   meetingf vincolo_consistenza_meetingf    TRIGGER     �   CREATE TRIGGER vincolo_consistenza_meetingf BEFORE INSERT ON public.meetingf FOR EACH ROW EXECUTE FUNCTION public.funzione_consistenza_meetingf();
 >   DROP TRIGGER vincolo_consistenza_meetingf ON public.meetingf;
       public          adminprogetto    false    230    211            {           2620    65725 )   progetto vincolo_totalità_projectmanager    TRIGGER     �   CREATE TRIGGER "vincolo_totalità_projectmanager" AFTER INSERT ON public.progetto FOR EACH ROW EXECUTE FUNCTION public."funzione_totalità_projectmanager"();
 D   DROP TRIGGER "vincolo_totalità_projectmanager" ON public.progetto;
       public          adminprogetto    false    205    213            x           2606    65680    partecipamf fk1_mf    FK CONSTRAINT     �   ALTER TABLE ONLY public.partecipamf
    ADD CONSTRAINT fk1_mf FOREIGN KEY (codf) REFERENCES public.dipendente(codf) ON UPDATE CASCADE;
 <   ALTER TABLE ONLY public.partecipamf DROP CONSTRAINT fk1_mf;
       public          adminprogetto    false    2913    204    212            t           2606    65624    partecipamt fk1_mt    FK CONSTRAINT     �   ALTER TABLE ONLY public.partecipamt
    ADD CONSTRAINT fk1_mt FOREIGN KEY (codf) REFERENCES public.dipendente(codf) ON UPDATE CASCADE;
 <   ALTER TABLE ONLY public.partecipamt DROP CONSTRAINT fk1_mt;
       public          adminprogetto    false    2913    209    204            y           2606    65685    partecipamf fk2_mf    FK CONSTRAINT     �   ALTER TABLE ONLY public.partecipamf
    ADD CONSTRAINT fk2_mf FOREIGN KEY (codmf) REFERENCES public.meetingf(codmf) ON UPDATE CASCADE;
 <   ALTER TABLE ONLY public.partecipamf DROP CONSTRAINT fk2_mf;
       public          adminprogetto    false    212    2925    211            u           2606    65629    partecipamt fk2_mt    FK CONSTRAINT     �   ALTER TABLE ONLY public.partecipamt
    ADD CONSTRAINT fk2_mt FOREIGN KEY (codmt) REFERENCES public.meetingt(codmt) ON UPDATE CASCADE;
 <   ALTER TABLE ONLY public.partecipamt DROP CONSTRAINT fk2_mt;
       public          adminprogetto    false    206    209    2917            s           2606    65603    ambito fk_ambito    FK CONSTRAINT     �   ALTER TABLE ONLY public.ambito
    ADD CONSTRAINT fk_ambito FOREIGN KEY (codp) REFERENCES public.progetto(codp) ON UPDATE CASCADE;
 :   ALTER TABLE ONLY public.ambito DROP CONSTRAINT fk_ambito;
       public          adminprogetto    false    208    2915    205            q           2606    65590    partecipante fk_codf    FK CONSTRAINT     �   ALTER TABLE ONLY public.partecipante
    ADD CONSTRAINT fk_codf FOREIGN KEY (codf) REFERENCES public.dipendente(codf) ON UPDATE CASCADE;
 >   ALTER TABLE ONLY public.partecipante DROP CONSTRAINT fk_codf;
       public          adminprogetto    false    207    204    2913            r           2606    65595    partecipante fk_codp    FK CONSTRAINT     �   ALTER TABLE ONLY public.partecipante
    ADD CONSTRAINT fk_codp FOREIGN KEY (codp) REFERENCES public.progetto(codp) ON UPDATE CASCADE;
 >   ALTER TABLE ONLY public.partecipante DROP CONSTRAINT fk_codp;
       public          adminprogetto    false    2915    207    205            v           2606    65667    meetingf fk_mf_progetto    FK CONSTRAINT     �   ALTER TABLE ONLY public.meetingf
    ADD CONSTRAINT fk_mf_progetto FOREIGN KEY (codp) REFERENCES public.progetto(codp) ON UPDATE CASCADE;
 A   ALTER TABLE ONLY public.meetingf DROP CONSTRAINT fk_mf_progetto;
       public          adminprogetto    false    2915    211    205            p           2606    65580    meetingt fk_mt_progetto    FK CONSTRAINT     �   ALTER TABLE ONLY public.meetingt
    ADD CONSTRAINT fk_mt_progetto FOREIGN KEY (codp) REFERENCES public.progetto(codp) ON UPDATE CASCADE;
 A   ALTER TABLE ONLY public.meetingt DROP CONSTRAINT fk_mt_progetto;
       public          adminprogetto    false    205    206    2915            w           2606    65672    meetingf fk_sala    FK CONSTRAINT     �   ALTER TABLE ONLY public.meetingf
    ADD CONSTRAINT fk_sala FOREIGN KEY (codsala) REFERENCES public.sala(codsala) ON UPDATE CASCADE;
 :   ALTER TABLE ONLY public.meetingf DROP CONSTRAINT fk_sala;
       public          adminprogetto    false    210    211    2923               G   x�3�LM�����L��2�,N�L�+�L�9�r�3�LP�M�4p&%g����g&r�s:eB�1z\\\ t�[         p  x�%�͎�0���W�����!�0��j/��� 186�}�xv�[}*u�W�8�CI�Q�$\8(W����/�$�8�s��*������0��\g�b�Pe�A��g<�d��:��pti�[�Z�f�����5�%��2N��Ӧ*	�8Ӓ[b��}�@��a��V���%��`�~�`�v�����(�2��[k��9I+�T�:!�����=��緜Q�2������j,XMI-Y�+�oa?#��V�'�i{q����T���3<|����ߖD�3!�@z�\����Z�����{ ����<��J�V���+ǔ����8�#&�:��yK\4��t4�R�$�_��~��#����c�|!})�3��K�O         �   x�}�A� ��_\�ژ��%�G	IH�C%vC 6C���W�a��+H`G���ĉTc
څ\qз�,F7!��1��@�@cȸ
�f;\r9v��C��Ch�r��a�]T�g̔�{��8-�������P�w�����<         �   x�]�;�0D��Sp��ؐp��@�� d�Ȉ������\l1o���AHȐQ�vC�֣��_a�iq�a�~��2ΰ$�]e43����	�$9�-PS̵}�������U���Z�����rC������ҏ�\��B����I����w_��ߧ�Q���Tk�p�}�%"~ ��CY         �   x�e�1� ����=&D��(F����k�~ï����o����Z��Їԛ���W�~�N����,RT�����>I&���%HI� ���>��frٰ��=�����Z�"dw�t���4�^�f92m�yQ����!�3�2c&�t�� |�e9}         �   x�]�;� �S�=R"�P@FU���5�'��ȟ`lq��E�� ����E��f��Y@QV_)՘	2�l�Ǣ��ق%&�yQ�c�d0�	�V�ojCΉ�i��v�Զ;���n��z]ۨ=���!���ӿ���֠0�         {  x���=O�0�g�WtcC�?{l%Q��*�: ��T�|T���������w���hAV�,B����^�s3�G�e�F�[>��8)+E��t�̌/����(�e�*��X("���$eY+���,����g�PY�\$���8MWi�x�ƣ~6=xξ�3�>Z���*A*.TLx~���p,@���==f4Gg}ȏ)L@Ŧ��e��'9'����Zm
ζD����#�W蜶S�i��Y`Q�H(SW����=�(7��,x��e��m&p�i�Z!r�����؁axZ>�UQ���h<�Px
���!���_�pk1�����V��������j��>��B�k��i'���w�~����0�!z���� :         �   x�e�=�0���9�3t`�bc1�Yj��I8=�QE�����ޙ&t�9���^I&1�`qH*vR�����j9tsL�8��}����1G�5�f6�C�q�<������-�E���N�<RH_m�]ַL;�����ܢ�ߙ����4K	 � I&_�         �   x�]��N�0D�ٯ��'utt���h��+Y���]���T�����n�V���q�qSv/�l�0�G�1ib˘N�xL_R6��ֺj�|��i��p���2ڥ�yU���H�\���owhfsO�m��dt!������*�*�C�E��S���N�&~�nI��F����l��!x�<�7�KE�     