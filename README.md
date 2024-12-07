# CS 2341
## Assignment 4

* **@author: Dani Jerez 49272827**
* **@author: Yash Shah 49309058**
* **@duedate: December 06, 2024**

------

### Objective
Write a program that reads in a string from the command line and a dictionary of words (given below) and checks whether it is a "strong" password.
Assume "strong" means that it:
1. is at least 8 characters long,
2. is not a word in the dictionary,
3. is not a word in the dictionary followed by a digit 0-9 (e.g., hello5)

https://www.mit.edu/~ecprice/wordlist.10000

You will implement the dictionary by using hash tables. For each word, associated value is the line number (a: 1, aaron: 4, abandoned: 6)

### Tasks
1. Insert the words into hash table using separate chaining and use below hash functions. Assume M=1000 (fixed size).
   1. First consider the following hashCode() implementation for String, which was used in early versions of Java. Then, consider the current implementation.
```Java
public int hashCode1() {
    int hash = 0;
    int skip = Math.max(1, length() / 8);

    for (int i = 0; i < length(); i += skip)
        hash = (hash * 37) + charAt(i);
    return hash;
}
```
```Java
public int hashCode2() {
    int hash = 0;
    for (int i = 0; i < length(); i++)
        hash = (hash * 31) + charAt(i);
    return hash;
}
```
2. Insert the words into hash table using linear probing and report the results for above hash functions. Assume M=20000 (fixed size).


### Output