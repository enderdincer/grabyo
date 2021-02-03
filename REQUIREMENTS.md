# Grabyo technical test

At Grabyo we love board games and will have an occasional poker night.
Since no one remembers the card ranking you will have to write a program that
compare poker hands and determines a winner.

## 1. Task

A poker hand has a constructor that accepts a string containing 5 cards: 

```
PokerHand hand = new PokerHand("KS 2H 5C JD TD");
```

and a method to compare itself to another hand

```
public enum Result {
	WIN,
	LOSS,
	TIE;
}
	
public Result compareWith(PokerHand hand) {
	/*
	 * Your code here
	 */
	 return Result.TIE;
}
```

The characteristics of the string of cards are:
*   A space is used as card seperator
*   Each card consists of two characters
*   The first character is the value of the card, valid characters are: `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `T`(en), `J`(ack), `Q`(ueen), `K`(ing), `A`(ce)
*   The second character represents the suit, valid characters are: `S`(pades), `H`(earts), `D`(iamonds), `C`(lubs)

The result of your poker hand compare can be one of the 3 options defined by the `PokerHand.Result` enum.

The ranking of the hands should follow the [Texas Hold'em rules](http://freepokerhoney.com/website_images/8245/poker-strategy/poker-hand-rankings.png)

## 2. Requirements

*   __Must__ put all the code in the `src/` folder

*   __Must__ use maven (see below)

*   __Should__ include a README that describes approach/documentation

Apart from these requirements you are free to architect your code the way you want: adding classes, enums or constants. You can use any libraries that you feel are relevant to solve this problem. External dependencies can easily be added using the central maven repository.

Sample unit tests have been included in the code skeleton. Writing more tests is welcome :)

#### Maven

Maven is a dependency management tool that can be easily downloaded and installed. The given project already includes a pom.xml so there should be no additional setup required.

Tests can be run from the command line like so:  
`mvn test`


Good luck ;)
