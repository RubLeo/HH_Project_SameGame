# Short Description of tasks
- MVC / Subject-Observer / both ✅
- 2 views: GUI swing & console log (/capture view list all moves) ✅
    - must be easy to de-/register ✅
# Model
- holds difficuly level = amount of colors ✅
- does current state of game ✅
- accepts controlling events to update state ✅
- checks endofgame ✅
- notifies observers when necessary ✅
- consider re-pluggable methods for providing different ways controlling game ✅
  - keyboard / mouse / phone (--> strategy pattern/...)

# ⬆️GRADE 3 ⬆️ Checkpoint

- sound notifications (observer pattern) ✅
  - seperated from GUI  ✅
- next move suggestor (for most scoring next move) ✅
- save/load list of highscores --> Serialization (Book chapter 7.5) ✅
- JavaDoc proper documentation ✅
- attempts of testing with JUnit ✅

# ⬆️GRADE 4 ⬆️ Checkpoint

- code around a framework to implement other similar games easily ✅
- document it's design in report ✅
- show reusibility implementing another game, e.g. 2048 / Sokoban
- github ✅
- no debugging statements ✅

# Handing in 🫴🏼📦

- single zip file ✅
  - incl. PDF report ✅
- Due 24th May

# Prj. Report

- informative title
- introduction explains problem
- ... see [Task_Description.md](Task_Description.md)

# Task List
- Report: Git mentioning, game over bug
- List what to fix after pres to hand-in
  - FINALLY: After final diagramm creation put classes in packages again (model, view, controller, input)


# code in sgm init for custom grid code generation
```java
// init array for games tiles
for (int row = 0; row < GAME_ROWS; row++) {
  for (int col = 0; col < GAME_COLS; col++) {
    int rInt = r.nextInt(difficulty);
    tiles[row][col] = new Tile(colors[rInt]);
    if (rInt == 0) System.out.print("testTiles[" + row + "][" + col + "] = new Tile(Color.red); ");
    if (rInt == 1) System.out.print("testTiles[" + row + "][" + col + "] = new Tile(Color.yellow); ");
    if (rInt == 2) System.out.print("testTiles[" + row + "][" + col + "] = new Tile(Color.blue); ");
    if (rInt == 3) System.out.print("testTiles[" + row + "][" + col + "] = new Tile(Color.green); ");
    if (rInt == 4) System.out.print("testTiles[" + row + "][" + col + "] = new Tile(Color.orange); ");
  }
  System.out.println();
}
```