CREATE TABLE public.CARDS_COUNTERPARTY (
counterparty_id		VARCHAR(255) NOT NULL ,
counterparty_name	VARCHAR(255) NOT NULL ,
counterparty_type	VARCHAR(255)	      ,
counterparty_company_number	VARCHAR(255)  ,
counterparty_branch_name	VARCHAR(255)  ,
counterparty_jurisdiction	VARCHAR(255)  ,
lei_code			VARCHAR(255) NOT NULL ,
city				VARCHAR(255) ,
country				VARCHAR(255) ,
address				TEXT	     ,
CONSTRAINT PK_CARDS_COUNTERPARTY PRIMARY KEY (counterparty_id)
) ;