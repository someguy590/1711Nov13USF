/**
 *  Javascript is a scripting language for client-side operations
 *   != Java. "JS is to Java as ham is to hamster"
 *   C-like syntax
 *   -Supports prototypal inheritance
 *   -Loosely typed
 *   -Variables: number, string, object, boolean, null (considered an object), 
 *      NaN (not a number), undefined
 */

var e = {name: "genesis", age:30};
var f = e.age;

function add(a, b) {
	return a+b;
}

function fib(n) {
	if(n < 2) return 1;
	return fib(n-1) + fib(n-2);
		
}