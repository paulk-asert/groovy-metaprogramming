Set primes = [2, 3, 5, 7, 11, 13]
List fibs = [1, 1, 2, 3, 5, 8, 13]

// check that there are 4 prime fibs
// hint: replace xxxxxxx with a java.util.Set extension method
assert 4 == primes.xxxxxxx{ it in fibs }.size()

// check again using a different approach
// hint: replace yyyyyyyyy with a java.util.Set extension method
assert 4 == primes.yyyyyyyyy(fibs).size()

// check that the digit '1' appears 6 times in total across both lists
// hints:
// replace aaaaaaa, bbbb, ddddd with java.lang.Iterable ext methods
// replace cccccc with a java.lang.CharSequence extension method
assert 6 == [primes, fibs].aaaaaaa().bbbb().cccccc().ddddd{ it == '1' }
