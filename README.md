## Readme

# PayMyBuddy
## Qu'est ce que c'est?
**PayMyBuddy** est une appli qui permettrait aux clients de transférer de l'argent pour gérer leurs finances ou payer leurs amis.

 ## Lien
 https://github.com/ErikM06/OC_P6_PayMyBuddy.git
 
 ## Initialisation de la Database
 -  Créer dans votre outil de gestion de DB une base de donnée nommée : <code> [paymybuddy_db] </code>
 - Dans votre IDE ou votre Environnement : 
	  -  ${MYSQL_USERNAME}  = {votre username}
	  - ${MYSQL_PASSWORD} = {votre password}

La DB s'initialise par schema.sql et data.sql.

( Si vous souhaitez initialiser la DB par le code, enlever les commentaires de la classe <code> InitDbValues </code> et supprimez  
<code> spring.sql.init.schema-locations=classpath:script.sql 
spring.sql.init.data-locations=classpath:data.sql </code>
puis changer <code> spring.jpa.hibernate.ddl-auto </code> a votre préférence. )

## Lancer l'application
Sur votre IDE une fois vos variables d'environnements renseignées.
Par la command-line avec : <code> mvn spring-boot:run </code>

 
## Fichiers

![BDD diagram](diagrams/P6_DBB_Modele/P6_DBB_Modele.png)
![Class diagram](diagrams/P6_DBB_Modele/domaine_metier_P6.png)

## Login
Logs à utiliser pour se connecter avec les Users fictifs de la DB.
username = Nathalie,  password = nathMDP 
username = Bob, password = BobMDP
username = Yann, password= YannMDP 
username = Marine, password = MarineMDP
username = Admin, password=  admin



> Written with [StackEdit](https://stackedit.io/).
