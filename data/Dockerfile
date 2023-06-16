# Utiliser l'image officielle de MySQL
FROM mysql:8.0

# Copier le script SQL pour initialiser la base de données
COPY chabiba_support.sql ./docker-entrypoint-initdb.d/

# Définir les variables d'environnement pour la configuration de MySQL
ENV MYSQL_DATABASE=chabiba_support
ENV MYSQL_ROOT_PASSWORD=root

# Exposer le port par défaut de MySQL
EXPOSE 3306
