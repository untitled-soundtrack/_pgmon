# Zusatzfunktion

> Pokedex für das PGMON Projekt

## Inhalt

Mittels dem Lexikon aller Pokémons, alias Pokedéx, kann man nach dem gewünschten Pokémon suchen.

- Die Suche nach dem Pokémon-Namen erfolgt über das obere Input-Textfeld. Hierbei liefert die Suche bereits nach Eingabe weniger Buchstaben Ergebnisse.

- Außerdem können die Pokémons nach deren Pokétyp (nur nach einem Typ möglich) gefiltert werden.

- Jedes gefundene Pokémon wird mit seiner Pokédex-Nr., Namen und all seinen Eigenschaften plus Typ-Icon angezeigt.

Um zum Pokedéx zu gelangen, muss man den linken Button in der Headerbar (Icon mit den Seiten) verwenden.

![Screenshot von Pokedex](/frontend/public/screenshots/pokedex-01.png)

## Hintergrund

Um einige Funktionen von Vue.js und den Elementen von Material Design Lite zu üben, wurden folgende Dinge verwendet:

- MDL Cards | Verwendung und Befüllung mit den Daten aus dem Backend (Pokémon Details)
- Material Symbole | Der Poké-Typ wird als jeweiliges Material Symbol angezeigt
- MDL Textfelder für die Suche | Wiederverwendung von vorhandener Vue.js Komponente
- MDL Textfeld als Select für die Filterung | Alle vorhandenen Poke-Typen werden aus dem Pinia Store gelesen
