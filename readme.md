Trinome : Vincent Chailley , Thomas Nagy , Vincent Beherec

Le jeu de tetris est fonctionnel mais pas le mode multijoueur. 
Nous voudrions connecter deux joueurs en demandant à l'un d'utiliser le serveur et à l'autre de s'y connecter (Client).

Pour jouer,
Lorsque le jeu se lance, un serveur démarre et le jeu est en pause (en attente d'un autre joueur).
Cliquez sur la touche 'P' pour continuer à jouer ou pour mettre en pause.
Les touches VK_LEFT et VK_RIGHT (flêche gauche et flêche droite) vous permet de deplacer les pièces à gauche ou à droite.
La touche VK_UP (flêche haut) permet une rotation de la pièce.
La touche VK_DOWN (flêche bas) vous permet de faire descendre plus rapidement la pièce.
La touche VK_SPACE (espace) permet de faire tomber instantanement la pièce.
Rêgles mode solo :
-> Idem que celle du jeu classique
Objectif : Score maximum.

Rêgles mode multi :
-> Lorsque vous aurez supprimé 10 lignes sur votre "terrain", l'adversaire vera une ligne se remplir.
Objectif : Faire en sorte que l'adversaire perde avant vous.

Les principes SOLID sont respectés et nous avons essayé de suivre le modèle C4 vu en cours.

Les Design Pattern utilisés sont :
- Listener : grace à la gestion des évenements clavier.
- Singleton : pour nous permettre d'instancier un seule fois un objet et s'en servir autant qu'on le souhaitait.

Quelques tests Junit ont été fait mais pas de test Mockito.

Voir aussi : https://drive.google.com/folderview?id=0BznAw5MDphKjTmRVNVdLc1J2a1U&usp=sharing

