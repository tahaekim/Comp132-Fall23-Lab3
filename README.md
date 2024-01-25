# Lab-3 Prelab - Knights Tour

In this Lab, you will be practicing programming with **multidimensional arrays** and **ArrayLists**.

An interesting puzzler for chess buffs is the Knight's Tour problem, originally proposed by the mathematician Euler. Can the knight piece move around an empty chess-board and touch each of the 64 squares once and only once? 
Here is more information and an animation demonstrating the problem: [Knight's Tour in Wikipedia](https://en.wikipedia.org/wiki/Knight%27s_tour)

![](/img/first.jpeg?raw=true "")

Let's develop an application that will move the knight around a chessboard until it can not move anymore. Represent the board by an n-by-n two-dimensional int array. Each square is initialized to zero. The knight makes only L-shaped moves (two spaces in one direction and one space in a perpendicular direction). We describe each of the eight possible moves in terms of its horizontal and vertical components. Horizontal moves to the left and vertical moves upward are indicated with negative numbers. The eight move types may be described by two one-dimensional arrays, horizontal and vertical, as follows:

- int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};

- int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};

Note that the given image is for representation. Positions(0-7) do not necessarily match with array elements.

Let the variables `currentRow` and `currentColumn` indicate the row and column, respectively, of the knight's current position. To make a move of type `moveNumber`, where `moveNumber` is between 0 and 7, your program should use the statements

- currentRow += vertical[moveNumber];

- currentCol += horizontal[moveNumber];

to move to a new position.

Consider the given initial information and create a class named KnightsTour with the following variables and methods. This class should be in the chessGame package. 

After that, by using these variables and methods, implement the asked questions in parts 1 and 2.

#### NOTE: In this Lab, you are asked to implement only one class, the KnightsTour class.

### Class Variables
#### - Random random:
We will use Java’s built-in random number generator to decide the initial position of the knight. To use this, you must import java.util.Random. 

The constructor for the Random class does not need any parameters.

#### - int[] horizontal: An integer array for holding horizontal moves as described above.

#### - int[] vertical: An integer array for holding vertical moves as described above.

#### - int currentRow: An integer value to hold the current row number.

#### - int currentCol: An integer value to hold the current column number.

#### - int counter: An integer value to keep the knight’s move count. 

#### - int[][] chessBoard: A 2D NxN java array as an imaginary chess board where the knight moves around. 

#### - int boardSize: An integer to indicate the dimensions of the chess board.

This parameter is taken as an argument from the user.

### Methods
```
 - public KnightsTour()
```
Empty/Default constructor method for the class.

```
- private static boolean isLegalMove(int row, int col)
```
Method for checking if a given position is legal to move or not. In other words, the position should be within the boundaries of the board and not be visited before.

If the move is a legal move, the method should return True. Otherwise, it returns False. 

```
- private static void move(int i)
```
A method for moving the knight to the next position. 

Input integer corresponds to an index for horizontal and vertical arrays. The method sets a new position by updating the current coordinates, as in the example above.

```
- private static void markBoard()
```
A method for marking the chessboard with the current move counter. Assigns the current move count to the current position on the chess board. Check sample output for better understanding.

```
 - private static void increaseCounter()
```
A method for updating the move counter. The move counter is increased by one whenever the knight moves to the next position.

```
 - private static void printBoard()
```
A method for printing the final chess board with the knight's moves marked on it.

Check and follow the example output format for this method.

## PreLab Part I - Single Tour

Write a program to move the knight around the chess-board, visiting board squares only once. Keep a counter that varies from 1 to n^2. Start from a square on the board (chosen randomly). Put a 1 there. Then, choose a move type (one of the possible 8 moves). Not every move type can be possible (out of the board, previously visited). Try always in the following order to find the possible move. First try move type 0 (then 1,2,...,7)  until you find a valid move type. Record the latest count in each square the knight moves to. Test each potential move to see if the knight has already visited that square. Test every potential move to ensure that the knight does not land off the chess-board. The tour ends when either each square is visited, or the knight cannot move further. The starting square is also determined ***randomly***. Print the board at the end when the tour ends.

## Methods for KnightsTour Class 
```
 - private static boolean deterministicTour()
```
This method describes a step of a Single Tour. As described above it checks every position to see if it is legal or not. 

- If it finds a legal move; It increases the move counter.

- Moves the knight to the next position.

- Marks board with current position count and returns True.

- Otherwise, it returns False.

```
- public static void singleTour(Scanner sc)
```
This method creates an NxN chess board with dimensions taken from the user. Randomly determines the knights’ initial position. The initial position should be labeled as 1. Also, the move counter must start from 1.

As mentioned, the tour continues until the knight visits all squares or there is no possible move to make. At the end of the tour board must be printed. See example outputs below. To make it traceable, you should add a random seed into this method. You can do this by using the setSeed method with your Random object. 

The example below is obtained with seed 7.


## PreLab Part II - Warnsdorff's rule

In this part, you will change your approach for a single tour by using a heuristic. This heuristic is called Warnsdorff’s Rule. This approach assumes the knight is moved so that it always proceeds to the square from which the knight will have the fewest onward moves. When calculating the number of onward moves for each candidate square, we do not count moves that revisit any square already visited.

For better understanding, you can consider the image on the left. At a certain moment during the tour, the knight reaches the B-4 position. All the next possible moves from B-4 are labeled with numbers(3-7-7-5-2). These numbers represent the count of the next possible moves if the knight moves to that location. For example, if the knight moves from B-4 to A-2, it will have 2 further possible moves. In such a situation, Warnsdorrf’s heuristic tells us to move to the square with the smallest integer in it, namely 2.

![](/img/second.jpeg?raw=true "")

For the second part of the prelab, write another program to move the knight around the chess-board, visiting board squares only once. Keep a counter that varies from 1 to n^2. Start from a square on the board (chosen randomly). Put a 1 there. Then, choose a move type by applying Warnsdorff’ rule. Again, not every move type can be possible (out of board, previously visited). Record the latest count in each square the knight moves to, as you did in the first part. The tour ends when each square is visited. The starting square is also determined ***randomly***. Print the board at the end when the tour ends.

## Methods for KnightsTour Class 

```
 - private static int legalMoveCounter(int row, int col)
```
This method counts and returns possible “legal” moves that the knight can make from a given position. 

```
 - private static int nextMoveType()
```
This method determines the next move of the knight and returns it as an index to move method. 

At a certain position, first, you should find and store the next possible moves. To store the moves, use an ArrayList.

To determine the next move from candidate moves, you should consider Warnsdorff’s Rule. Among the candidates, you should select the one with the least next move possibilities. In case of a tie, you can keep the first selected move type. 

You should also check the situation where there is no move possibility initially. 

The method should return the move type. If there is no possible move, you should return -1.

```
 - private static boolean WarnsdorffTour()
```

This method is very similar to deterministicTour. Instead of checking the next move one-by-one, this time, you should use Warnsforff’s 

Rule, where you implemented the above method. 

```
 - public static int singleTourWarnsdorff(Scanner sc)
```

This method is just like the singleTour method you previously implemented. 

Instead of using deterministicTour, you should use WarnsdorffTour.

At the end, print the chess board.

To make it traceable, you should add a random seed into this method. You can do this by using the setSeed method with your Random object. 

The example below is obtained with seed 7.

## Main Class

This class is provided for you to test your code. Please do not apply any changes to this class.

### Sample Output for Single Tour

![](/outputs/single.jpeg?raw=true "")

### Sample Output for Warnsdorff Tour

![](/outputs/warnsdorff.jpeg?raw=true "")
