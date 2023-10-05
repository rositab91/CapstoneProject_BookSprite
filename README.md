# 📚🧚‍♀️ BookSprite - Il Tuo Booktracker Personale

Benvenuto nel repository di BookSprite, la soluzione perfetta per gli appassionati di libri! Questo progetto fullstack, realizzato con Java e Angular, ti permette di tracciare i tuoi progressi di lettura e pianificare le tue prossime avventure letterarie, tutto con uno stile elegante e una UI intuitiva.

## 🌟 Funzionalità

- **Ricerca libri**: Sfruttando l'API di Google Books, trova facilmente i tuoi libri preferiti.
- **Stato della lettura**: Contrassegna ogni libro come "To Read", "Reading" o "Read" con un solo click.
- **Gestione sicura**: Grazie a Spring Security, i tuoi dati sono sempre protetti e sicuri.

## 🛠️ Tecnologie Utilizzate

### Frontend

- **Angular**: Usato per creare un'interfaccia utente dinamica e reattiva.

### Backend

- **Java Spring Boot**: Scelto per la sua robustezza e flessibilità nella creazione di applicazioni web.
- **Spring Security**: Garantisce un'ottima sicurezza e gestione delle autenticazioni.
- **PostgreSQL**: Usato come database affidabile e performante.

## 🚀 Installazione e Esecuzione

1. **Configurazione del Backend**:
    - Assicurati di avere Java JDK e Maven installati.
    - Naviga nella directory del backend.
    - Configura il tuo database PostgreSQL e aggiorna `application.yml` con le tue credenziali.
    - Esegui l'applicazione.

⚠️ **Attenzione/Importante**: Istruzioni o informazioni importanti vanno qui.

Dopo aver creato le tabelle, effettuare il seguente cambiamento per garantire che la descrizione dei libri possa essere conservata correttamente:
    - Naviga alla tabella google_books_info_volume.
    - Trova la colonna "description".
    - Click destro su "description" e seleziona "properties".
    - Vai alla sezione "definition" e modifica "Length" da (255) a (10000).

2. **Configurazione del Frontend**:
    - Assicurati di avere Node.js e Angular CLI installati.
    - Naviga nella directory del frontend.
    - Esegui `npm install` per installare le dipendenze.
    - Avvia l'applicazione con `ng serve`. L'app sarà disponibile all'indirizzo `http://localhost:4200`.
  
## 🙌 Contribuire

Se desideri contribuire a BookSprite, sei il benvenuto! Crea una fork del repository, apporta le tue modifiche e invia una Pull Request.

---

Grazie per l'interesse in BookSprite! 📖✨

---

🖋️ Creato con ❤️ da Rosita.

-------------------------------------------------------------------

# 📚🧚‍♀️ BookSprite - Your Personal Booktracker

Welcome to the BookSprite repository, the perfect solution for book enthusiasts! This fullstack project, crafted with Java and Angular, allows you to track your reading progress and plan your next literary adventures, all with a sleek design and an intuitive UI.

## 🌟 Features

- **Book Search**: Using the Google Books API, you can easily find your favorite books.
- **Reading Status**: Mark each book as "To Read", "Reading", or "Read" with just one click.
- **Secure Management**: Thanks to Spring Security, your data is always protected and secure.

## 🛠️ Technologies Used

### Frontend

- **Angular**: For crafting a dynamic and responsive user interface.

### Backend

- **Java Spring Boot**: Chosen for its robustness and flexibility in building web apps.
- **Spring Security**: Ensures top-notch security and authentication management.
- **PostgreSQL**: Used as a reliable and high-performance database.

## 🚀 Installation and Execution

1. **Backend Setup**:
    - Ensure you have the Java JDK and Maven installed.
    - Navigate to the backend directory.
    - Set up your PostgreSQL database and update `application.yml` with your credentials.
    - Start the application.
  
⚠️ Caution/Important: Important instructions or information are provided here.

After creating the tables, perform the following change to ensure book descriptions are stored properly:
    - Navigate to the google_books_info_volume table.
    - Locate the "description" column.
    - Right-click on "description" and select "properties".
    - Go to the "definition" section and change "Length" from (255) to (10000).

2. **Frontend Setup**:
    - Ensure you have Node.js and Angular CLI installed.
    - Navigate to the frontend directory.
    - Run `npm install` to install dependencies.
    - Start the app with `ng serve`. It will be available at `http://localhost:4200`.

## 🙌 Contribute

If you wish to contribute to BookSprite, you're most welcome! Fork the repo, make your changes, and send a Pull Request.

---

Thanks for your interest in BookSprite! 📖✨

---

🖋️ Created with ❤️ by Rosita.

-------------------------------------------------------------------

# 📚🧚‍♀️ BookSprite - Votre Booktracker Personnel

Bienvenue sur le dépôt de BookSprite, la solution parfaite pour les amateurs de livres ! Ce projet fullstack, réalisé avec Java et Angular, vous permet de suivre vos progrès de lecture et de planifier vos prochaines aventures littéraires, le tout avec un design élégant et une interface intuitive.

## 🌟 Fonctionnalités

- **Recherche de livres**: En utilisant l'API Google Books, vous pouvez facilement trouver vos livres préférés.
- **Statut de lecture**: Marquez chaque livre comme "À Lire", "En Cours de Lecture", ou "Lu" en un seul clic.
- **Gestion sécurisée**: Grâce à Spring Security, vos données sont toujours protégées et sécurisées.

## 🛠️ Technologies utilisées

### Frontend

- **Angular**: Pour créer une interface utilisateur dynamique et réactive.

### Backend

- **Java Spring Boot**: Choisi pour sa robustesse et sa flexibilité dans la création d'applications web.
- **Spring Security**: Garantit une sécurité et une gestion de l'authentification de premier ordre.
- **PostgreSQL**: Utilisé comme base de données fiable et performante.

## 🚀 Installation et exécution

1. **Configuration du Backend**:
    - Assurez-vous d'avoir installé Java JDK et Maven.
    - Naviguez vers le répertoire backend.
    - Configurez votre base de données PostgreSQL et mettez à jour `application.yml` avec vos identifiants.
    - Démarrez l'application.
  
⚠️ Attention/Important: Des instructions ou des informations importantes sont fournies ici.

Après la création des tables, effectuez le changement suivant pour garantir que les descriptions des livres soient correctement stockées :

- Naviguez vers la table google_books_info_volume.
- Trouvez la colonne "description".
- Cliquez avec le bouton droit sur "description" et sélectionnez "propriétés".
- Allez à la section "définition" et changez "Length" de (255) à (10000).

3. **Configuration du Frontend**:
    - Assurez-vous d'avoir Node.js et Angular CLI installés.
    - Naviguez vers le répertoire frontend.
    - Exécutez `npm install` pour installer les dépendances.
    - Lancez l'application avec `ng serve`. Elle sera disponible à `http://localhost:4200`.

## 🙌 Contribuer

Si vous souhaitez contribuer à BookSprite, vous êtes les bienvenus ! Faites une fork du dépôt, apportez vos modifications et envoyez une Pull Request.

---

Merci pour votre intérêt pour BookSprite! 📖✨

---

🖋️ Créé avec ❤️ par Rosita.
